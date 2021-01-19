package View_Controller;

import Model.Product;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 
import Model.Inventory;
import static Model.Inventory.allParts;
import static Model.Inventory.products;
import static Model.Inventory.getParts;
import static Model.Inventory.getProducts;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
        

/**
 * FXML Controller class
 *
 * @author alect
 */
public class MainScreenController implements Initializable {

    @FXML private AnchorPane scrMain;
    
    //Parts
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInvCol;
    @FXML private TableColumn<Part, Double> partCostCol;
    @FXML private Button partDelete;
    @FXML private Button partAdd;
    @FXML private Button partMod;
    @FXML private TextField partSearch;
    @FXML private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    @FXML private final ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    
    //Products
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productIDCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productInvCol;
    @FXML private TableColumn<Product, Double> productCostCol;
    @FXML private Button productDelete;
    @FXML private Button productAdd;
    @FXML private Button productMod;
    @FXML private TextField productSearch;
    @FXML private final ObservableList<Product> prodInventory = FXCollections.observableArrayList();
    @FXML private final ObservableList<Product> prodInventorySearch = FXCollections.observableArrayList();
    
    @FXML private Button exitButton;
    @FXML private Inventory inv;
    private static Part selectedPart;
    private static Product selectedProduct;

   
     /* Initializes the controller class.
     */
   public MainScreenController() {
   }
   
    /*
    Search for part on enter button click using part ID or part Name
    */
   @FXML
   private void searchForPart() {
       if(partSearch.getText().trim().isEmpty()) { 
           partsTable.setItems(allParts);
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
            partsTable.setItems(partInventorySearch);
            partsTable.refresh();                       
       }
   }
   
    /*
    Change screen to the add part screen on add part button click
    */
    @FXML  
    public void addPartsScreen() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) partAdd.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    /*
    Change screen to the modify part screen on modify part button click
    */
    @FXML  
    public void modPartsScreen() throws IOException{    
        selectedPart = partsTable.getSelectionModel().getSelectedItem();        
        if (selectedPart == null) {
            noSelectionAlert();
        }        
        else {
        Parent loader = FXMLLoader.load(getClass().getResource("modPart.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) partMod.getScene().getWindow();
        window.setScene(scene);
        window.show();
        }
    }
    
    /*
    Removes the selected product from the parts list
    */
    @FXML
    public void deleteButtonAction() {
        Part part = partsTable.getSelectionModel().getSelectedItem();       
        if (part == null) {
            noSelectionAlert();
        }
        else {
        Inventory.removePart(part.getId());
        }
    }
    
    /*
    Search for product on enter button click using part ID or part Name
    */
   @FXML
   private void searchForProduct() {
       if(productSearch.getText().trim().isEmpty()) { 
           productTable.setItems(products);
       }
       else {
           prodInventorySearch.clear();
           for (Product p : Inventory.products) {
               if (p.getProductName().contains(productSearch.getText().trim())
                   ||
                   String.valueOf(p.getProductId()).equals(productSearch.getText().trim()))                    
               {
                   prodInventorySearch.add(p);
               }
           }
            productTable.setItems(prodInventorySearch);
            productTable.refresh();                       
       }
   }
    
    /*
    Change screen to the add product screen on add porduct button click
    */
    @FXML  
    public void addProductScreen() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) productAdd.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    /*
    Change screen to the modify product screen on modify product button click
    */
    @FXML  
    public void modProductScreen() throws IOException{
        selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            noSelectionAlert();
        }
        else {
        Parent loader = FXMLLoader.load(getClass().getResource("modProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) productMod.getScene().getWindow();
        window.setTitle("Modify Product");
        window.setScene(scene);
        window.show();
        }
    }
    
    //Method to return the selected part
    public static Part getSelectedPart() {
        return selectedPart;
    }
    
    //Method to return the selected product
    public static Product getSelectedProduct() {
        return selectedProduct;
    }
    
    //Method to delete the selected product
    @FXML
    public void deleteProductAction() {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            noSelectionAlert();
        }
        else {
        Inventory.removeProduct(product.getProductId());
        }
    }
    
    /*
    Exit application on exit button action
    */
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partCostCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        productIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductId()).asObject());
        productNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        productInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductStock()).asObject());
        productCostCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProductPrice()).asObject());
        
        updatePartsTable();
        updateProductsTable();     
    }
    
    //Method to update the parts table
    @FXML
    public void updatePartsTable() {
        partsTable.setItems(getParts());
    }

    //Method to update the products table
    @FXML
    public void updateProductsTable() {
        productTable.setItems(getProducts());
    }
    
    //Method to show a no selection alert
    private void noSelectionAlert() {
        Alert nullalert = new Alert(AlertType.ERROR);
        nullalert.setTitle("No Selection");
        nullalert.setContentText("You must make a selection before you can do this action");
        nullalert.showAndWait();
    }
    
}
