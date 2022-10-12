export class Ingredient{
    name : String;
    quantity : Number;
    unit : String;
    price : Number;

    constructor(name : String, quantity : Number, unit : String, price : Number){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
    }
}