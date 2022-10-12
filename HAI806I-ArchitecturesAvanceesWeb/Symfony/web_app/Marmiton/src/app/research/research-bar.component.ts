import { Component, ElementRef, ViewChild } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'research-bar',
  templateUrl: './research-bar.component.html',
  styleUrls: ['./research-bar.component.css']
})

export class ResearchBar {
  advanceResearch : boolean;
  router: Router;

  @ViewChild('name') name:ElementRef;
  @ViewChild('author') author:ElementRef;
  @ViewChild('difficulty') difficulty:ElementRef;
  @ViewChild('ingredient') ingredient:ElementRef;
  @ViewChild('price') price:ElementRef;

  constructor(private routeParam: Router){
    this.advanceResearch = false;

    this.name = {} as ElementRef;
    this.author = {} as ElementRef;
    this.difficulty = {} as ElementRef;
    this.ingredient = {} as ElementRef;
    this.price = {} as ElementRef;

    this.router = routeParam;
  }

  ngOnInit(){
  }

  switchAdvanceResearch(){
      this.advanceResearch = !this.advanceResearch;
  }

  research(){
    let name = this.name?.nativeElement?.value || "";
    let author = this.author?.nativeElement?.value || "";
    let difficulty = this.difficulty?.nativeElement?.value || "";
    let ingredient = this.ingredient?.nativeElement?.value || "";
    let price = this.price?.nativeElement?.value || "";

    this.router.navigate(["/"], {queryParams:{name:name, author:author, difficulty:difficulty, ingredient:ingredient, price:price}});
      	
    this.advanceResearch = false;
  }
  
  searchKeyPressed(event : any) {
  	if(event.keyCode === 13){
  		this.research();
  	}
  }
}
