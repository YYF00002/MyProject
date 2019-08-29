package com.project.kafkaconsumer.service;


import com.project.kafkaconsumer.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {
    @Autowired
    private KafkaConfig kafkaConfig;


    @KafkaListener(topics = {"${kafkaConfig.topic}"},containerFactory="neusoftKafkaListenerContainerFactory",group = "test1")
    public void receive(String message) {
        System.out.println("YYF--消费消息:" + message);
    }

    @KafkaListener(topics = {"yyf_topic"},containerFactory="neusoftKafkaListenerContainerFactory2",group = "test2")
    public void receive2(String message) {
        System.out.println("YYF2--消费消息:" + message);

    }
}