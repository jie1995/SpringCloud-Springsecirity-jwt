package com.microservice.cloud.data.service;


import com.microservice.cloud.common.page.Page;
import com.microservice.cloud.common.utils.DataUtils;
import com.microservice.cloud.common.utils.TextUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO: Mybatis数据库操作模板
 *
 * @author junyunxiao
 * @date 2018-9-15 18:54
 */

@Service(value = "dataServiceStat")
public class DataServiceMybatis {

    private static Logger logger = LoggerFactory.getLogger(DataServiceMybatis.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     *
     * @param getData
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> List<T> query(String getData, Object parameter) {
        long time = System.currentTimeMillis();
        List<T> list = sqlSessionTemplate.selectList(getData, parameter);
        time = System.currentTimeMillis() - time;
        logger.info("com.microservice.cloud.data.service.DataServiceMybatis.query:sql执行时间"+time+"ms");
        return list;
    }


    /**
     *
     * @param getData
     * @param getDataCount
     * @param parameter
     * @param page
     * @param <T>
     * @return
     */
    public <T> Page<T> query(String getData, String getDataCount, Object parameter, Page<T> page) {
        Map<String, Object> mapParameter = DataUtils.convertToMap(parameter);
        long time = System.currentTimeMillis();
        if (page.getPageSize() > 0) {
            if (TextUtils.isNotEmpty(getDataCount)) {
                page.setTotal(((Integer) sqlSessionTemplate.selectOne(getDataCount, mapParameter)).intValue());
            }
            if (page.getTotal() > 0) {
                int pageCurrent = page.getPageCurrent();
                if (pageCurrent > 0) {
                    --pageCurrent;
                }
                page.setRows(sqlSessionTemplate.selectList(getData, mapParameter,new RowBounds(pageCurrent * page.getPageSize(), page.getPageCurrent())));
            } else {
                page.setRows(new ArrayList<>());
            }
        } else {
            page.setRows(sqlSessionTemplate.selectList(getData, mapParameter));
            page.setTotal(page.getRows().size());
        }
        time = System.currentTimeMillis() - time;
        logger.info("sql执行时间:"+time+"ms");
        return page;
    }

    /**
     *
     * @param getData
     * @param name
     * @param parameter
     * @param <T>
     * @param <V>
     * @return
     */
    public <T, V> Map<T, V> query(String getData, String name, Object parameter) {
        long time = System.currentTimeMillis();
        Map<String, Object> map = sqlSessionTemplate.selectMap(getData, parameter, name);
        time = System.currentTimeMillis() - time;
        logger.info("sqk执行时间:"+time);
        return (Map<T, V>) map;
    }

    /**
     *
     * @param getObject
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T getObject(String getObject, Object parameter) {
        long time = System.currentTimeMillis();
        T result =sqlSessionTemplate.selectOne(getObject, parameter);
        time = System.currentTimeMillis() - time;
        logger.info("com.maomiyibian.microservice.provider.template.DataServiceMybatis.getObject: sql执行时间"+time+"ms");
        return result;
    }

    /**
     *
     * @param getMap
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getMap(String getMap, Object parameter) {
        long time = System.currentTimeMillis();
        Map<String, Object> map = sqlSessionTemplate.selectOne(getMap, parameter);
        time = System.currentTimeMillis() - time;
        logger.info("sql执行时间:"+time);
        return (Map<String, T>) map;
    }

    /**
     *
     * @param insertData
     * @param parameter
     */
    public int insert(String insertData, Object parameter) {
        return sqlSessionTemplate.insert(insertData, parameter);
    }


    /**
     *
     * @param updateData
     * @param parameter
     */
    public void update(String updateData, Object parameter) {
        sqlSessionTemplate.update(updateData, parameter);
    }
}
