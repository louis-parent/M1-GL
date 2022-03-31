import { Component, ElementRef, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../../user/user';
import { Ingredient } from '../ingredient/Ingredient';
import { ApiResponse } from 'src/app/utils/ApiResponse';
import { Router } from '@angular/router';

@Component({
    selector: 'recipe-creation',
    templateUrl: './recipe-creation.component.html',
    styleUrls: ['./recipe-creation.component.css']
})

export class RecipeCreation{
    http :HttpClient;
    user : User;
    router: Router;

    @ViewChild('title') title:ElementRef;
    @ViewChild('cookingMode') cookingMode:ElementRef;
    @ViewChild('quantityPerson') quantityPerson:ElementRef;
    @ViewChild('difficulty') difficulty:ElementRef;
    @ViewChild('error') error:ElementRef;

    @ViewChildren('nameIngredient') nameIngredientRefs: QueryList<ElementRef>
    @ViewChildren('quantityIngredient') quantityIngredientRefs: QueryList<ElementRef>
    @ViewChildren('unitIngredient') unitIngredientRefs: QueryList<ElementRef>
    @ViewChildren('priceIngredient') priceIngredientRefs: QueryList<ElementRef>

    ingredients : Array<Ingredient>;

    @ViewChildren('step') refSteps: QueryList<ElementRef>
    steps : Array<String>;

    constructor(private httpParam :HttpClient, private routerParam : Router){
        this.http = httpParam;
        this.user = JSON.parse(localStorage.getItem("user") || "");

        this.title = {} as ElementRef;
        this.cookingMode = {} as ElementRef;
        this.quantityPerson = {} as ElementRef;
        this.error = {} as ElementRef;
        this.difficulty = {} as ElementRef;

        this.refSteps = new QueryList<ElementRef>();

        this.nameIngredientRefs = new QueryList<ElementRef>();
        this.quantityIngredientRefs = new QueryList<ElementRef>();
        this.unitIngredientRefs = new QueryList<ElementRef>();
        this.priceIngredientRefs = new QueryList<ElementRef>();

        this.ingredients = new Array();
        this.steps = new Array();

        this.router = routerParam;
    }

    ngOnInit(){
        
    }

    addRecipe(){
        let nameRecipeValue = this.title.nativeElement.value;
        let cookingModeValue = this.cookingMode.nativeElement.value;
        let quantityPersonValue = parseInt(this.quantityPerson.nativeElement.value);
        let difficultyValue = parseInt(this.difficulty.nativeElement.value);

        let tempIngredient = new Array<Ingredient>();
        let tempSteps = new Array<String>();

        for(let i = 0; i < this.ingredients.length; i++){
            let name = this.nameIngredientRefs.get(i)?.nativeElement?.value || "";
            let quantity = parseFloat(this.quantityIngredientRefs.get(i)?.nativeElement?.value || 0);
            let unit = this.unitIngredientRefs.get(i)?.nativeElement?.value || "unit";
            let price = parseFloat(this.priceIngredientRefs.get(i)?.nativeElement?.value) || 0;

            if(name !== "" && quantity !== 0){
                tempIngredient.push(new Ingredient(name, quantity, unit, price));
            }
        }

        for(let i = 0; i < this.steps.length; i++){
            let step = this.refSteps.get(i)?.nativeElement?.value || "";

            if(step !== ""){
                tempSteps.push(step);
            }
        }

        this.steps = tempSteps;
        this.ingredients = tempIngredient;

        this.http.post<ApiResponse>(environment.apiUrl + "/recipe", {
            email:this.user.email,
            name:nameRecipeValue,
            difficulty:difficultyValue,
            ingredients:this.ingredients,
            cookingMode: cookingModeValue,
            quantityPerson: quantityPersonValue,
            steps: this.steps

        }).subscribe((data : ApiResponse) => {
            if(data.success){
              this.router.navigate(["/"]);
            }else{
              this.errorUser(data.message);
            }
  
          });
    }

    addIngredient(){
        this.ingredients.push(new Ingredient("", 0, "unit", 0));
    }

    removeIngredient(ingredient : Ingredient){
        this.ingredients.splice(this.ingredients.indexOf(ingredient), 1);
    }

    addStep(){
        this.steps.push(new String());
    }

    removeStep(step : String){
        this.steps.splice(this.steps.indexOf(step), 1);
    }

    errorUser(error : string){
        this.error.nativeElement.innerText = error; 
    }

}