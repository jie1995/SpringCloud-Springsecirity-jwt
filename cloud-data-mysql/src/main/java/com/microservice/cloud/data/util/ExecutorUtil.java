package com.microservice.cloud.data.util;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * TODO: sql执行工具类
 *
 * @author junyunxiao
 * @date 2018-9-12 10:15
 */
public class ExecutorUtil {

    private static Field additionalParametersField;

    static {
        try {
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取 BoundSql 属性值 additionalParameters
     *
     * @param boundSql
     * @return
     */
    public static Map<String, Object> getAdditionalParameter(BoundSql boundSql) {
        try {
            return (Map<String, Object>) additionalParametersField.get(boundSql);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param executor
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @param boundSql
     * @param <E>
     * @return
     * @throws SQLException
     */
    public static  <E> List<E> pageQuery(Executor executor, MappedStatement ms, Object parameter,RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql,CacheKey cacheKey) throws SQLException {
        String pageSql=getPageSql(boundSql,rowBounds,cacheKey);
        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
        Map<String,Object> additionalParameters=getAdditionalParameter(boundSql);
        //设置动态参数
        for (String key : additionalParameters.keySet()) {
            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        return executor.query(ms,parameter,RowBounds.DEFAULT,resultHandler,cacheKey,pageBoundSql);
    }


    /**
     * mysql分页sql获取
     * @param boundSql
     * @param rowBounds
     * @param pageKey
     * @return
     */
    public static String getPageSql(BoundSql boundSql,RowBounds rowBounds,CacheKey pageKey){
        String sql=boundSql.getSql();
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        if (rowBounds.getOffset() == 0) {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(rowBounds.getLimit());
        } else {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(rowBounds.getOffset());
            sqlBuilder.append(",");
            sqlBuilder.append(rowBounds.getLimit());
            pageKey.update(rowBounds.getLimit());
        }
        return sqlBuilder.toString();
    }


    /**
     * 获取oracle分页查询语句
     * @param boundSql
     * @param rowBounds
     * @param pageKey
     * @return
     */
    public static String getOraclePageSql (BoundSql boundSql, RowBounds rowBounds, CacheKey pageKey) {
        String sql=boundSql.getSql();
        int startRow = rowBounds.getOffset();
        int endRow = rowBounds.getOffset() + rowBounds.getLimit();
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
        if (startRow > 0) {
            sqlBuilder.append("SELECT * FROM ( ");
        }
        if (endRow > 0) {
            sqlBuilder.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");
        }
        sqlBuilder.append(sql);
        if (endRow > 0) {
            sqlBuilder.append(" ) TMP_PAGE WHERE ROWNUM <= ");
            sqlBuilder.append(endRow);
            pageKey.update(endRow);
        }
        if (startRow > 0) {
            sqlBuilder.append(" ) WHERE ROW_ID > ");
            sqlBuilder.append(startRow);
            pageKey.update(startRow);
        }
        return sqlBuilder.toString();
    }



}
