import { API_URL } from './../app.constants';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { error } from 'protractor';
import { user } from '../welcome/welcome.component';

export const TOKEN = ''
export const AUTHENTICATED_USER = ''

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {
     
  constructor(private http: HttpClient) { }

  executeJWTAuthenticationService(userName, password) {
    
    return this.http.post<any>(
      `${API_URL}/authenticate`,{
        userName,
        password
      });//.pipe(
      //   map(
      //     data => {
      //       sessionStorage.setItem('AUTHENTICATED_USER', userName);
      //       sessionStorage.setItem('TOKEN', `Bearer ${data.auth_token}`);
      //       return data;
      //     },
      //     error=>{
      //       console.log("****Authentication failed *********")

      //     }
      //   )
      // );
    //console.log("Execute Hello World Bean Service")
  }


  // executeAuthenticationService(username, password) {
    
  //   let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

  //   let headers = new HttpHeaders({
  //       Authorization: basicAuthHeaderString
  //     })

  //   return this.http.get<AuthenticationBean>(
  //     `${API_URL}/basicauth`,
  //     {headers}).pipe(
  //       map(
  //         data => {
  //           sessionStorage.setItem(AUTHENTICATED_USER, username);
  //           sessionStorage.setItem(TOKEN, data.auth_token);
  //           return data;
  //         }
  //       )
  //     );
  //   //console.log("Execute Hello World Bean Service")
  // }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken() {
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
  }


  isUserLoggedIn() {
    let user = sessionStorage.getItem('TOKEN')
    return !(user === null)
  }


  



  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
  }

}

export class AuthenticationBean{
  constructor(public auth_token:string) { }
}