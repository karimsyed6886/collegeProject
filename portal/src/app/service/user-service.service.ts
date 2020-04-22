import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { user } from '../userlist/userlist.component';
import { API_URL } from '../app.constants';
import { user } from '../welcome/welcome.component';
import { Response } from '../registration/registration.component';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllTodos() {
    // let headers = new HttpHeaders({
    //   Authorization: sessionStorage.getItem('TOKEN')
    // })
    return this.http.get<user[]>(`${API_URL}/user/users`);
    //console.log("Execute Hello World Bean Service")
  }

  deleteUser( id){

    // let headers = new HttpHeaders({
    //   Authorization: sessionStorage.getItem('TOKEN')
    // })
    return this.http.delete(`${API_URL}/user/deleteUser/${id}`);
  }

  retrieveUserbyId( id) : any{

    // let headers = new HttpHeaders({
    //   Authorization: sessionStorage.getItem('TOKEN')
    // })
    return this.http.get<user>(`${API_URL}/userById/${id}`);
  }

  updateUser(id, user){
    return this.http.put(
          `${API_URL}/user/updateUser/${id}`
                , user);
  }

  createUser(user){
    return this.http.post<Response>(
              `${API_URL}/user/createUser`
                , user);
  }








  
}
