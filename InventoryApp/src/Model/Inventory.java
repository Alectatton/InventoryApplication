package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alect
 */
public class Inventory {

    public final static ObservableList<Product> products = FXCollections.observableArrayList();
    public final static ObservableList<Part> allParts = FXCollections.observableArrayList();
   
    /*
    Empty Constructor for inventory class
    */
    public Inventory() {
    }

    /**
     *
     * @param part add part to inventory
     */
    public static void addPart(Part part) {
        allParts.addAll(part);
    }
    
    /**
     *
     * @param part remove part from inventory
     * @return true if part removed
     */
    public static boolean deletePart(Part part) {
        for (Part i : allParts) {
            if (i.getId() == part.getId()) {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param i ID of part being modified
     * @param part part being modified
     */
    public static void modPart(int i, Part part) {
        allParts.set(i, part);
    }
    
    /**
     *
     * @return list of all parts
     */
    public static ObservableList<Part> getParts() {
        return allParts;
    }

    /**
     *
     * @param product add product to inventory
     */
    public static void addProduct(Product product) {
        products.add(product);
    }
    
    /**
     *
     * @param product remove product from inventory
     * @return true if removed
     */
    public static boolean deleteProduct(Product product) {
        for (Product i : products) {
            if (i.getProductId() == product.getProductId()) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param i ID of product to be modified
     * @param product modify product
     */
    public static void modProduct(int i, Product product) {
        products.set(i, product);
    }

    /**
     *
     * @return all products in inventory
     */
    public static ObservableList<Product> getProducts() {
        return products;
    }
    
    /**
     *
     * @return generates a new and unique part ID
     */
    public static int getNewPartID() {
        int newID = 1;
        for (Part p : Inventory.allParts) {
            if (p.getId() == newID) {
                newID++;
            }
        }
        return newID;
    }
    

     /**
     *
     * @return generates a new and unique product ID
     */
    public static int getNewProdID() {
        int newID = 1001;
        for (Product p : Inventory.products) {
            if (p.getProductId() == newID) {
                newID++;
            }
        }
        return newID;
    }
    
    /**
     *
     * @param partName input to be searched
     * @return parts with a matching Name or ID
     */
    public static Part lookupPart(String partName) {
           for (Part p : Inventory.allParts) {
               if (p.getName().contains(partName)
                   ||
                   String.valueOf(p.getId()).equals(partName))                    
               {
                   return p;
               }
           }                     
        return null;
       }
    
    /**
     *
     * @param productName input to be searched
     * @return products with a matching Name or ID
     */
    public static Product lookupProduct(String productName) {
           for (Product p : Inventory.products) {
               if (p.getProductName().contains(productName)
                   ||
                   String.valueOf(p.getProductId()).equals(productName))                    
               {
                   return p;
               }
           }                     
        return null;
       }   
    
}