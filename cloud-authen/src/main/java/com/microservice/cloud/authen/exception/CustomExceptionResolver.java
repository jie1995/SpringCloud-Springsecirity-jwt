package com.microservice.cloud.authen.exception;

import com.microservice.cloud.common.message.TradeMessages;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

/**
 * TODO: 异常统一处理
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@ControllerAdvice
public class CustomExceptionResolver {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public TradeMessages<String> exceptionHandler(Exception e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("1000");
		messages.setResultMessage("系统发生异常，异常信息为:"+e.getMessage());
		return messages;
	}

	@ResponseBody
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public TradeMessages<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("405");
		messages.setResultMessage("接口请求异常,请核对接口所支持的请求方式（Get/Post）");
		messages.setData(null);
		return messages;
	}

	@ResponseBody
	@ExceptionHandler(value = MultipartException.class)
	public TradeMessages<String> multipartExcptionHandler(MultipartException e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("10001");
		messages.setResultMessage("文件上传发生异常，异常信息为:"+e.getMessage());
		messages.setData(null);
		return messages;
	}

	/**
	 * 用户名不存在，异常处理
	 * @param e
	 * @return
	 */
/*	@ResponseBody
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public TradeMessages<String> usernameNotFoundExceptionHandler(UsernameNotFoundException e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("10002");
		messages.setResultMessage(e.getMessage());
		messages.setData(null);
		return messages;
	}*/

}
