package com.javatechie.jwt.api.repository;

import com.javatechie.jwt.api.entity.Client_config;
import com.javatechie.jwt.api.entity.User;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
  
   
	User findByUserName(String username);
   

}
