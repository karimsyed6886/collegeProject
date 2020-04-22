package com.javatechie.jwt.api.service;

import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUserName(username);
        CustomUserDetails userDetails =null;
        if(user!=null) {
        	userDetails = new CustomUserDetails();
        	//String encrypted_password = passwordEncoder.encode(user.getPassword());
        	//user.setPassword(encrypted_password);
        	//userDetails.setUser(user);
        	return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());

       }else {
    	   throw new UsernameNotFoundException("user not found with name :"+ username);
       }
       //return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
      //return null;   
    }
}
