package com.example.producer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.dto.MessageDto;
import com.example.producer.service.AmqpService;

@RestController
@RequestMapping(value = "/producer")
public class ControllerRabbit {
	
	@Autowired
	private AmqpService amqp;
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PostMapping(value = "/sender")
	public void sender(@RequestBody MessageDto message) {
		amqp.sendToConsumer(message);
	}

}
