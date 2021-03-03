package com.example.producer.ampq;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.producer.dto.MessageDto;

@Component
public class AmqpProducer implements IAmqpProducer<MessageDto>{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	
	@Value("${spring.rabbitmq.request.exchenge.producer}")
	private String exchange;

	@Override
	public void producer(MessageDto message) {
		try {
			rabbitTemplate.convertAndSend(exchange, queue, message);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
