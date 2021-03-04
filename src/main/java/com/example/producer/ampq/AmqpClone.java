package com.example.producer.ampq;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.producer.dto.MessageDto;

@Component
public class AmqpClone implements IAmqpSender<MessageDto> {

	@Value("${spring.rabbitmq.request.routing-key.producerclone}")
	private String routingKey;

	@Value("${spring.rabbitmq.request.exchenge.producerclone}")
	private String exchange;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void producer(MessageDto message) {
		try {
			rabbitTemplate.convertAndSend(exchange, routingKey, message);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
