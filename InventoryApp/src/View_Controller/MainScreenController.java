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
import static Model.Inventory.getParts;
import static Model.Inventory.getProducts;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
        

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
    @FXML private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    @FXML private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    
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
    @FXML private ObservableList<Product> prodInventory = FXCollections.observableArrayList();
    @FXML private ObservableList<Product> prodInventorySearch = FXCollections.observableArrayList();
    
    @FXML private Button exitButton;
    @FXML private Inventory inv;

   
     /* Initializes the controller class.
     */
   public MainScreenController() {
   }
   
   /*
   @FXML
   private void searchForPart() {
       if(!partSearch.getText().trim().isEmpty()) {
           partsInventorySearch.clear();
           for (Part p : inv.getParts()) {
               if (p.getName().contains(partSearch.getText().trim())) {
                   partsInventoySearch.add(p);
               }
           }
           partsTable.setItems(partsInventorySearch);
           partsTable.refresh();
       }
   }
   */
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
        Parent loader = FXMLLoader.load(getClass().getResource("modPart.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) partMod.getScene().getWindow();
        window.setScene(scene);
        window.show();
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
        Parent loader = FXMLLoader.load(getClass().getResource("modProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) productMod.getScene().getWindow();
        window.setScene(scene);
        window.show();
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
    
    @FXML
    public void updatePartsTable() {
        partsTable.setItems(getParts());
    }

    @FXML
    public void updateProductsTable() {
        productTable.setItems(getProducts());
    }
    
}
