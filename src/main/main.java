package main;

import Model.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Javadocs in dist/javadoc

/**
 *
 * @author alect
 */
public class main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @param stage
     */
    
    @Override
    public void start(Stage stage) throws IOException{        
        addTestData();
            
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.show();   
    }
    
    /**
     * @param args 
     */


    void addTestData() {
        //Add Parts
        InHouse p1 = new InHouse();
        OutSourced p2 = new OutSourced();
        InHouse p3 = new InHouse();
        
        p1.setId(1);
        p1.setName("Brakes");
        p1.setStock(10);
        p1.setPrice(15.00);
        p1.setMax(20);
        p1.setMin(5);
        p1.setMachineID(1);
        
        p2.setId(2);
        p2.setName("Wheel");
        p2.setStock(16);
        p2.setPrice(11.00);
        p2.setMax(20);
        p2.setMin(5);
        p2.setCompanyName("Company");
        
        p3.setId(3);
        p3.setName("Seat");
        p3.setStock(10);
        p3.setPrice(15.00);
        p3.setMax(20);
        p3.setMin(5);
        p3.setMachineID(1);

        Inventory.addPart(p1);
        Inventory.addPart(p2);
        Inventory.addPart(p3);

        
        //Add Products
        Product prod1 = new Product();
        Product prod2 = new Product();
        
        prod1.setProductID(1000);
        prod1.setProductName("Giant Bike");
        prod1.setProductStock(5);
        prod1.setProductPrice(299.99);
        prod1.setProductMax(5);
        prod1.setProductMin(2);
        
        prod2.setProductID(1001);
        prod2.setProductName("Tricycle");
        prod2.setProductStock(3);
        prod2.setProductPrice(99.99);
        prod2.setProductMax(10);
        prod2.setProductMin(0);
        
        Inventory.addProduct(prod1);
        Inventory.addProduct(prod2);
    }
}
