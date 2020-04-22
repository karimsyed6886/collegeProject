import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';
import { error } from 'protractor';
import { user } from '../welcome/welcome.component';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute, RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})

 







export class UserlistComponent implements OnInit {
  title = 'Angular Search Using ng2-search-filter';
  searchText;
  items = [];
  pageOfItems: Array<any>;
      users:  user[];


  constructor(
              public userservice :UserServiceService,
              private router: Router
             ) { }

  ngOnInit() {
      // an example array of 150 items to be paged
     this.userservice.retrieveAllTodos().subscribe(
     data=>{
        console.log(data)
        this.users=data;
     },
     error=>{
        console.log(error)
     }

     );
      this.items = Array(150).fill(0).map((x, i) => ({ id: (i + 1), name: `Item ${i + 1}`}));
  }

  onChangePage(pageOfItems: Array<any>) {
      // update current page of items
      this.pageOfItems = pageOfItems;
  }
   retriveAllusers(){
    this.userservice.retrieveAllTodos().subscribe(
      data=>{
         console.log(data)
         this.users=data;
      },
      error=>{
         console.log(error)
      }
 
      );



   }

  

  addUser () {
    this.router.navigate(['todos',-1])
  }

  updateUser(id){
    console.log(`update ${id}`)
    this.router.navigate(['updateUser',id])

  }

  deleteUser(id){
    this.userservice.deleteUser(id).subscribe(
    success=>{
      console.log(success)
      this.retriveAllusers();
      console.log(" ***** user deleted succesfully ******")
    
    },
    error=>{
      console.log(error)
      console.log(" ***** user deletion got failed****")


    }


    );

  }

 

  


}



  



