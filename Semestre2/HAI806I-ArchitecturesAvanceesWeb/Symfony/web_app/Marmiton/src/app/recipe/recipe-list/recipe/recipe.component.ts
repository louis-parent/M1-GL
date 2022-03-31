import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css'],
  providers: []
})


export class RecipeItem {
   @Input()
   idRecipe = 0;
   http: HttpClient;
   recipe: any;

   constructor(private httpParam :HttpClient){
    this.http = httpParam;
    this.recipe = {};
  }

   ngOnInit(){
    this.http.get(environment.apiUrl + "/recipe/" + this.idRecipe).subscribe((recipe : any) => {
      this.recipe = recipe
    });
   }
}
