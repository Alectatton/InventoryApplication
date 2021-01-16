package Model;

import java.util.ArrayList;

/**
 *
 * @author alect
 */
public class Product {

     private final ArrayList<Part> associatedParts = new ArrayList<Part>();
     private int productID;
     private String name;
     private double price;
     private int stock;
     private int min;
     private int max;
     private double cost;
     
     public Product() {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
     }  
    
    public void setProductID(int productID) {
        this.productID = productID;
    } 
     
    public int getProductId() {
        return productID;
    }
    
    public void setProductName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return name;
    }
    
    public void setProductStock(int stock) {
        this.stock = stock;
    }

    public int getProductStock() {
        return stock;
    }
    
    public void setProductPrice(double price) {
        this.price = price;
    }

    public double getProductPrice() {
        return price;
    }
    
    public void setProductMax(int max) {
        this.max = max;
    }
    
    public double getProductMax() {
        return max;
    }
    
    public void setProductMin(int min) {
        this.min = min;
    }
    
    public double getProductMin() {
        return min;
    }
}
