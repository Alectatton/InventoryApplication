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
public class modProductController implements Initializable {

    
    //Add Product text fields
    @FXML private TextField modProdID;
    @FXML private TextField modProdName;
    @FXML private TextField modProdInv;
    @FXML private TextField modProdPrice;
    @FXML private TextField modProdMax;
    @FXML private TextField modProdMin;
    @FXML private TextField modProdSearch;
    
    //InStockTable
    @FXML private TableView<Part> modProdInStock;
    @FXML private TableColumn<Part, Integer> modProdInStockID;
    @FXML private TableColumn<Part, String> modProdInStockName;
    @FXML private TableColumn<Part, Integer> modProdInStockInv;
    @FXML private TableColumn<Part, Double> modProdInStockPrice;
    @FXML private Button modProdAdd;
    
    //Associated Table
    @FXML private TableView<Part> modProdAssociated;
    @FXML private TableColumn<Part, Integer> modProdAID;
    @FXML private TableColumn<Part, String> modProdAName;
    @FXML private TableColumn<Part, Integer> modProdAInv;
    @FXML private TableColumn<Part, Double> modProdAPrice;
    @FXML private Button modProdRemove;
    @FXML private Button modProdSave;
    @FXML private Button modProdCancel;

    
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) modProdCancel.getScene().getWindow();
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
