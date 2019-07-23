package com.project.demo.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class demo {

    @Value("${name}")
    private String name;


    @RequestMapping(path = "/test" ,method = RequestMethod.GET)
    public  String demo(){
        log.info("这个是info日志：{}",name);
        log.debug("这个是debug日志：{}",name);
    return "这个是"+name;
    }
}
