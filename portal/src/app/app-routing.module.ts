import { TodoComponent } from './todo/todo.component';
import { RouteGuardService } from './service/route-guard.service';
import { LogoutComponent } from './logout/logout.component';
import { ListTodosComponent } from './list-todos/list-todos.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { SampleRoutegardService } from './service/sample-routegard.service';
import { UserlistComponent } from './userlist/userlist.component';
import { RegistrationComponent } from './registration/registration.component';
import { UpdateUserComponent } from './update-user/update-user.component';

// welcome 
const routes: Routes = [
  { path: '', component: LoginComponent  },//canActivate, RouteGuardService
  { path: 'login', component: LoginComponent },
  //{ path: 'home', component: WelcomeComponent, canActivate:[SampleRoutegardService]},
  { path: 'todos', component: ListTodosComponent, canActivate:[RouteGuardService] },
  { path: 'logout', component: LogoutComponent, canActivate:[RouteGuardService] },
  { path: 'todos/:id', component: TodoComponent, canActivate:[RouteGuardService] },
  { path: 'home', component: HomeComponent,  },
  { path: 'userlist', component: UserlistComponent, canActivate:[RouteGuardService] },
  { path: 'register', component: RegistrationComponent, },  
  { path: 'updateUser/:id', component: UpdateUserComponent, canActivate:[RouteGuardService] },  
  { path: 'registration', component: UpdateUserComponent },                                     
  

  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
