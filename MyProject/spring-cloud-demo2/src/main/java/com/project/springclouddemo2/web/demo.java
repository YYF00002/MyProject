package com.project.springclouddemo2.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class demo {

    @Value("${name}")
    private String name;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/test" ,method = RequestMethod.GET)
    public  String demo(){
        String forObject = restTemplate.getForObject("http://127.0.0.1:8080/test", String.class);

        System.out.println(forObject);

        return "这个是demo2";
    }
}
