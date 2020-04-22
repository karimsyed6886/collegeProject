import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { user } from 'src/app/welcome/welcome.component';

export class HelloWorldBean {
  constructor(public message:string){ }
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(
    private http:HttpClient
  ) { }
  basicAuthHeaderString =''
  executeHelloWorldBeanService() {
    return this.http.get<HelloWorldBean>('http://localhost:8080/hello-world-bean');
    //console.log("Execute Hello World Bean Service")
  }
  //http://localhost:8080/hello-world/path-variable/in28minutes

  executeHelloWorldServiceWithPathVariable(name) {
    // let basicAuthHeaderString = this.createBasicAuthenticationHttpHeader();

    // 
    return this.http.get<HelloWorldBean> (
      `http://localhost:8080/hello-world/path-variable/${name}`,
      //{headers}
      );
    //console.log("Execute Hello World Bean Service")
  }

  // createBasicAuthenticationHttpHeader() {
  //   let username = 'in28minutes'
  //   let password = 'dummy'
  //   let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
  //   return basicAuthHeaderString;
  // }
  //Access to XMLHttpRequest at 
  //'http://localhost:8080/hello-world/path-variable/in28minutes' 
  //from origin 'http://localhost:4200' has been blocked by CORS policy: 
  //No 'Access-Control-Allow-Origin' header is present on the requested resource.

  //Access to XMLHttpRequest at 'http://localhost:8080/hello-world/path-variable/in28minutes' from origin 'http://localhost:4200' 
  //has been blocked by CORS policy: 
  //Response to preflight request doesn't pass 
  //access control check: It does not have HTTP ok statusca

  getuserbyId(){ 
    let headers = new HttpHeaders({
          Authorization: sessionStorage.getItem('TOKEN')
        })
  
    return this.http.get<user> (
      `http://localhost:9192/userById/183`,
      {headers}
      );
  }

}
