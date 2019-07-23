package com.project.springbootvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.springbootvideo.mappers")
public class SpringBootVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVideoApplication.class, args);
    }

}
