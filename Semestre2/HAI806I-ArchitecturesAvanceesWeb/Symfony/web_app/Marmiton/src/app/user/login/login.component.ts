import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: []
})


export class Login {
  http: HttpClient;
  router: Router;

  @ViewChild('email') mailElement:ElementRef;
  @ViewChild('password') passwordElement:ElementRef;
  @ViewChild('error') errorElement:ElementRef;

  constructor(private httpParam :HttpClient, private routerParam : Router){
    this.http = httpParam;
    this.mailElement = {} as ElementRef;
    this.passwordElement = {} as ElementRef;
    this.errorElement = {} as ElementRef;

    this.router = routerParam;
  }

  connect(){
    let email : string;
    let password : string;

    email = this.mailElement.nativeElement.value;
    password = this.passwordElement.nativeElement.value;

    if(email != "" && password != ""){
      this.http.get(environment.apiUrl + "/connection/" + email + "/" + password).subscribe((user : any) => {
          if(Object.keys(user).length === 0){
            this.errorUser();
          }else{
            localStorage.setItem("user", JSON.stringify(user));
            window.dispatchEvent( new Event('storage') )
            this.router.navigate(["/"]);
          }
      });
    }
  }

  errorUser(){
    this.errorElement.nativeElement.innerText = "Identifiant ou mot de passe erroné"; 
  }
}
