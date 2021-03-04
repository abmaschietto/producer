package com.example.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.producer.ampq.AmqpClone;
import com.example.producer.ampq.AmqpProducer;
import com.example.producer.ampq.IAmqpSender;
import com.example.producer.dto.MessageDto;

@Service
public class AmqpService implements IAmqpService{
	
	@Autowired
	private AmqpProducer amqp;
	
	@Autowired
	private AmqpClone amqpClone;

	@Override
	public void sendToConsumer(MessageDto message) {
		if(message.getIndice() == 2) {
			amqpClone.producer(message);
			return;
		}
		amqp.producer(message);
	}

	
}
