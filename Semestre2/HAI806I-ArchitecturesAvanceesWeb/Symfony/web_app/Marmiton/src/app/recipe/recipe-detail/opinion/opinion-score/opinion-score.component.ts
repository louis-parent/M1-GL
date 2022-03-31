import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Input, Output } from "@angular/core";
import { User } from "src/app/user/user";
import { environment } from "src/environments/environment";
import { Opinion } from "../opinion";

@Component({
	selector: 'opinion-score',
	templateUrl: './opinion-score.component.html',
	styleUrls: ['./opinion-score.component.css'],
	providers: []
})

export class OpinionScore{
	@Input()
	score : number;
	

	constructor(){
		this.score = 0;
	}

	rangeObtained(){
		if(!Number.isNaN(this.score) && this.score != undefined)
		{
			return new Array(Math.floor(this.score));
		}
		else
		{
			return new Array(0);
		}
	}
	
	rangeMissing() {
		if(!Number.isNaN(this.score) && this.score != undefined)
		{
			return new Array(Math.ceil(5 - this.score));
		}
		else
		{
			return new Array(5);
		}
	}
}
