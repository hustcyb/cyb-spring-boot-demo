package com.cyb.spring.boot.demo.api.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.spring.boot.demo.api.domain.ErrorCodeEnum;
import com.cyb.spring.boot.demo.api.domain.ExceptionResponse;
import com.cyb.spring.boot.demo.domain.wechat.WechatException;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 全局异常处理程序
 * 
 * @author Administrator
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理数据验证失败异常
	 * 
	 * @param exception
	 *            数据验证失败异常
	 * @return 异常响应
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ExceptionResponse handleConstraintViolationException(
			ConstraintViolationException exception) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionHandler.handleConstraintViolationException: start, exception message = {}",
					exception.getMessage());
		}

		StringBuilder builder = new StringBuilder(100);
		String delimiter = "";
		for (ConstraintViolation<?> violation : exception
				.getConstraintViolations()) {
			builder.append(delimiter + violation.getMessage());
			delimiter = "\n";
		}

		ExceptionResponse response = new ExceptionResponse(
				ErrorCodeEnum.VALIDATION.getCode(), builder.toString());
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExcepitonHandler.handleContraintViolationException: end, return = {}",
					JsonUtils.bean2Json(response));
		}

		return response;
	}

	/**
	 * 处理微信请求异常
	 * 
	 * @param wechatException
	 *            微信请求异常
	 * @return 微信请求异常
	 */
	@ExceptionHandler(WechatException.class)
	@ResponseBody
	public ExceptionResponse handleWechatException(
			WechatException wechatException) {
		logger.error("微信请求异常", wechatException);

		Integer errorCode = wechatException.getErrorCode();
		String message = wechatException.getMessage();
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionHandler.handleWechatException: start, error code = {}, message = {}",
					errorCode, message);
		}

		ExceptionResponse response = new ExceptionResponse(
				ErrorCodeEnum.WECHAT.getCode(), message);
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionHandler.handleWechatException: end, return = {}",
					JsonUtils.bean2Json(response));
		}

		return response;
	}

	/**
	 * 处理未知异常
	 * 
	 * @param exception
	 *            未知异常
	 * @return 响应结果
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ExceptionResponse handleException(Exception exception) {
		logger.error("未知异常", exception);
		String message = exception.getMessage();
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionController.handleException: start, exception message = {}",
					message);
		}

		ExceptionResponse response = new ExceptionResponse(
				ErrorCodeEnum.UNKNOWN.getCode(), exception.getMessage());
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionController.handleException: end, return = {}",
					JsonUtils.bean2Json(response));
		}

		return response;
	}
}
