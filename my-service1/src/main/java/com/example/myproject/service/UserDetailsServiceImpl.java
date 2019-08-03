package com.example.myproject.service;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Role;
import com.example.myproject.model.User;
import com.example.myproject.model.UserDetailsImpl;
import com.example.myproject.repository.RoleRepository;
import com.example.myproject.repository.UserRepository;

@Service
public class UserDetailsServiceImpl extends BaseService<User> implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByFirstName(userName);
        return Optional.ofNullable(user).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
               .map(UserDetailsImpl::new).get();
    }
}

