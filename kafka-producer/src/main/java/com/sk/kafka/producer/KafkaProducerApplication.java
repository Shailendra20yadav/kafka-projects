package com.sk.kafka.producer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.name:demo-topic}")
    private String topicName;

    public KafkaProducerApplication(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) {
    	try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Kafka Producer started. Type messages to send to topic '" 
                               + topicName + "'. Type 'exit' to quit.");

            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message.trim())) {
                    System.out.println("Exiting Kafka producer...");
                    break;
                }

                kafkaTemplate.send(topicName, message);
                System.out.println("Sent: " + message);
            }
        }
    }
}
