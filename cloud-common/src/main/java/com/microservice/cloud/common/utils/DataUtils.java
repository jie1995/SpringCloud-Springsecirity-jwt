package com.microservice.cloud.common.utils;

import org.apache.commons.beanutils.PropertyUtils;
import java.util.*;

/**
 * TODO: 数据转换工具类
 *
 * @author junyunxiao
 * @date 2018-9-12 16:12
 */
public class DataUtils {


    /**
     * 将输入参数转换为map
     * @param parameter
     * @return
     */
    public static Map<String, Object> convertToMap(Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Map) {
                return (Map) parameter;
            }

            try {
                return PropertyUtils.describe(parameter);
            } catch (Exception arg1) {
                System.out.println("DataUtils.convertToMap错误：" + arg1.getMessage());
            }
        }

        return new HashMap(16);
    }

}
