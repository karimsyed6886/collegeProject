import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { user } from '../welcome/welcome.component';
import { UserServiceService } from '../service/user-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ValidatorServiceService } from '../service/http/validator-service.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user : user
  succes_response : Response
   roles:Roles[]
   stuentcheck : boolean
   teachercheck :  boolean
  

  constructor(
    private userService: UserServiceService,
    private route: ActivatedRoute,
    private router: Router, 
    private validator_service : ValidatorServiceService,
    

  ) { }

  ngOnInit() {
   this.succes_response= new Response()
   this.user= new user()
   this.validator_service.user_regsiter_validation=[false ," "]
   this.validator_service.passwrd_validation= [false, false]  
   console.log(" *** registration page got called *****")

  }

  saveUser(user){
     //=== ==
      this.userService.createUser( user)
          .subscribe (
            data => {
              console.log(" *** success register response ******")
              console.log(data)
              this.succes_response=data
        
            },
            error=>{
               console.log(error)

            }
          );
    
  }


  validatePassword(user) : any{
   return  this.validator_service.password_validation(user)
  }


    saveRegisterUser(user){
  
   let roles  =[{ "role" :"STUDENT" }];
    
    console.log("**** user registered succesfully *********")
    console.log(user)
  
    let validation=false;
    validation= this.validatePassword(user)
  if(!validation){
   user=  {
      
      "userName": user.userName,
      "password": user.password,
      "email": user.email,
      "rollName": "student",
      "rollId": 0,
      "creationDateTime": "2020-04-04T11:00:17.000+0000",
      "updateTimestamp": "2020-04-04T11:00:17.000+0000",
      "profile_image": "image002.jpg",
      "image_url": "https://ahmad457.s3.us-east-2.amazonaws.com/profile_pics/image002.jpg",
      "softDelete": false,
      "secretKey": null,
      "access_key": null,
      "aws_region": null,
      "bucket_name": null,
      "roles":[{ "role" :"STUDENT" }],
      "configurations": {
          "user_id": 0,
          "secretKey": "lUJtrGqFAMGbKbRQITJBpLlerLD5HZfSICHYnkEO",
          "access_key": "AKIATUHGGMI4IWVPDCP3",
          "aws_region": "us-east-2",
          "bucket_name": "ahmad457",
          "creationDateTime": "2020-04-04T11:00:17.000+0000",
          "updateTimestamp": "2020-04-04T11:00:17.000+0000",
          
      }
  }
  
  this.userService.createUser( user)
  .subscribe (
    data => {
      console.log(" *** success register response ******")
      console.log(data)
      this.succes_response=data
      console.log(" print success response")
      console.log(this.succes_response)
      //response=this.succes_response
      console.log(this.succes_response.response_code)
      if(this.succes_response.response_code=="201 CREATED"){
        this.validator_service.user_regsiter_validation[0]=true
        console.log("** user created sucessully***")
  
        
      }
      //return 
    //  this.succes_response=data       
     // return object
      //this.router.navigate(['todos'])
    },
    error=>{
       console.log(error)

    }
  );

  }else{
    console.log("*******user account creation failed********")
  }


  }
  studetVisibility(e){
    console.log(e.target.stuentcheck) ;
    console.log(this.stuentcheck)
    this.stuentcheck=e.target.checked
  }
  teacherVisibility(e){
    console.log(e.target.stuentcheck) ;
    console.log(this.stuentcheck)
    this.stuentcheck=e.target.checked
  }
  



}
export class Response{
         date : String
	    message :String
      details : String
       response_code : String
    constructor(){

    }



}
export class Roles{

  role : String
}

