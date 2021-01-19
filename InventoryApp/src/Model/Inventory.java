package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Inventory {
   
    //Initialize variables
    public static final ObservableList<Product> products = FXCollections.observableArrayList();
    public static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partID;
   
    //Constructor
    public Inventory() {
    }
    
    //Add part method
    public static void addPart(Part part) {
        allParts.addAll(part);
    }
    
    //Remove part Method
    public static void removePart(int partID) {
        for (Part i : allParts) {
            if (i.getId() == partID)
                allParts.remove(i);
        }
    }
    
    //Modify Part method
    public static void modPart(int i, Part part) {
        allParts.set(i, part);
    }
    
    //List with all parts
    public static ObservableList<Part> getParts() {
        return allParts;
    }

    //Method to add a new product
    public static void addProduct(Product product) {
        products.add(product);
    }
    
    //Method to remove a product
    public static void removeProduct(int productID) {
        for (Product i : products) {
            if (i.getProductId() == productID)
                products.remove(i);
        }
    }
    
    //Method to modify an existing product
    public static void modProduct(int i, Product product) {
        products.set(i, product);
    }
    
    //List with all of the products
    public static ObservableList<Product> getProducts() {
        return products;
    }
    
    //Generate a unique Part ID
    public static int getNewPartID() {
        int newID = 1;
        for (Part p : Inventory.allParts) {
            if (p.getId() == newID) {
                newID++;
            }
        }
        return newID;
    }
    
    //Generate a unique Product ID
    public static int getNewProdID() {
        int newID = 1001;
        for (Product p : Inventory.products) {
            if (p.getProductId() == newID) {
                newID++;
            }
        }
        return newID;
    }
   
}