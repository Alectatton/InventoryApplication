/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    @FXML private TextField addProdSearch;
    
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

    
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) addProdCancel.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
