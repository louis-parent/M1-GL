import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'recipes-list',
  templateUrl: './recipes-list.component.html',
  styleUrls: ['./recipes-list.component.css']
})
export class RecipesList {
  http :HttpClient;
  recipesId : number[]
  route : ActivatedRoute;

  constructor(private httpParam :HttpClient, private routeParam : ActivatedRoute){
    this.http = httpParam;
    this.recipesId = new Array();
    this.route = routeParam;
  }

  ngOnInit(){
    this.route.queryParams.subscribe(params => {
      if(Object.keys(params).length > 0){
        let name = params['name'] || "undefined";
        let author = params['author'] || "undefined";
        let difficulty = params['difficulty'] || "undefined";
        let ingredient = params['ingredient'] || "undefined";
        let price = params['price'] || "undefined";

        this.http.get(environment.apiUrl + "/recipes/" + name + "/" + author+ "/" + difficulty+ "/" + ingredient+ "/" + price).subscribe((recipes : any) => {
          this.recipesId = recipes;
        });
      }else{
        this.http.get(environment.apiUrl + "/recipes/").subscribe((recipes : any) => {
          this.recipesId = recipes;
        });
      }      
    })    
  }
}
