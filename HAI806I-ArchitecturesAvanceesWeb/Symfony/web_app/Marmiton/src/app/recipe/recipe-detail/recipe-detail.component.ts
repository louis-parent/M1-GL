import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute} from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css'],
  providers: []
})

export class RecipeDetail {
   http: HttpClient;
   recipe: any;
   person: number;
   opinions : any;
   recipeId : String;
   currencyFormater: Intl.NumberFormat;
   quantityFormater: Intl.NumberFormat;

   constructor(private httpParam :HttpClient, private route: ActivatedRoute){
    this.http = httpParam;
    this.recipe = {};
    this.person = 1;
    this.recipeId = "";
    this.currencyFormater = new Intl.NumberFormat('fr-FR', {
		currency: 'EUR',
		style: 'currency',
	});
	this.quantityFormater = new Intl.NumberFormat('fr-FR', {
		maximumSignificantDigits: 3
	});
  }

   ngOnInit(){
    this.route.queryParams.subscribe(params => {
        let recipeIdTemp = this.route.snapshot.paramMap.get('recipeId');
        if(recipeIdTemp != null){
          this.recipeId = recipeIdTemp;
        }

        this.http.get(environment.apiUrl + "/recipe/" + this.recipeId).subscribe((recipe : any) => {
            this.recipe = recipe;
            this.person = recipe.quantityPerson;
        });
    });
   }
   
   incrPerson(){
   	this.person++;
   }
   
   decrPerson(){
   	this.person--;
   }
   
   getQuantityForPerson(quantity : number) {
   	return (quantity / this.recipe.quantityPerson) * this.person;
   }
}
