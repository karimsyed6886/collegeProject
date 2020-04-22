import { BasicAuthenticationService } from './../basic-authentication.service';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  constructor(
    private basicAuthenticationService : BasicAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){

    let basicAuthHeaderString = sessionStorage.getItem('TOKEN')
    let username = sessionStorage.getItem('AUTHENTICATED_USER')

    if(basicAuthHeaderString && username) { 

      

      const headers = new HttpHeaders({

         
        Authorization: basicAuthHeaderString,
        Origin       : 'http://localhost:4200/',
        'Access-Control-Allow-Origin' :"*"
        
      });

      request = request.clone({headers}) 
   }
    return next.handle(request);//sending modified request.
  }


}
