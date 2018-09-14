package com.mq;

import org.apache.activemq.ActiveMQConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息生产者实例
 * @author ljk
 *
 */
public class Producer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
	private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String SUBJECT = "test-activemq-queue";
	
	public static void main(String[] args) {
		
	}
}
