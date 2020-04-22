package com.javatechie.jwt.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	/*
	 * public User(int id, String userName, String password, String email, String
	 * rollName, int rollId, Date creationDateTime, Date updateTimestamp, String
	 * profile_image, String image_url, boolean softDelete, String secretKey, String
	 * access_key, String aws_region, String bucket_name,Client_config
	 * configurations) { super(); this.id = id; this.userName = userName;
	 * this.password = password; this.email = email; this.rollName = rollName;
	 * this.rollId = rollId; this.creationDateTime = creationDateTime;
	 * this.updateTimestamp = updateTimestamp; this.profile_image = profile_image;
	 * this.image_url = image_url; this.softDelete = softDelete; this.secretKey =
	 * secretKey; this.access_key = access_key; this.aws_region = aws_region;
	 * this.bucket_name = bucket_name; this. configurations=configurations; }
	 */
	
	
	  @Id
	  @GeneratedValue
	  private int id;

	  public User(int id,
			@Size(max = 20, min = 3, message = " User name should be between 3 to 20 characters ") @NotEmpty(message = "Please enter name") String userName,
			String password, @Email(message = " Invalid Email ") @NotEmpty(message = "Please enter email") String email,
			@NotEmpty(message = "Please enter roll name") String rollName, int rollId, Date creationDateTime,
			Date updateTimestamp, String profile_image, String image_url, boolean softDelete, String secretKey,
			String access_key, String aws_region, String bucket_name, Client_config configurations, Set<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.rollName = rollName;
		this.rollId = rollId;
		this.creationDateTime = creationDateTime;
		this.updateTimestamp = updateTimestamp;
		this.profile_image = profile_image;
		this.image_url = image_url;
		this.softDelete = softDelete;
		this.secretKey = secretKey;
		this.access_key = access_key;
		this.aws_region = aws_region;
		this.bucket_name = bucket_name;
		this.configurations = configurations;
		this.roles = roles;
	}
	@Size(max = 20, min = 3, message = " User name should be between 3 to 20 characters ")
	  @NotEmpty(message = "Please enter name")
	  private String userName;
	  private String password; 
	  @Email(message = " Invalid Email ")
	  @NotEmpty(message = "Please enter email")
	  private String email;
	  @NotEmpty(message = "Please enter roll name")
	  private String rollName;
	  private int rollId;
	  @CreationTimestamp
	  private Date creationDateTime;
	  @UpdateTimestamp
	  private Date updateTimestamp;
	  private String profile_image;
	 
	  private String image_url;
	  private boolean softDelete;
	  private String secretKey;
	  private String  access_key;
	  private String aws_region;
	  private String bucket_name;
	  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	  @OneToOne(fetch=FetchType.LAZY)
	  private Client_config configurations;
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles;
	  
	  
	  
	  
	  
	  
	  
	  
	   
	 
	
	

}
