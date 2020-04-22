package com.javatechie.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.jwt.api.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Integer>{

}
