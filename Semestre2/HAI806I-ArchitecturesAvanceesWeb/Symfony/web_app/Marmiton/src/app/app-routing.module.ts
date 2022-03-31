import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesList } from './recipe/recipe-list/recipes-list.component';
import { RecipeDetail } from './recipe/recipe-detail/recipe-detail.component';
import { Login } from './user/login/login.component';
import { Register } from './user/register/register.component';
import { RecipeCreation } from './recipe/recipe-creation/recipe-creation.component';

const routes: Routes = [
  { path: '',   component: RecipesList },
  {path: 'recipe/:recipeId', component: RecipeDetail},
  { path: 'addRecipe',   component: RecipeCreation },
  { path: 'login',   component: Login },
  { path: 'register',   component: Register }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
