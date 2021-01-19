package View_Controller;

import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.addPart;
import Model.OutSourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alect
 */
public class AddPartController implements Initializable {

    //Radio Buttons
    @FXML private RadioButton addPartInHouse;
    @FXML private RadioButton addPartOutsourced;
    @FXML private Text machineIdText;
    private ToggleGroup partType;
    private Boolean isInHouse = true;
    
    //Buttons
    @FXML private Button addPartSave;
    @FXML private Button addPartCancel;
    
    //Text Fields
    //@FXML private TextField addPartID;
    @FXML private TextField addPartName;
    @FXML private TextField addPartInv;
    @FXML private TextField addPartPrice;
    @FXML private TextField addPartMax;
    @FXML private TextField addPartMin;
    @FXML private TextField addPartMachineID;
    
    //Initialize variables as strings
    String name;
    String stock;
    String price;
    String max;
    String min;
    String machID;
    int partID;
    
    //Handle changing of radio buttons
    @FXML
    public void partTypeChange() {
        if(this.partType.getSelectedToggle().equals(this.addPartInHouse)) {
            isInHouse = true;
            machineIdText.setText("Machine ID");
        }
        else {
            isInHouse = false;
            machineIdText.setText("Company");
        }
    }
    
    //Add part to inventory on save action
    @FXML
    void handleSaveAction() throws IOException {
        
        //Set variables to text fields
        name = addPartName.getText();
        stock = addPartInv.getText();
        price = addPartPrice.getText();
        max = addPartMax.getText();
        min = addPartMin.getText();
        machID = addPartMachineID.getText();
       
        //Logic to verify no fields are empty
        if (
            addPartName.getText().isEmpty() ||
            addPartInv.getText().isEmpty() ||
            addPartPrice.getText().isEmpty() ||
            addPartMax.getText().isEmpty() ||
            addPartMin.getText().isEmpty() ||
            addPartMachineID.getText().isEmpty()) 
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
        else if (Integer.parseInt(stock) <= Integer.parseInt(min) || Integer.parseInt(stock) >= Integer.parseInt(max)) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("Invalid");
            nullalert.setContentText("Inventory must be between maximum and minimum!");
            nullalert.showAndWait();
        }
        
        //Finish function if verified
        else {
            
            //Check part type
            if (isInHouse) {
                InHouse newPartIn = new InHouse();
        
                newPartIn.setId(partID);
                newPartIn.setName(name);
                newPartIn.setStock(Integer.parseInt(stock));
                newPartIn.setPrice(Double.parseDouble(price));
                newPartIn.setMax(Integer.parseInt(max));
                newPartIn.setMin(Integer.parseInt(min));
                newPartIn.setMachineID(Integer.parseInt(machID));
            
                addPart(newPartIn);  
            }
            else {
                OutSourced newPartOut = new OutSourced();
                
                newPartOut.setId(partID);
                newPartOut.setName(name);
                newPartOut.setStock(Integer.parseInt(stock));
                newPartOut.setPrice(Double.parseDouble(price));
                newPartOut.setMax(Integer.parseInt(max));
                newPartOut.setMin(Integer.parseInt(min));
                newPartOut.setCompanyName(machID);    
        
                addPart(newPartOut);
            }
        
            //Return to the main screen
            Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) addPartSave.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    } 
    
    //Return to the main screen on cancel button action
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) addPartCancel.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        partType = new ToggleGroup();
        addPartInHouse.setToggleGroup(partType);
        addPartOutsourced.setToggleGroup(partType);
        addPartInHouse.setSelected(true);
        machineIdText.setText("Machine ID");        
        partID = Inventory.getNewPartID();        
    }        
}
