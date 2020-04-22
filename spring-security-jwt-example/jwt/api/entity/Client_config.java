package com.javatechie.jwt.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Client_configurations")
public class Client_config {
	
	  @javax.persistence.Id
	  @GeneratedValue
	  private int    Id;
	  private int user_id;
	  private String secretKey;
	  private String access_key;
	  private String aws_region;
	  private String bucket_name;
	  
	  @CreationTimestamp 
	  private Date creationDateTime;
	  
	  @UpdateTimestamp 
	  private Date updateTimestamp;
	 
	  

}
