package Model;

import java.util.ArrayList;

/**
 *
 * @author alect
 */
public class Product {

     private ArrayList<Part> associatedParts = new ArrayList<Part>();
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

    public Product(int i, String giant_Bike, double d, int i0, int i1, int i2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     


    public int getProductId() {
        return productID;
    }

    public String getProductName() {
        return name;
    }

    public int getProductStock() {
        return stock;
    }

    public double getProductPrice() {
        return price;
    }
    
}
