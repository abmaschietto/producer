package com.example.producer.ampq;

public interface IAmqpProducer<T>{

	void producer(T t);
}
