import { HttpClient } from "@angular/common/http";
import { Component, Input } from "@angular/core";
import { User } from "src/app/user/user";
import { environment } from "src/environments/environment";

@Component({
  selector: 'opinion-list',
  templateUrl: './opinion-list.component.html',
  styleUrls: ['./opinion-list.component.css'],
  providers: []
})

export class OpinionList{
  opinions : any;
  http: HttpClient;
  @Input()
  recipeId : String = "";
  currentUser : User = new User();
  userConnected : boolean;

  alreadyCommented : boolean;

  constructor(private httpParam : HttpClient){
    this.opinions = new Array();
    this.http = httpParam;
    this.alreadyCommented = false;
    this.userConnected = false;
  }

  ngOnInit(){
    this.loadListOpinion();    

    let user = localStorage.getItem("user");
    
    if(user != null){
      this.currentUser = <User>JSON.parse(user);
      this.userConnected = true;
    }
  }

  loadListOpinion(){
    this.http.get(environment.apiUrl + "/opinions/" + this.recipeId).subscribe((opinions : any) => {
      this.opinions = opinions;
      
      for(let opinion of this.opinions){
        if(opinion.email == this.currentUser.email){
          this.alreadyCommented = true;
        }
      }
    });
  }
}
