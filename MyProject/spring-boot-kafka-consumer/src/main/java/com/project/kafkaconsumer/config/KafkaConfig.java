package com.project.kafkaconsumer.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="kafka")
@Data
public class KafkaConfig {

    private boolean enabled;
    private String brokers;
    private String group;
    private String topic;
    private int bachSize;
    private int lingerMs;
    private int bufferMemory;
    private String autoCommitIntervalMs;
    private String sessionTimeoutMs;
    private String heartbeatIntervalMs;
    private String autoOffsetReset;
    private int maxPollRecords;
    private int concurrency;
    private int pollTimeout;





//    @KafkaListener(topics = {"yyf_topic1"})
//    public void receive(String message){
//        System.out.println("YYF--消费消息:" + message);
//    }
//
//    @KafkaListener(topics = {"yyf_topic1"})
//    public void receive2(String message){
//        System.out.println("YYF2--消费消息:" + message);
//    }


}
