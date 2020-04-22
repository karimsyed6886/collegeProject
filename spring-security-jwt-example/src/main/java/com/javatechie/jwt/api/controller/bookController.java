package com.javatechie.jwt.api.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import com.javatechie.jwt.api.entity.AuthRequest;
import com.javatechie.jwt.api.entity.Book;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.exception.ResourceNotFoundException;
import com.javatechie.jwt.api.repository.Book_repository;
import com.javatechie.jwt.api.repository.Config_repository;
import com.javatechie.jwt.api.repository.UserRepository;
import com.javatechie.jwt.api.util.JwtUtil;
import com.javatechie.jwt.api.util.S3util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin
@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/book")
public class bookController {
	@Autowired
	private UserRepository employeeRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private S3util s3util;
    @Autowired
    private Config_repository config_repo;
    @Autowired
    private Book_repository book_repo;
    
    
    
    
    

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }
    
    @GetMapping("/hello")
    public String welcomehello() {
        return "hello world";
    }
    

   
    
    @GetMapping("/getbookById/{id}")
    public ResponseEntity<Optional<Book>> getUserById(@PathVariable(value = "id") int bookId)
        throws ResourceNotFoundException {
    	
    	Optional<Book> book=book_repo.findById(bookId);
    	if(book!=null){
    		
    		return ResponseEntity.notFound().build();
    	}else {
        //User employee = employeeRepository.findById(employeeId)
         // .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(book);
    	}
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
      return employeeRepository.findAll();
    }
    @Transactional 
    @SuppressWarnings("deprecation")
	@PostMapping("/createBook")
    public Book createBook(@Valid @RequestBody Book book) {
    	//get user details by user id
    	User user=employeeRepository.getOne(book.getCreatedByuserid());
    	//create a connectiion to the aws s3
    	//String destination_path="profile_pics/image002.jpg";
    	String source_path="/media/jwt/spring-security-jwt-example/src/main/pdf1.pdf";
    	AmazonS3 s3client= s3util.createClientconnection(user.getConfigurations().getAccess_key(), user.getConfigurations().getSecretKey());
       // boolean bucketCheck=	s3util.createBucket(user.getBucket_name());
        //checking s3 bucket is already exisit or not
	
		  if(s3client.doesBucketExist(user.getConfigurations().getBucket_name())) {
			  
		  System.out.println("*********** bucket is already existed ***********");
		 }else {
			 System.out.println(" ******** new bucket is created ************");
			 s3client.createBucket(user.getConfigurations().getBucket_name());
		 }
		 // config_repo.save(user.getConfigurations());
		 
	     // String fileObjKeyName= "image_"+user.getId()+".jpg";
	      String destination_path="books/".concat(book.getBook_name());
	      s3util.uploadSingleDocument(user.getConfigurations().getBucket_name(), destination_path, source_path);
	      URL url= s3client.getUrl(user.getConfigurations().getBucket_name(), destination_path);
	      //user.setImage_url(url.toString());
	      book.setBook_url(url.toString());
	      return book_repo.save(book);
	      
          
    }
    
    /**
     * Update user response entity.
     *
     * @param userId the user id
     * @param userDetails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
   
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails)
        throws ResourceNotFoundException {
      User user =
    		  employeeRepository
              .findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
      if(org.springframework.util.StringUtils.isEmpty(user.getEmail())) {
    	  user.setEmail(userDetails.getEmail());
      }
      if(org.springframework.util.StringUtils.isEmpty(user.getUserName())) {
    	  user.setUserName(userDetails.getEmail());
      }
       if(org.springframework.util.StringUtils.isEmpty(user.getBucket_name())) {
    	  user.setBucket_name(user.getBucket_name());
       }
       if(org.springframework.util.StringUtils.isEmpty(user.getPassword())) {
     	  user.setPassword(user.getBucket_name());
        }
       if(org.springframework.util.StringUtils.isEmpty(user.getImage_url())) {
     	  user.setImage_url(user.getImage_url());
        }
       if(org.springframework.util.StringUtils.isEmpty(user.getProfile_image())) {
     	  user.setProfile_image(user.getProfile_image());
        }
     
      final User updatedUser = employeeRepository.save(user);
      return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/user/{id}")
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

    
}
