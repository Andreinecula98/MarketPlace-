package Products;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class Aliment extends Product{

    private ProductType type;
    private Date expirationDate;

    public Aliment(String name, Integer pret, ProductType tip , Date expirationDate) {
        super(name, pret);
        this.type = tip;
        this.expirationDate = expirationDate;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getExpirationDate() {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleFormat.format(expirationDate);
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String verification(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();

        System.out.println("Today's date is " + simpleFormat.format(now));
        if (simpleFormat.format(expirationDate).compareTo(simpleFormat.format(now)) < 0 ){
            return "This " + getType() + " expired!";
        }else{
            return "This " + getType() + " is still good!";
        }
    }

    public void Exists(String name, boolean nothing){
        if(getName().equalsIgnoreCase(name)){
            nothing = true;
            System.out.println(printDetails(name));
        }
    }

    @Override
    public String printDetails(String name) {

        return "Here are some details about " + name + ":\n" + "Name: " + getName()
                                                             + "\nPrice: " + getPrice() + "$"
                                                             + "\nExpiration Date: " + getExpirationDate()
                                                             + "\nQuality: " + verification();
    }

    @Override
    public String toString() {
        return "This " + getName() + " is a " + getType() + " and costs " + getPrice() + "$";
    }


}
