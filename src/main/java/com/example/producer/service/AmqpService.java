package com.example.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.producer.ampq.IAmqpProducer;
import com.example.producer.dto.MessageDto;

@Service
public class AmqpService implements IAmqpService{
	
	@Autowired
	private IAmqpProducer<MessageDto> amqp;

	@Override
	public void sendToConsumer(MessageDto message) {
		amqp.producer(message);
	}

}
