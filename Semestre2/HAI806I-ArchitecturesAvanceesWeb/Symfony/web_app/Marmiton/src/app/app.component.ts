import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Marmiton';
  user : any;
  router: Router;
  
  constructor(private routerParam : Router){
    this.user = null;
    this.router = routerParam;
  }

  ngOnInit(){
    this.connect();

    window.addEventListener("storage", () => {
      console.log("event")
      this.connect();
    });
  }

  connect(){
    let user = localStorage.getItem("user");

    if(user != null){
      this.user = JSON.parse(user);
    }
  }

  disconnect(){
    this.user = null;

    localStorage.removeItem("user");
    window.dispatchEvent( new Event('storage') )
    this.router.navigate(["/"]);
  }
}
