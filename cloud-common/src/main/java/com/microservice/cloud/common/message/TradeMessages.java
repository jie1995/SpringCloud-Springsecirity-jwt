package com.microservice.cloud.common.message;

import java.io.Serializable;

/**
 * TODO:消息载体
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/11 17:16
 */
public class TradeMessages<T> implements Serializable {

	private static final long serialVersionUID = -2352663152701894912L;

	/**
	 * 请求用户ID
	 */
	private String userId;

	/**
	 * 签名
	 */
	private String sign="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIm2Xri9vqKzXdaPp6Hnq2hOcHFhrXA7cDXb5ft0eYK4USRZ9NCI/aw0Hk8L04/1F9eaIYE2BykYFNSfNMfQV7gUDVKJCXFCOGUQ2X88/CNfiNVlII/Z+UoDysELNbvMMhkhA6c1mUPSSHuzSjMdmHuEvs8ujZ4lnQKDNwICScxNAgMBAAECgYBRlsbP1TsY0gQKltnORp97EN31jtVe5Rg6EKpVLFDBbcpFKzasRj1kxQwf8PXLGH6jsiGfmA7t/eJ5hkmTCJCvEhwtJqIo9lLEFyPq+CCI7cqg59rN4EDsE7l00CczpEnEs4izR1EsqXWyxvgBtKiqv8KddJuyt6ZWdv2q6SWVgQJBANhD/5btD42zfPKEXan3rh/LOjzFdan6oSEXvCLKwTQgpZ8DSyKluSzO0HUK6lH080HrmEZrf4NTLtOdlAHsG+UCQQCjA6OTMzJc6SjuGMbF0IXtde8Bq0OSjrtQ2RkLyJp4BBme+cz5vGw4pVcwW1cqCZk+BCd73ZuXmbjoq0WAMPhJAkEAvk1PqjpeVl2b1Dffx8G4CiglzfYfrPxrLxiYp7Dsw/b6ZmtagVl9Ec3HJ0b8nDPEnrw2mbjcUu4upW3jILHodQJAF3c8osHp7An8RFn4sx0TSl2BrEHVFlHJkRfPTSzxX3lnnsTixshi47yZUnKzl2+OSakbbe82qJoOTh/pf0yNUQJAP88Lsw4YZjYzSrqmXoaYIJQk652GzjbtmTI4fqntPetzik+9zxULSzSHV42WUdDMUHhLdHwzMO0YoV10DhOCeg==";

	/**
	 * 登陆标识
	 */
	private String token;

	/**
	 * 请求返回码
	 */
	private String resultCode;

	/**
	 * 请求返回消息
	 */
	private String resultMessage;

	/**
	 *  请求的唯一编号，接口处理完业务后，将返回客户请求时传入的唯一编号
	 */
	private String requestUniqeld;
	/**
	 * 流水号
	 */
	private String serial;

	/**
	 * 返回值Json格式
	 */
	private T data;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getRequestUniqeld() {
		return requestUniqeld;
	}

	public void setRequestUniqeld(String requestUniqeld) {
		this.requestUniqeld = requestUniqeld;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
