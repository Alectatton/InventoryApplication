package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Product {

    /**
     *
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
     
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
     * @param productID sets the product ID
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
     * @param name set the product name
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
     * @param stock set the number of products in stock
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
     * @param price set the product price
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
     * @param max set the maximum number of products
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
     * @param min set the minimum number of products
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
    public ObservableList<Part> getAssociated() {
        return this.associatedParts;
    }
    
    /**
     *set associated parts
     * @param associatedParts set associated parts list
     */
    public void setAssociated(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    /**
     *
     * @param part add part to the products associated parts list
     */
    public void addAssociated(Part part) {
        associatedParts.add(part);
    }
    
    
    /**
     *
     * @param part remove part
     */
    public void removeAssociated(Part part) {
        associatedParts.remove(part);
    }

}

