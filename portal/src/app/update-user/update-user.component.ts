import { Component, OnInit } from '@angular/core';
import { user } from '../welcome/welcome.component';
import { TodoDataService } from '../service/data/todo-data.service';
import { ActivatedRoute, Router } from '@angular/router'
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
id:number
  user: user

  constructor(
    private userService: UserServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    
    this.id = this.route.snapshot.params['id'];
    
   this. user= new user();
    
    if(this.id!=-1) {
      this.userService.retrieveUserbyId( this.id)
          .subscribe (
            data => {
              console.log(data )
              this.user=data
            },
            error=>{
              console.log(error)
              console.log("***** error we got *********")
            }

          )
    }
  }

  saveTodo() {
    if(this.id == -1) { //=== ==
      this.userService.createUser( this.user)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['todos'])
            }
          )
    } else {
      this.userService.updateUser(this.id, this.user)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['userlist'])
            }
          )
    }
  }

  checkpass(){
    console.log(" check pass method got called ")
  }

}
