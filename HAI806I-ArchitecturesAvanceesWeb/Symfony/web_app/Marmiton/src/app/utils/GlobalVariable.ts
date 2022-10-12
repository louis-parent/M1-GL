import { Injectable, OnChanges } from "@angular/core";
import { User } from "../user/user";


@Injectable()
export class GlobalVariable{
  user : User;

  constructor(){
    this.user = new User();
  }
}