package com.example.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.model.Role;
import com.example.myproject.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController<Role, RoleService> {
	
}
