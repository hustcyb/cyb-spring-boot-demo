package com.cyb.spring.boot.demo.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cyb.spring.boot.demo.common.exception.ExceptionResponse;
import cyb.spring.boot.demo.common.json.JsonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ExceptionResponse handleException(HttpServletRequest request,
			Exception exception) {
		String message = exception.getMessage();
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GlobalExceptionController.handleException: start, exception message = {}",
					message);
		}

		ExceptionResponse response = JsonUtils.json2Bean(message,
				ExceptionResponse.class);
		if (response == null) {
			logger.error("未知异常", exception);
			response = new ExceptionResponse("未知异常");
		}

		return response;
	}
}
