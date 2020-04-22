package com.javatechie.jwt.api.controller;

import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.services.s3.AmazonS3;
import com.javatechie.jwt.api.entity.Response;
import com.javatechie.jwt.api.entity.Role;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.exception.ResourceNotFoundException;
import com.javatechie.jwt.api.exception.UserNotFoundException;
import com.javatechie.jwt.api.repository.Config_repository;
import com.javatechie.jwt.api.repository.RoleRepository;
import com.javatechie.jwt.api.repository.UserRepository;
import com.javatechie.jwt.api.util.JwtUtil;
import com.javatechie.jwt.api.util.S3util;
import com.sipios.springsearch.anotation.SearchSpec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/user")
public class Usercontroller {
	@Autowired
	private UserRepository employeeRepository;
    @Autowired
    private JwtUtil jwtUtil;
   // @Autowired
  //  private AuthenticationManager authenticationManager;
    @Autowired
    private S3util s3util;
    @Autowired
    private Config_repository config_repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository role_Repository;

  
    
	/*
	 * @PostMapping("/authenticate") public String generateToken(@RequestBody
	 * AuthRequest authRequest) throws Exception { try {
	 * 
	 * System.out.println("****** user name***"+ authRequest.getUserName()
	 * +" and password :"+authRequest.getPassword());
	 * authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(authRequest.getUserName(),
	 * authRequest.getPassword()) ); } catch (Exception ex) { throw new
	 * Exception("inavalid username/password"); } return
	 * jwtUtil.generateToken(authRequest.getUserName()); }
	 */
    
    
    
    @GetMapping("/userById/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") int employeeId)
        {
        Optional<User> user = employeeRepository.findById(employeeId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("***user not found exception ***");
        	
        }else {
           return ResponseEntity.ok().body(user);
        }
    }
    
    
    
  //  @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
      return employeeRepository.findAll();
    }
    
    
    
    @SuppressWarnings({ "deprecation", "static-access" })
	@PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    	//create a connectiion to the aws s3
    	//String destination_path="profile_pics/image002.jpg";
    	
    	String source_path="/media/jwt/spring-security-jwt-example/src/main/image002.jpg";
    	AmazonS3 s3client= s3util.createClientconnection(user.getConfigurations().getAccess_key(), user.getConfigurations().getSecretKey());
       // boolean bucketCheck=	s3util.createBucket(user.getBucket_name());
        //checking s3 bucket is already exisit or not
	
		  if(s3client.doesBucketExist(user.getConfigurations().getBucket_name())) {
			  
		  System.out.println("*********** bucket is already existed ***********");
		 }else {
			 System.out.println(" ******** new bucket is created ************");
			 s3client.createBucket(user.getConfigurations().getBucket_name());
		 }
	      Role role= new Role();
	   //   role.setRole(user.getRoles()+"");
	    //  role_Repository.save(role);
		  config_repo.save(user.getConfigurations());
		 
	     // String fileObjKeyName= "image_"+user.getId()+".jpg";
	      String destination_path="profile_pics/".concat(user.getProfile_image());
	      s3util.uploadSingleDocument(user.getConfigurations().getBucket_name(), destination_path, source_path);
	      URL url= s3client.getUrl(user.getConfigurations().getBucket_name(), destination_path);
	      user.setImage_url(url.toString());
	     /* encrypt user password to secure*/
	      //String originalInput = "test input";
	     // Base64 base64 = new Base64();
	     // String encodedString = new String(base64.encode(originalInput.getBytes()));
	     // user.setPassword(encodedString);
	     String encrypted_password = passwordEncoder.encode(user.getPassword());
	     user.setPassword(encrypted_password);
	     
	     //user.setRoles(user.getRoles());
	      //role_Repository.save(user.getRoles());
	      employeeRepository.save(user);
	     ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
	     URI  location = ServletUriComponentsBuilder.fromCurrentRequest().fromCurrentContextPath().path("user/userById/{id}").buildAndExpand(user.getId()).toUri();
	      MultiValueMap<String, String> headers = new HttpHeaders();
	     headers.add("clientid", user.getId()+"");
	     headers.add("uri", location.toString());
	     Response response = new Response(new Date(), "User created successfully", location.toString(), HttpStatus.CREATED+"");
	      return new ResponseEntity<Object>(response, headers, HttpStatus.CREATED);
           
    }
    
    
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails)
        throws ResourceNotFoundException {
      User user =
    		  employeeRepository
              .findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
      if(!org.springframework.util.StringUtils.isEmpty(userDetails.getEmail())) {
    	  user.setEmail(userDetails.getEmail());
      }
      if(!org.springframework.util.StringUtils.isEmpty(userDetails.getUserName())) {
    	  user.setUserName(userDetails.getUserName());
      }
       if(!org.springframework.util.StringUtils.isEmpty(userDetails.getBucket_name())) {
    	  user.setBucket_name(userDetails.getBucket_name());
       }
       if(!org.springframework.util.StringUtils.isEmpty(userDetails.getPassword())) {
     	  user.setPassword(userDetails.getBucket_name());
        }
       if(!org.springframework.util.StringUtils.isEmpty(userDetails.getImage_url())) {
     	  user.setImage_url(userDetails.getImage_url());
        }
       if(!org.springframework.util.StringUtils.isEmpty(userDetails.getProfile_image())) {
     	  user.setProfile_image(userDetails.getProfile_image());
        }
     
      final User updatedUser = employeeRepository.save(user);
      return ResponseEntity.ok(updatedUser);	
    }
    
    @DeleteMapping("/deleteUser/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws Exception {
      User user =
    		  employeeRepository
              .findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
      employeeRepository.delete(user);
      Map<String, Boolean> response = new HashMap<>();
      response.put("deleted", Boolean.TRUE);
      return response;
    }
    
    @GetMapping("/searchUser")
    public ResponseEntity<List<User>> searchForCars(@SearchSpec Specification<User> specs) {
        return new ResponseEntity<>(employeeRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    
}
