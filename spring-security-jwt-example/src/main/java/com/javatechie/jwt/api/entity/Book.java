package com.javatechie.jwt.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
	private  int bookId;
	private String book_name;
	private String book_isbn;
	private String author;
	private String cover_page;
	private String book_url;
	private int createdByuserid;
	@CreationTimestamp
	private Date creationDateTime;
    @UpdateTimestamp
	private Date updateTimestamp;
    
  
	
	
}
