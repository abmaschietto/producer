package com.example.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloneConfiguration {
	
	@Value("${spring.rabbitmq.request.routing-key.producerclone}")
	private String queueClone;
	
	@Value("${spring.rabbitmq.request.exchenge.producerclone}")
	private String exchangeClone;
	
	@Value("${spring.rabbitmq.requeste.deadletter.producer}")
	private String deadLetter;
	
	@Bean
	DirectExchange exchangeClone() {
		return new DirectExchange(exchangeClone);
	}
	
	@Bean
	Queue deadLetterClone() {
		return new Queue(deadLetter);
	}
	
	@Bean
	Queue queueClone() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-dead-letter-exchange", exchangeClone);
		args.put("x-dead-letter-routing-key", deadLetter);
		return new Queue(queueClone, true, false, false, args);
	}
	
	@Bean
	public Binding bindingQueueClone() {
		return BindingBuilder.bind(queueClone())
				.to(exchangeClone()).with(queueClone);
	}

	@Bean
	public Binding bindingDeadLetterClone() {
		return BindingBuilder.bind(deadLetterClone())
				.to(exchangeClone()).with(deadLetter);
	}

}
