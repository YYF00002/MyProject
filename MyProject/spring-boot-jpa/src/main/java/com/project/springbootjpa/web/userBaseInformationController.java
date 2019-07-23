package com.project.springbootjpa.web;


import com.project.springbootjpa.service.IUserBaseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userBaseInformation")
public class userBaseInformationController {
    @Autowired
    private IUserBaseInformation userBaseInformationService;


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public void addUser(){
        userBaseInformationService.addUser();
    }
}
