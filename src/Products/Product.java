package Products;

import Interfata.ProductStatus;


public abstract class Product implements ProductStatus {

    private String name;
    private double price;

    public Product(){

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String Exists(String name){
        if(getName().equalsIgnoreCase(name)){
            return printDetails(name);
        }
        return "The product doesn't exist!";
    }

    public void changeName(String name, String newName) {
        if(getName().equalsIgnoreCase(name)){
            setName(newName);
            System.out.println("The name has been changed!");
        }else{
            System.out.println("Sorry we don't have this product!");
        }
    }

    public void changePrice(String name, Double newPrice) {
        if(getName().equalsIgnoreCase(name)){
            setPrice(newPrice);
            System.out.println("The price has been changed!");
        }else{
            System.out.println("Sorry we don't have this product!");
        }
    }


    @Override
    public void inStock() {
        System.out.println("The product enters in the stock");
    }

    @Override
    public void addInTheCart() {
        System.out.println("The product has been add to the cart!");
    }
    abstract public String printDetails(String name);

    @Override
    public String toString() {
        return "This " + name + "has the price" + price;
    }
}
