package com.javatechie.jwt.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.jwt.api.entity.AuthRequest;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.exception.ResourceNotFoundException;
import com.javatechie.jwt.api.repository.UserRepository;
import com.javatechie.jwt.api.util.JwtUtil;
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class WelcomeController {
	@Autowired
	private UserRepository employeeRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
   
   
   
    
    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }
    
    @GetMapping("/hello")
    public String welcomehello() {
        return "hello world";
    }
    

    @PostMapping("/authenticate")
    public Map<String,String>  generateToken(@RequestBody AuthRequest authRequest) throws Exception {
       Map<String,String> token = new HashMap<String,String>();
    	try {
        	
        	System.out.println("****** user name***"+ authRequest.getUserName() +" and password :"+authRequest.getPassword());
            
        	// decrypt the password got from  user
        	
        	
        	authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
          System.out.println(jwtUtil.generateToken(authRequest.getUserName()));
        String token1= jwtUtil.generateToken(authRequest.getUserName());
       token.put("auth_token", token1);
       return token;
    }
    
    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int employeeId)
        throws ResourceNotFoundException {
        User employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
      return employeeRepository.findAll();
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
      user.setEmail(userDetails.getEmail());
      
      user.setUserName(userDetails.getUserName());
     
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
