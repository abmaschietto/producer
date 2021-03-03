package com.example.producer.service;

import com.example.producer.dto.MessageDto;

public interface IAmqpService {

	void sendToConsumer(MessageDto message);
}
