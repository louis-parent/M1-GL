import { HttpClient } from "@angular/common/http";
import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from "@angular/core";
import { User } from "src/app/user/user";
import { ApiResponse } from "src/app/utils/ApiResponse";
import { environment } from "src/environments/environment";
import { Opinion } from "../opinion";

@Component({
    selector: 'opinion-editor',
    templateUrl: './opinion-editor.component.html',
    styleUrls: ['./opinion-editor.component.css'],
    providers: []
  })

export class OpinionEditor{
    http : HttpClient;
    @Input()
    recipeId : String = new String();
    @Input()
    newOpinion : boolean = false;;

    user : User;

    @ViewChild('score') score:ElementRef;
    @ViewChild('comments') comments:ElementRef;
    @ViewChild('error') error:ElementRef;

    @Output() changeRequest = new EventEmitter<any>();

    constructor(private httpParam : HttpClient){
        this.http = httpParam;

        this.user = new User();
        this.newOpinion = false;

        this.score = {} as ElementRef;
        this.comments = {} as ElementRef;
        this.error = {} as ElementRef;
    }

    ngOnInit(){
      this.user = JSON.parse(localStorage.getItem("user")||"");
      
      if(!this.newOpinion)
      {
		  this.selectOpinion();
      }
    }

    selectOpinion(){
      this.http.get<Opinion>(environment.apiUrl + "/opinion/" + this.user.email + "/" + this.recipeId).subscribe((data : Opinion) => {
        if(data != null){
          this.newOpinion = false;
          this.fillOpinion(data);
        }else{
          this.newOpinion = true;
        }
      });
    }

    fillOpinion(data: Opinion) {
      this.score.nativeElement.value = data.score;
      this.comments.nativeElement.value = data.comments;
    }
    
    sendOpinion() {
    	if(parseInt(this.score.nativeElement.value) < 0 || parseInt(this.score.nativeElement.value) > 5)
    	{
    		this.displayError("La note doit être comprise entre 0 et 5 inclus");
    	}
    	else
    	{
			if(this.newOpinion)
			{
				this.addOpinion();
			}
			else
			{
				this.modifyOpinion();
			}
    	}
    
    }

    modifyOpinion(){
      let recipeId = this.recipeId;
      let email = JSON.parse(localStorage.getItem("user")||"").email;
      let score = this.score.nativeElement.value;
      let comments = this.comments.nativeElement.value;
      let date = this.getCurrentDate();

      this.http.put<ApiResponse>(environment.apiUrl + "/opinion", {
        recipeId:recipeId,
        email:email,
        score:score,
        comments:comments,
        date:date
      }).subscribe((data : ApiResponse) => {
        if(data.success){
          this.notifyParent();
        }else{
          this.displayError(data.message);
        }

      });
    }

    addOpinion(){
      let recipeId = this.recipeId;
      let email = JSON.parse(localStorage.getItem("user")||"").email;
      let score = this.score.nativeElement.value;
      let comments = this.comments.nativeElement.value;
      let date = this.getCurrentDate();

      this.http.post<ApiResponse>(environment.apiUrl + "/opinion", {
        recipeId:recipeId,
        email:email,
        score:score,
        comments:comments,
        date:date
      }).subscribe((data : ApiResponse) => {
        console.log(data);
        if(data.success){
          this.notifyParent();
        }else{
          this.displayError(data.message);
        }
      });
    }

    notifyParent(){
      this.changeRequest.emit();
    }

    getCurrentDate(){
      let date = new Date();
      let str = date.toISOString().split("T")[0];

      return str;
    }

    displayError(msgError : String){
      this.error.nativeElement.innerText = msgError;
    }

}
