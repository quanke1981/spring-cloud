package com.example.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("a-bootiful-client")
public interface MyServiceClient {
	
	@RequestMapping(method=RequestMethod.GET, value="hello")
	String sayHello();
}