package Products;

import java.util.ArrayList;
import java.util.List;

public class ShopingCart {

    private Double cost;
    private List<Object> productList;
    private String Details;
    private String deliveryAddress;

    public ShopingCart(){};
    public ShopingCart(Double cost, List<Object> productList, String Details, String deliveryAddress) {
        this.cost = cost;
        this.productList = productList;
        this.Details = Details;
        this.deliveryAddress = deliveryAddress;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Object> getProductList() {
        return productList;
    }

    public void setProductList(List<Object> productList) {
        this.productList = productList;
    }

    public String getDetails() {
        return Details;
    }

    public ArrayList<Object> findName(String product){
        List<Object> Products = getProductList();
        ArrayList<Object> productDate = new ArrayList<Object>();
        Object found = null;
        for (Object item: Products) {
            int index = item.toString().lastIndexOf("costs");
            int index$ = item.toString().lastIndexOf("$");
            String theCost = item.toString().substring(index+6,index$);
            if(item.toString().contains(product)) {
                found = item;
                productDate.add(found);
                productDate.add(theCost);
            }
        }

        return productDate;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public void printAll() {
        System.out.println("This is your Shopping Cart: " + getProductList() + "\n To pay: " + getCost() +"$");
    }
    public void printAllDetails(){
        System.out.println("This is your Shopping Cart: " + getProductList() + "\n To pay: " + getCost() +"$" +
                " and the details are: " + getDetails() + " to the address: " + getDeliveryAddress());
    }
    public void sale(){
        cost = cost * 15/100;
    }
}
