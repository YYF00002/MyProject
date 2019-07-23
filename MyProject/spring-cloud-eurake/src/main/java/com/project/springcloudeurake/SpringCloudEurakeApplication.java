package com.project.springcloudeurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurakeApplication.class, args);
    }

}
