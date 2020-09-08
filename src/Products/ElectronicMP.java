package Products;

import Interfata.ProductStatus;
import Electronic.*;

import java.util.ArrayList;

public class ElectronicMP extends Product implements ProductStatus {

    private ProductType type;
    private ArrayList<MobilePhoneSpecifications> Mobile;

    public ProductType getType() {
        return type;
    }

    public ElectronicMP(){

    }

    public ElectronicMP(String name, double price, ProductType type, ArrayList<MobilePhoneSpecifications> mobile) {
        super(name, price);
        this.type = type;
        Mobile = mobile;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ArrayList<MobilePhoneSpecifications> getMobile() {
        return Mobile;
    }

    public void setMobile(ArrayList<MobilePhoneSpecifications> mobile) {
        Mobile = mobile;
    }

    public void PrintList(){
        System.out.println("This " + getName() + " is a " + getType() + " and costs  " + getPrice());
    }

    public String printDetails(String name) {
        return "Here are some details about " + name + ":\n" + "Name: " + getName()
                + "\n Price: " + getPrice() + "$"
                + "\n List of Specifications: " + getMobile();
    }

    @Override
    public String toString() {
        return "This " + getName() + " is a " + getType() + " and costs " + getPrice() + "$ with " + getMobile();
    }
}
