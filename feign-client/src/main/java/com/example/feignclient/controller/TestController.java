package com.example.feignclient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignclient.MyServiceClient;


@RestController
public class TestController {
	
	@Autowired
	MyServiceClient myService;

    @RequestMapping("/test")
    public String get() {
        return myService.sayHello();
    }
    
}