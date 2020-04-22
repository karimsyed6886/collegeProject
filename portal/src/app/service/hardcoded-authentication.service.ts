import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }




  logout(){
    sessionStorage.removeItem('TOKEN')
    sessionStorage.removeItem('AUTHENTICATED_USER')  

  }

}
