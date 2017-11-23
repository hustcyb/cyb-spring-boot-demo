package com.cyb.spring.boot.demo.api.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.spring.boot.demo.core.service.AsyncService;

/**
 * 异常代码控制器
 * @author Administrator
 *
 */
@RequestMapping("async")
@RestController
public class AsyncController {

	/**
	 * 日志接口
	 */
	private static Logger logger = LoggerFactory.getLogger(AsyncController.class);
	
	@Resource
	private AsyncService asyncService;
	
	@GetMapping
	public String test() throws InterruptedException {
		if (logger.isDebugEnabled()) {
			logger.debug("AsyncController.test: start");
		}
		
		long startMillis = System.currentTimeMillis();
		asyncService.asyncTask();
		asyncService.syncTask();
		long endMillis = System.currentTimeMillis();
		
		long millis = endMillis - startMillis;
		String message = String.format("AsyncController.test takes %d ms", millis);
		if (logger.isDebugEnabled()) {
			logger.debug("AsyncController.test: end");
		}
		
		return message;
	}
}
