
export class User{
    public firstName : string;
    public lastName : string;
    public nickname : string;
    public email: string;
    public password : string;

    constructor(){
        this.firstName = "";
        this.lastName = "";
        this.nickname = "";
        this.email = "";
        this.password = "";
    }

    isComplete(){
        return this.firstName != "" && this.lastName != "" && this.nickname != "" && this.email != "" && this.password != "";
    }
}