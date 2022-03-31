import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { ApiResponse } from 'src/app/utils/ApiResponse';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: []
})


export class Register {
  http: HttpClient;
  router: Router;

  @ViewChild('firstname') firstNameElement:ElementRef;
  @ViewChild('lastname') lastNameElement:ElementRef;
  @ViewChild('nickname') nicknameElement:ElementRef;
  @ViewChild('email') mailElement:ElementRef;
  @ViewChild('password') passwordElement:ElementRef;
  @ViewChild('passwordconfirm') passwordConfirmElement:ElementRef;

  @ViewChild('error') errorElement:ElementRef;

  constructor(private httpParam :HttpClient, private routerParam : Router){
    this.http = httpParam;

    this.firstNameElement = {} as ElementRef;
    this.lastNameElement = {} as ElementRef;
    this.nicknameElement = {} as ElementRef;
    this.mailElement = {} as ElementRef;
    this.passwordElement = {} as ElementRef;
    this.passwordConfirmElement = {} as ElementRef;
    this.errorElement = {} as ElementRef;

    this.router = routerParam;
  }

  register(){
    let user = new User();

    user.firstName = this.firstNameElement.nativeElement.value;
    user.lastName = this.lastNameElement.nativeElement.value;
    user.nickname = this.nicknameElement.nativeElement.value;
    user.email = this.mailElement.nativeElement.value;
    user.password = this.passwordElement.nativeElement.value;

    if(user.isComplete()){
      if(this.passwordElement.nativeElement.value == this.passwordConfirmElement.nativeElement.value){
        this.http.post<ApiResponse>(environment.apiUrl + "/register", user).subscribe((data : ApiResponse) => {
          if(data.success){
            this.router.navigate(["/"]);
          }else{
            this.errorUser(data.message);
          }

        });
      }else{
        this.errorUser("La confirmation de mot de passe n'est pas indentique");
      }
    }else{
      this.errorUser("Tout les champs sont requis");
    }
  }

  errorUser(error : string){
    this.errorElement.nativeElement.innerText = error; 
  }
 
}
