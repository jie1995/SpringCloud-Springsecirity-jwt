package com.microservice.cloud.authen.security.validate;

import com.microservice.cloud.authen.security.properties.SecurityConstants;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
public enum ValidateCodeType {

    /**
     * 短信验证码,enum实例
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
