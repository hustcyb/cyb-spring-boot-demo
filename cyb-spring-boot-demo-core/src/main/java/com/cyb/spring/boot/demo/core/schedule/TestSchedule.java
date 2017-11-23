package com.cyb.spring.boot.demo.core.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory.getLogger(TestSchedule.class);
	
	@Scheduled(cron = "${cyb.schedule.task1.cron}")
	public static void task1() throws InterruptedException {
		logger.debug("TestSchedule.task1: start");
		Thread.sleep(6000L);
		logger.debug("TestSchedule.task1: end");
	}
}
