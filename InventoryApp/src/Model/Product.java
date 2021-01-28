package Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Product {

     public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
     private int productID;
     private String name;
     private double price;
     private int stock;
     private int min;
     private int max;
     private double cost;
     
     /*
     Constructor for the product class
     */
     public Product() {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
     }  
    
     /**
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    } 
     
    /**
     *
     * @return product ID
     */
    public int getProductId() {
        return productID;
    }
    
    /**
     *
     * @param name
     */
    public void setProductName(String name) {
        this.name = name;
    }

    /**
     *
     * @return product name
     */
    public String getProductName() {
        return name;
    }
    
    /**
     *
     * @param stock
     */
    public void setProductStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return product stock
     */
    public int getProductStock() {
        return stock;
    }
    
    /**
     *
     * @param price
     */
    public void setProductPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return product price
     */
    public double getProductPrice() {
        return price;
    }
    
    /**
     *
     * @param max
     */
    public void setProductMax(int max) {
        this.max = max;
    }
    
    /**
     *
     * @return product max
     */
    public int getProductMax() {
        return max;
    }
    
    /**
     *
     * @param min
     */
    public void setProductMin(int min) {
        this.min = min;
    }
    
    /**
     *
     * @return product min
     */
    public int getProductMin() {
        return min;
    }
    
    /**
     *
     * @return associated parts list
     */
    public static ObservableList getAssociated() {
        return associatedParts;
    }
    
    /**
     *set associated parts
     * @param associatedParts
     */
    public static void setAssociated(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }
    
    /**
     *
     * @param part
     */
    public static void addAssociated(Part part) {
        associatedParts.add(part);
    }
    
    /**
     *
     * @param part remove part
     */
    public static void removeAssociated(Part part) {
        associatedParts.remove(part);
    }

}

