package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Inventory {
    
   private static final ObservableList<Product> products = FXCollections.observableArrayList();
   private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partID;
   
   public Inventory() {
   }

    public static void addPart(Part part) {
        allParts.addAll(part);
    }
    
    public static void removePart(int partID) {
        for (Part i : allParts) {
            if (i.getId() == partID)
                allParts.remove(i);
        }
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
    
    public static void removeProduct(int productID) {
        for (Product i : products) {
            if (i.getProductId() == productID)
                products.remove(i);
        }
    }
    
    public static void modProduct(int i, Product product) {
        products.set(i, product);
    }
    
    public static ObservableList<Product> getProducts() {
        return products;
    }
    
    public static int getPartIDCount() {
        partID++;
        return partID;
    }
   
}