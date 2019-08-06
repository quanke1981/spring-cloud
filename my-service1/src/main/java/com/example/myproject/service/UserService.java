package com.example.myproject.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Book;
import com.example.myproject.model.User;
import com.example.myproject.model.UserDetailsImpl;
import com.example.myproject.repository.UserRepository;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    
    protected JpaRepository<User, Integer> getRepository() {
		return this.repository;
	}
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(userName);
        return Optional.ofNullable(user).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
               .map(UserDetailsImpl::new).get();
    }
}

