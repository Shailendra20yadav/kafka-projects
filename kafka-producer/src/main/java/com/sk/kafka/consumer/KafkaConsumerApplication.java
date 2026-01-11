package com.sk.kafka.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication implements CommandLineRunner {

    @Value("${app.topic.name:demo-topic}")
    private String topicName;
    
    @Override
    public void run(String... args) {
        System.out.println("Kafka Consumer started. Listening to topic '" + topicName + "'...");
    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}
