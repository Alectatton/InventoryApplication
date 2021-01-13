package Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Inventory {
    
   private static ObservableList<Product> products = FXCollections.observableArrayList();
   private static ObservableList<Part> allParts = FXCollections.observableArrayList();
   
   public Inventory() {
   }

    public static void addPart(Part part) {
        allParts.addAll(part);
    }
    
    public static void removePart(Part part) {
        allParts.add(part);
    }
    
    public static void modPart(int i, Part part) {
        allParts.set(i, part);
    }
    
    public static ObservableList<Part> getParts() {
        return allParts;
    }
    
    

    public static void addProduct(Product product) {
        products.add(product);
    }
    
    public static void removeProduct(Product product) {
        products.remove(product);
    }
    
    public static void modProduct(int i, Product product) {
        products.set(i, product);
    }
    
    public static ObservableList<Product> getProducts() {
        return products;
    }
   
}