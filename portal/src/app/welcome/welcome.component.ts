import { WelcomeDataService } from './../service/data/welcome-data.service';
import { ActivatedRoute } from '@angular/router';
//package com.in28minutes.springboot.web;

//import org.springframework.boot.SpringApplication;
import { Component, OnInit } from '@angular/core';//'./app.component';
import { UserServiceService } from '../service/user-service.service';
//import { HttpHeaders } from '@angular/common/http';
//import { UserlistComponent, user } from '../userlist/userlist.component';
//import { AppComponent } from '../app.component';

//@ComponentScan(
//      value = "com.in28minutes.springboot.web")
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

//public class SpringBootFirstWebApplication implements SomeInterface{
export class WelcomeComponent implements OnInit {

  message = 'Some Welcome Message'
  welcomeMessageFromService:string
  name = ''
  //String message = "Some Welcome Message"
  
  //public SpringBootFirstWebApplication() {	

  //ActivatedRoute
  constructor(
    private route:ActivatedRoute,
    private service:WelcomeDataService,
    private userService: UserServiceService
    ) {

  }

  // void init() {
  ngOnInit(){
    //COMPILATION ERROR this.message = 5
    //console.log(this.message)
    // console.log(this.route.snapshot.params['name'])
   // this.name = this.route.snapshot.params['name'];
    
  }

  getWelcomeMessage( )  : any{ 
    //console.log(this.service.executeHelloWorldBeanService());
    
   // this.service.executeHelloWorldBeanService().subscribe(
     // response => this.handleSuccessfulResponse(response),
     // error => this.handleErrorResponse(error)
    //);
    
    //console.log('last line of getWelcomeMessage')

    //console.log("get welcome message");

    //this.userlistcomp.getUserbyId(183);
  
  }

  getWelcomeMessageWithParameter() {
    //console.log(this.service.executeHelloWorldBeanService());
   
   // this.service.executeHelloWorldServiceWithPathVariable(this.name).subscribe(
    this.service.getuserbyId().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
    
    //console.log('last line of getWelcomeMessage')

    //console.log("get welcome message");
  }


  handleSuccessfulResponse(response){
  console.log(response)
    //console.log(response);
    //console.log(response.message);
  }

  handleErrorResponse(error) {
    //console.log(error);
    //console.log(error.error);
    //console.log(error.error.message);
    this.welcomeMessageFromService = error.error.message
  }

  getUserbyId( ) {
   

    this.userService.retrieveUserbyId(183).subscribe(
      data=>{
        console.log("we get into success get user by id")
        console.log(data)
        console.log(data.email)
        return data
    
    
      },
      error=>{
         console.log("got an error")
        console.log(error)
        return error
      }
    
    );
    
    
      }

}

  
export class user

 {
  static password(password: any): any {
    throw new Error("Method not implemented.");
  }
  id: BigInteger
  userName: String
  password: String
  email: String
  rollName:String
  rollId: String
  creationDateTime:String
  updateTimestamp: String
  profile_image: String
  image_url: String
  softDelete: String
  secretKey: String
  access_key: String
  aws_region: String
  bucket_name: String
  confirm_password: String
  configurations: {
      user_id: BigInteger
      secretKey: String
      access_key: String
      aws_region: String
      bucket_name: String
      creationDateTime: String
      updateTimestamp: String
      id: BigInteger
  }
}

