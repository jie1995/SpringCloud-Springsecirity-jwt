package com.microservice.cloud.authen.security.properties;

/**
 * @describe url配置
 * @author weili
 */
public interface SecurityConstants {

    /**
     * 验证码的redis键
     */
    String SMS_CODE_KEY="SMSCODE";

    /**
     * 验证码长度
     */
     int LENGTH = 6;
    /**
     * 过期时间
     */
     int EXPIRE_TIME = 60;

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * http请求中默认的手机号的参数的名称
     */
    String DEFAULT_PHONE_PARAMETER="id";

     /**
     * 默认的用户注册请求处理url
     */
    String DEFAULT_REGISTER_URL = "/register";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/loginByMobile";



    //----------------
    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";


    /**
     * weixin appID
     */
    String DEFAULT_SOCIAL_WEIXIN_APP_ID = "wx246bae8548b676c6";

    /**
     * weixin appsECRET
     */
    String DEFAULT_SOCIAL_WEIXIN_APP_SECRET = "62dbd3330ad2284b37677da9e6315903";


    /**
     *自定义社交social拦截地址  默认/auth  (SocialAuthenticationFilter)
     */
    String DEFAULT_SOCIAL_PROCESS_URL = "/login";

    /**
     * 提供商的ID
     */
    String DEFAULT_SOCIAL_QQ_PROVIDER_ID = "qq";

    /**
     * 提供商的ID
     */
    String DEFAULT_SOCIAL_WEIXIN_PROVIDER_ID = "weixin";


    /**
     * 默认用户密码
     */
    String DEFAULT_PASSWORD="123456";
}
