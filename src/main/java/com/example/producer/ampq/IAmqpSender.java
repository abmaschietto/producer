package com.example.producer.ampq;

public interface IAmqpSender<T>{

	void producer(T t);
}
