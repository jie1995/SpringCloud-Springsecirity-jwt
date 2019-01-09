package com.microservice.cloud.data.sqlsource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

/**
 * TODO: BoundSqlSqlSource
 *
 * @author junyunxiao
 * @date 2018-9-15 18:25
 */
public class BoundSqlSqlSource implements SqlSource {

    private BoundSql boundSql;

    public BoundSqlSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return this.boundSql;
    }
}
