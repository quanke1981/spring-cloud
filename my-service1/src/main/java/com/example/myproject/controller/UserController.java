package com.example.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.model.User;
import com.example.myproject.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController extends BaseController<User, UserDetailsServiceImpl> {
	
	
}
