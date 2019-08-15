package com.example.myproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Role;
import com.example.myproject.repository.RoleRepository;

@Transactional
@Service
public class RoleService extends BaseService<Role> {

	@Autowired
	protected RoleRepository repository;
	
	@Override
	protected JpaRepository<Role, Integer> getRepository() {
		return repository;
	}
	
	@Override
	public List<Role> getAll() {
		
		List<Role> roles = getRepository().findAll();
		roles.forEach(role->{
			role.getUsers();
		});
		
		return roles;
	}
}
