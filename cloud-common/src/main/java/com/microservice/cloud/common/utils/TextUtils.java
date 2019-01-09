package com.microservice.cloud.common.utils;


/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-9-12 16:13
 */
public class TextUtils {
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !"".equals(value);
    }

    public static boolean isNotEmpty(String... values) {
        boolean flag = true;
        if (values != null) {
            String[] arg1 = values;
            int arg2 = values.length;

            for (int arg3 = 0; arg3 < arg2; ++arg3) {
                String value = arg1[arg3];
                flag &= isNotEmpty(value);
            }
        }

        return flag;
    }

}
