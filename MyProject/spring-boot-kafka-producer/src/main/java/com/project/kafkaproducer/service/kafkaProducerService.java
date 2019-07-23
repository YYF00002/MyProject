package com.project.kafkaproducer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@EnableScheduling
@EnableKafka
public class kafkaProducerService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private int i=0;


    /**
     * 定时任务
     */
    @Scheduled(cron = "00/1 * * * * ?")
    public void send(){
        String message = (i++)+"YYF";
        ListenableFuture future = kafkaTemplate.send("yyf_topic", "这是topic1的消息："+message);
//        ListenableFuture future2 = kafkaTemplate.send("yyf_topic1",1,"这是topic2的消息："+message);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable -> System.out.println("消息发送失败：" + message));
//        future2.addCallback(o -> System.out.println("send2-消息发送成功：" + message), throwable -> System.out.println("消息发送失败2：" + message));

    }


}
