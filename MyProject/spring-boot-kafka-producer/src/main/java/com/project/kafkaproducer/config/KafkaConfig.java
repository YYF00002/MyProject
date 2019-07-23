package com.project.kafkaproducer.config;


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


}
