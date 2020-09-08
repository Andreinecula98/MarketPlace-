package Products;

import Electronic.TVSpecifications;

import java.util.ArrayList;

public class ElectronicTV extends Product{

    private ProductType type;
    private ArrayList<TVSpecifications> TVSpecifications;

    public ProductType getType() {
        return type;
    }

    public ElectronicTV(){

    }

    public ElectronicTV(String name, double price, ProductType type, ArrayList<TVSpecifications> TVSpecifications) {
        super(name, price);
        this.type = type;
        this.TVSpecifications = TVSpecifications;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ArrayList<TVSpecifications> getTVSpecifications() {
        return TVSpecifications;
    }

    public void setTVSpecifications(ArrayList<TVSpecifications> TVSpecifications) {
        this.TVSpecifications = TVSpecifications;
    }

    public String printDetails(String name) {
        return "Here are some details about " + name + ":\n" + "Name: " + getName()
                + "\n Price: " + getPrice() + "$"
                + "\n List of Specifications: " + getTVSpecifications();
    }

    @Override
    public String toString() {
        return "This " + getName() + " is a " + getType() + " and costs " + getPrice() + "$ with " + getTVSpecifications();
    }
}
