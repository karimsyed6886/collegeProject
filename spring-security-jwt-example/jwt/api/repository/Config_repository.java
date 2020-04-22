package com.javatechie.jwt.api.repository;

import com.javatechie.jwt.api.entity.Client_config;
import com.javatechie.jwt.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Config_repository extends JpaRepository<Client_config,Integer> {
    //User findByUserName(String username);


}
