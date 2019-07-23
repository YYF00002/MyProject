package com.project.kafkaconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangbin
 * Copyright @ 2018 Tima Networks Inc. All Rights Reserved. 
 */
@Configuration
@ConditionalOnExpression("${kafka.enabled:false}")
public class KafkaConsumerConfig {
    @Autowired
    private KafkaConfig kafkaConfig;

    @Bean("neusoftKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(kafkaConfig.getPollTimeout());
        factory.setConcurrency(kafkaConfig.getConcurrency());
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> properties = new HashMap<String, Object>(10);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBrokers());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConfig.getAutoCommitIntervalMs());
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConfig.getSessionTimeoutMs());
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConfig.getHeartbeatIntervalMs());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getGroup());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getAutoOffsetReset());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getMaxPollRecords());
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    @Bean("neusoftKafkaListenerContainerFactory2")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory2());
        factory.getContainerProperties().setPollTimeout(kafkaConfig.getPollTimeout());
        factory.setConcurrency(kafkaConfig.getConcurrency());
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory2() {
        Map<String, Object> properties = new HashMap<String, Object>(10);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBrokers());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConfig.getAutoCommitIntervalMs());
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConfig.getSessionTimeoutMs());
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConfig.getHeartbeatIntervalMs());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test2");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getAutoOffsetReset());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getMaxPollRecords());
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }
}
