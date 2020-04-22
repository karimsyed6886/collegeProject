package com.javatechie.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.jwt.api.entity.Book;
import com.javatechie.jwt.api.entity.Client_config;

public interface Book_repository extends JpaRepository<Book,Integer> { 

}
