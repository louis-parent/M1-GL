import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Input, Output } from "@angular/core";
import { User } from "src/app/user/user";
import { environment } from "src/environments/environment";
import { Opinion } from "../opinion";

@Component({
	selector: 'opinion-item',
	templateUrl: './opinion-item.component.html',
	styleUrls: ['./opinion-item.component.css'],
	providers: []
})

export class OpinionItem{
	@Input()
	recipeId : String = "";
	@Input()
	email : String = ""

	@Output() changeRequest = new EventEmitter<any>();

	http : HttpClient;
	opinion : Opinion;

	condButton : boolean = false;
	condEditor : boolean = false;
	isEditMode : boolean = false;

	constructor(private httpParam : HttpClient){
		this.http = httpParam;
		this.opinion = new Opinion();
		this.isEditMode = false;
	}

	ngOnInit(){
		this.http.get(environment.apiUrl + "/opinion/" + this.email	+ "/"	+ this.recipeId).subscribe((opinion : any) => {
			this.opinion = new Opinion(opinion.email, opinion.nickname, opinion.recipedId, opinion.comments, parseInt(opinion.score), opinion.date);
			this.initCond();
		});
	}

	initCond(){
		try
		{
			let user : User = JSON.parse(localStorage.getItem("user") || "" );
			let userConnected = user != null;
			let isUserOpinion = this.email == user.email;

			this.condButton = userConnected && isUserOpinion && !this.isEditMode;
		}
		catch
		{
			this.condButton = false;
		}
	}

	editOpinion(){
		this.isEditMode = true;
	}

	changeItem(){
		this.changeRequest.emit();
	}
	
	dateLocaleFormat(date: string) {
		return new Date(date).toLocaleDateString();
	}
}
