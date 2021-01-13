package main;

import Model.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Inventory inv = new Inventory();
        addTestData(inv);
            
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


    void addTestData(Inventory inv) {
        //Add Parts
        //Part p1 = new OutSourced(1,"Brakes",15.00,10,2,100,"Company");
        //Part p2 = new InHouse(2,"Wheel",11.00,16,2,20,3);
        //Part p3 = new InHouse(3,"Seat",15.00,10,2,12,4);
        //inv.addPart(p1);
        //inv.addPart(p2);
        //inv.addPart(p3);
        
        //Add Products
        //Product prod1 = new Product(1000,"Giant Bike",299.99,5,2,7);
        //Product prod2 = new Product(1001,"Tricycle",99.99,3,2,7);
        //inv.addProduct(prod1);
        //inv.addProduct(prod2);
    }
    
}
