package com.java1234.util;

import com.java1234.dal.enums.DataSourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多路数据源
 *
 * @author lt on 2015/4/11.
 * @version 1.0.0
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DataSourceEnum> dataSourceKey = new InheritableThreadLocal<>();

    public static DataSourceEnum getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void setDataSourceKey(DataSourceEnum dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (dataSourceKey.get() == null) {
            return DataSourceEnum.MAIN;
        }
        return dataSourceKey.get().name();
    }
}
