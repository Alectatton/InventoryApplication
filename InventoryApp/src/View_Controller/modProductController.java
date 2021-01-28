/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import static Model.Inventory.allParts;
import Model.Part;
import Model.Product;
import static Model.Product.getAssociated;
import static Model.Inventory.getParts;
import static Model.Inventory.modProduct;
import static Model.Product.addAssociated;
import static Model.Product.associatedParts;
import static View_Controller.MainScreenController.getSelectedProduct;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alect
 * 
 * FUTURE ENHANCEMENT: I believe a potentially useful feature that could be added to the project is allowing
 * the user to Add new or modify current parts in the inventory from both the add product and modify product screens
 */
public class modProductController implements Initializable {
    
    //Add Product text fields
    @FXML private TextField modProdID;
    @FXML private TextField modProdName;
    @FXML private TextField modProdInv;
    @FXML private TextField modProdPrice;
    @FXML private TextField modProdMax;
    @FXML private TextField modProdMin;
    @FXML private TextField partSearch;
    
    //List for part search
    @FXML private final ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    
    //InStockTable
    @FXML private TableView<Part> modProdInStock;
    @FXML private TableColumn<Part, Integer> modProdInStockID;
    @FXML private TableColumn<Part, String> modProdInStockName;
    @FXML private TableColumn<Part, Integer> modProdInStockInv;
    @FXML private TableColumn<Part, Double> modProdInStockPrice;
    @FXML private Button modProdAdd;
    
    //Associated Table
    @FXML public ObservableList<Part> currentAssociated = FXCollections.observableArrayList();
    @FXML private TableView<Part> modProdAssociated;
    @FXML private TableColumn<Part, Integer> modProdAID;
    @FXML private TableColumn<Part, String> modProdAName;
    @FXML private TableColumn<Part, Integer> modProdAInv;
    @FXML private TableColumn<Part, Double> modProdAPrice;
    @FXML private Button modProdRemove;
    @FXML private Button modProdSave;
    @FXML private Button modProdCancel;
    
    //Initialize variables as strings
    int prodId;
    String name;
    String inv;
    String price;
    String max;
    String min;

   /*
    * Method to search for part by product ID or name
   */
    
    @FXML
    private void searchForPart() {
        if(partSearch.getText().trim().isEmpty()) { 
           modProdInStock.setItems(allParts);
        }
        else {
            partInventorySearch.clear();
            for (Part p : Inventory.allParts) {
                if (p.getName().contains(partSearch.getText().trim())
                   ||
                   String.valueOf(p.getId()).equals(partSearch.getText().trim()))                    
                {
                   partInventorySearch.add(p);
                }
            }
            modProdInStock.setItems(partInventorySearch);
            modProdInStock.refresh();                       
        }
    }
    
    /*
    *Save modified product in inventory on save button press
    */
    @FXML
    public void handleSaveAction() throws IOException {
        
        //Get variables from text fields
        prodId = getSelectedProduct().getProductId();
        name = modProdName.getText();
        inv = modProdInv.getText();
        price = modProdPrice.getText();
        max = modProdMax.getText();
        min = modProdMin.getText();
        currentAssociated = modProdAssociated.getItems();
            
        try{
            //Logic to verify no fields are empty
            if (
                modProdName.getText().isEmpty() ||
                modProdInv.getText().isEmpty() ||
                modProdPrice.getText().isEmpty() ||
                modProdMax.getText().isEmpty() ||
                modProdMin.getText().isEmpty()) 
                {
                Alert nullalert = new Alert(Alert.AlertType.ERROR);
                nullalert.setTitle("Invalid");
                nullalert.setContentText("Must fill out all fields!");
                nullalert.showAndWait();
            }
        
            //Logic to verify max is larger than min
            else if (Integer.parseInt(max) <= Integer.parseInt(min)) {
                Alert nullalert = new Alert(Alert.AlertType.ERROR);
                nullalert.setTitle("Invalid");
                nullalert.setContentText("Maximum must be larger than minimum!");
                nullalert.showAndWait();
            }
            
            //Logic to verify Inventory is between the max and min
            else if (Integer.parseInt(inv) <= Integer.parseInt(min) || Integer.parseInt(inv) >= Integer.parseInt(max)) {
                Alert nullalert = new Alert(Alert.AlertType.ERROR);
                nullalert.setTitle("Invalid");
                nullalert.setContentText("Inventory must be between maximum and minimum!");
                nullalert.showAndWait();
            }
        
            //Finish function if verified
            else {
                Product newProduct = new Product();
                newProduct.setProductID(prodId);
                newProduct.setProductName(name);
                newProduct.setProductStock(Integer.parseInt(inv));
                newProduct.setProductPrice(Double.parseDouble(price));
                newProduct.setProductMax(Integer.parseInt(max));
                newProduct.setProductMin(Integer.parseInt(min));
                newProduct.setAssociated(currentAssociated);                     
                modProduct(prodId - 1000, newProduct);                     
                
                //Return to the main screen
                Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) modProdSave.getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        }
        
        //Catch for type errors
        catch (IOException | NumberFormatException e) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("Type error");
            nullalert.setContentText("Verify that all fields are the correct data type");
            nullalert.showAndWait();
        }
    }
   
    /**
     * Add selected part to the associated parts list
     */
    @FXML  
    public void addPartAction(){
        Part part = modProdInStock.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("No Selection");
            nullalert.setContentText("You must make a selection before you can do this action");
            nullalert.showAndWait();
        }
        else if(associatedParts.contains(part)) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("Duplicate Part");
            nullalert.setContentText("Part cannot be added twice");
            nullalert.showAndWait();
        }
        else {
            associatedParts.add(part);
            modProdAssociated.setItems(associatedParts);
        }
    }
    
    /*
    *Method to remove the selected part from the associated parts list
    */
    @FXML
    public void removePartAction() {
        Part part = modProdAssociated.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("No Selection");
            nullalert.setContentText("You must make a selection before you can do this action");
            nullalert.showAndWait();
        }
        else {
            Product.removeAssociated(part);       
            modProdAssociated.setItems(getAssociated());
            modProdAssociated.refresh();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success!");
            alert.setContentText("Selected part removed");
            alert.showAndWait();
        }
    }
    
    /*
    * Return to the main screen on cancel button press
    */
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) modProdCancel.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    /*
    * Updates the in stock table with all of the parts
    */
    private void updateAllParts() {
        modProdInStock.setItems(getParts());
    }
    
    private void updateAssociatedParts() {
        modProdAssociated.setItems(currentAssociated);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product selectedProduct = getSelectedProduct();
        int ID = getSelectedProduct().getProductId();
        
        modProdInStockID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        modProdInStockName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        modProdInStockInv.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        modProdInStockPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        
        modProdAID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        modProdAName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        modProdAInv.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        modProdAPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());        
        
        modProdID.setText("Auto Gen - " + ID);
        modProdName.setText(selectedProduct.getProductName()); 
        modProdInv.setText(Integer.toString(selectedProduct.getProductStock()));
        modProdPrice.setText(Double.toString(selectedProduct.getProductPrice()));
        modProdMax.setText(Integer.toString(selectedProduct.getProductMax()));
        modProdMin.setText(Integer.toString(selectedProduct.getProductMin()));
               
        updateAllParts();
        
        currentAssociated = Product.getAssociated();   
        updateAssociatedParts();
    }        
}
