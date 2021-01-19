package View_Controller;

import Model.Inventory;
import static Model.Inventory.addProduct;
import static Model.Inventory.allParts;
import static Model.Inventory.getParts;
import Model.Part;
import Model.Product;
import static Model.Product.getAssociated;
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
 */
public class AddProductController implements Initializable {
    
    //Add Product text fields
    @FXML private TextField addProdID;
    @FXML private TextField addProdName;
    @FXML private TextField addProdInv;
    @FXML private TextField addProdPrice;
    @FXML private TextField addProdMax;
    @FXML private TextField addProdMin;
    @FXML private TextField partSearch;
    
    //List for part search
    @FXML private final ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    
    //InStockTable
    @FXML private TableView<Part> addProdInStock;
    @FXML private TableColumn<Part, Integer> addProdInStockID;
    @FXML private TableColumn<Part, String> addProdInStockName;
    @FXML private TableColumn<Part, Integer> addProdInStockInv;
    @FXML private TableColumn<Part, Double> addProdInStockPrice;
    @FXML private Button addProdAdd;
    
    //Associated Table
    @FXML private TableView<Part> addProdAssociated;
    @FXML private TableColumn<Part, Integer> addProdAID;
    @FXML private TableColumn<Part, String> addProdAName;
    @FXML private TableColumn<Part, Integer> addProdAInv;
    @FXML private TableColumn<Part, Double> addProdAPrice;
    @FXML private Button addProdRemove;
    @FXML private Button addProdSave;
    @FXML private Button addProdCancel;
    
    //Initialize variables as strings
    int prodId;
    String name;
    String inv;
    String price;
    String max;
    String min;

    //Search for part by product ID or name
   @FXML
   private void searchForPart() {
       if(partSearch.getText().trim().isEmpty()) { 
           addProdInStock.setItems(allParts);
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
            addProdInStock.setItems(partInventorySearch);
            addProdInStock.refresh();                       
       }
   }
   
   //Add product to inventory on save button press
    @FXML
    public void handleSaveAction() throws IOException {
        prodId = Inventory.getNewProdID();
        name = addProdName.getText();
        inv = addProdInv.getText();
        price = addProdPrice.getText();
        max = addProdMax.getText();
        min = addProdMin.getText();
        
        //Logic to verify no fields are empty
        if (
            addProdName.getText().isEmpty() ||
            addProdInv.getText().isEmpty() ||
            addProdPrice.getText().isEmpty() ||
            addProdMax.getText().isEmpty() ||
            addProdMin.getText().isEmpty()) 
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
        
            addProduct(newProduct);
        
        //Return to the main screen
            Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) addProdSave.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
    
    //Add selected part to the associated parts list
    @FXML  
    public void addPartAction(){
        Part part = addProdInStock.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("No Selection");
            nullalert.setContentText("You must make a selection before you can do this action");
            nullalert.showAndWait();
        }
        else {
            Product.addAssociated(part);
            addProdAssociated.setItems(getAssociated());
            addProdAssociated.refresh();
        }
    }
    
    //Method to remove the selected part
    @FXML
    public void removePartAction() {
        Part part = addProdAssociated.getSelectionModel().getSelectedItem();
        Product.removeAssociated(part);        
        addProdAssociated.setItems(getAssociated());
        addProdAssociated.refresh();
    }
    
    //Exit back to the main screen
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) addProdCancel.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    //Update table with all of the in stock items
    private void updateAllParts() {
        addProdInStock.setItems(getParts());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addProdInStockID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        addProdInStockName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        addProdInStockInv.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        addProdInStockPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        
        addProdAID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        addProdAName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        addProdAInv.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        addProdAPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        
        updateAllParts();
    }      
}
