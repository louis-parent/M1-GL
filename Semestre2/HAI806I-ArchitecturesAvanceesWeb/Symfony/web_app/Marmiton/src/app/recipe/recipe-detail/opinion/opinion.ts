export class Opinion{
  email: string;
  nickname : string;
  recipedId : string;
  comments : string;
  score : number;
  date : string;

  constructor(emailParam ?: string, nicknameParam ?: string, recipeIdParam ?: string, commentParam ?: string, noteParam ?: number, dateParam ?: string){
    this.email = emailParam || "";
    this.nickname = nicknameParam || "";
    this.recipedId = recipeIdParam || "";
    this.comments = commentParam || "";
    this.score = noteParam || 0;    
    this.date = dateParam || "";
  }
}
