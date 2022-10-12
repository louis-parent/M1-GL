import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipesList} from './recipe/recipe-list/recipes-list.component'
import { RecipeItem } from './recipe/recipe-list/recipe/recipe.component';
import { RecipeDetail } from './recipe/recipe-detail/recipe-detail.component';
import { Login } from './user/login/login.component';
import { Register } from './user/register/register.component';
import { OpinionList } from './recipe/recipe-detail/opinion/opinion-list/opinion-list.component';
import { OpinionItem } from './recipe/recipe-detail/opinion/opinion-item/opinion-item.component';
import { OpinionEditor } from './recipe/recipe-detail/opinion/opinion-editor/opinion-editor.component';
import { RecipeCreation } from './recipe/recipe-creation/recipe-creation.component';
import { ResearchBar } from './research/research-bar.component';
import { OpinionScore } from './recipe/recipe-detail/opinion/opinion-score/opinion-score.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipesList,
    RecipeItem,
    RecipeDetail,
    RecipeCreation,
    OpinionList,
    OpinionItem,
    OpinionEditor,
    Login,
    Register,
    ResearchBar,
    OpinionScore
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
