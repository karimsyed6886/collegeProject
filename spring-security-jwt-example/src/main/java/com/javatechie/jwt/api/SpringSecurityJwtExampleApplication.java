package com.javatechie.jwt.api;

import com.javatechie.jwt.api.entity.Client_config;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.repository.Config_repository;
import com.javatechie.jwt.api.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringSecurityJwtExampleApplication {
	@Autowired
	private UserRepository repository;
	@Autowired
	private Config_repository config_repo;

	@Autowired
	@PersistenceContext
	EntityManager entity;

//    com.javatechie.jwt.api.entity.User.User(int id, String userName, String password, String email, String rollName,
//    		int rollId, Date creationDateTime, Date updateTimestamp, String profile_image, 
//    		String image_url, boolean softDelete, String secretKey, String access_key, String aws_region, String bucket_name)

	/*
	 * @PostConstruct public void initUsers() { List<User> users = Stream.of( new
	 * User(101, "Admin", "Admin", "Admin@gmail.com","Admin",1,null,null, null,
	 * null, false, "lUJtrGqFAMGbKbRQITJBpLlerLD5HZfSICHYnkEO",
	 * "AKIATUHGGMI4IWVPDCP3", "us-east-2", "ahmad457")
	 * ).collect(Collectors.toList()); repository.saveAll(users); }
	 */
	/*
	 * @Transactional
	 * 
	 * @PostConstruct public void initUsers() { //User obj2 =new User();
	 * //constructdb();
	 * 
	 * 
	 * Client_config obj1= new Client_config();
	 * obj1.setAccess_key("AKIATUHGGMI4IWVPDCP3"); obj1.setAws_region("us-east-2");
	 * obj1.setSecretKey("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");
	 * obj1.setBucket_name("ahmad457"); //entity.persist(obj1);
	 * config_repo.save(obj1);
	 * 
	 * 
	 * 
	 * User user= new User(); user.setImage_url(
	 * "https://ahmad457.s3.us-east-2.amazonaws.com/profile_pics/image002.jpg");
	 * user.setPassword("Admin123"); user.setProfile_image("image002.jpg");
	 * user.setRollName("Admin"); user.setUserName("Admin123");
	 * user.setConfigurations(obj1); //entity.persist(user); //entity.flush();
	 * repository.save(user);
	 * 
	 * 
	 * 
	 * List<User> users = Stream.of( new User(101, "Admin", "Admin",
	 * "Admin@gmail.com","Admin",1,null,null, null, null, false,
	 * "lUJtrGqFAMGbKbRQITJBpLlerLD5HZfSICHYnkEO", "AKIATUHGGMI4IWVPDCP3",
	 * "us-east-2", "ahmad457" obj1) ).collect(Collectors.toList());
	 * repository.saveAll(users);
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Transactional public void constructdb() {
	 * 
	 * 
	 * }
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);

	}

}
