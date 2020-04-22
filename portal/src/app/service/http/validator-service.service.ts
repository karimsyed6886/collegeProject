import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidatorServiceService {
  user: any;
  //validation: any
  passwrd_validation : Object[]
  user_regsiter_validation : Object[]

  constructor( ) { }
   

  password_validation(user):any{
    this.passwrd_validation[0]= false
    this.passwrd_validation[1]= false
    if(user.password!= user.confirm_password){
      this.passwrd_validation[0]= true
     // this.passwrd_validation[1]=" password and confirm password should be same"
      this.user=user
      console.log("**** both passwrods are not same***")
      return true
    }
         var passwordValidator = require('password-validator');
        // Create a schema
          var schema = new passwordValidator();
         // Add properties to it
schema
.is().min(8)                                    // Minimum length 8
.is().max(100)                                  // Maximum length 100
.has().uppercase()                              // Must have uppercase letters
.has().lowercase()                              // Must have lowercase letters
.has().digits()                                 // Must have digits
.has().not().spaces()                           // Should not have spaces
.is().not().oneOf(['Passw0rd', 'Password123']); // Blacklist these values

// Validate against a password string
console.log(schema.validate(user.password));
// Get a full list of rules which failed
let validation_paswrd=schema.validate(user.password, { list: true })
console.log(validation_paswrd)
if(validation_paswrd!=0 ){
 this.passwrd_validation[1]= true
  //this.passwrd_validation[3]=" password should contain atleast one char,number,cap& samll char"
  this.user=user
  console.log("** password validation got failed ****")
  return true
 //return this.validation
}else{
 this.passwrd_validation[0]= false
  this.passwrd_validation[1]= false
 return false
}
  }








}


