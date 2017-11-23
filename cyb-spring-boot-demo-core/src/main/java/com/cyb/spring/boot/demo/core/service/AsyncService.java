package com.cyb.spring.boot.demo.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	/**
	 * 日志接口
	 */
	private static Logger logger = LoggerFactory.getLogger(AsyncService.class);

	@Async
	public void asyncTask() throws InterruptedException {
		long startMillis = System.currentTimeMillis();
		Thread.sleep(3000L);
		long endMillis = System.currentTimeMillis();
		long millis = endMillis - startMillis;
		logger.debug("AsyncService.task1 comsumes {} ms", millis);
	}

	public void syncTask() throws InterruptedException {
		long startMillis = System.currentTimeMillis();
		Thread.sleep(1000L);
		long endMillis = System.currentTimeMillis();
		long millis = endMillis - startMillis;
		logger.debug("AsyncService.task1 comsumes {} ms", millis);
	}
}
