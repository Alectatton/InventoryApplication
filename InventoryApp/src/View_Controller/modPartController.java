package View_Controller;

import Model.InHouse;
import static Model.Inventory.modPart;
import Model.OutSourced;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static View_Controller.MainScreenController.getSelectedPart;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author alect
 */
public class modPartController implements Initializable {

    //Radio Buttons
    @FXML private RadioButton modPartInHouse;
    @FXML private RadioButton modPartOutsourced;
    @FXML private Text machineIdText;
    private ToggleGroup partType;
    private Boolean isInHouse = true;
    
    //Buttons
    @FXML private Button modPartSave;
    @FXML private Button modPartCancel;
    
    //Text Fields
    @FXML private TextField modPartID;
    @FXML private TextField modPartName;
    @FXML private TextField modPartInv;
    @FXML private TextField modPartPrice;
    @FXML private TextField modPartMax;
    @FXML private TextField modPartMin;
    @FXML private TextField modPartMachineID;
    
    //Initialize Variables
    String name;
    String stock;
    String price;
    String max;
    String min;
    String machID;
    int partID;

    /**
     * Handle changing the part type
     */
    @FXML
    public void partTypeChange() {
        if(this.partType.getSelectedToggle().equals(this.modPartInHouse)) {
            isInHouse = true;
            machineIdText.setText("Machine ID");
        }
        else {
            isInHouse = false;
            machineIdText.setText("Company");
        }
    }
    
    /*
    * Handle saving the modified part
    */
    @FXML
    void handleSaveAction() throws IOException {
        
        //Set text boxes to variables, use old part ID
        partID = getSelectedPart().getId();
        name = modPartName.getText();
        stock = modPartInv.getText();
        price = modPartPrice.getText();
        max = modPartMax.getText();
        min = modPartMin.getText();
        machID = modPartMachineID.getText();
        
        try{
            //Logic to verify no fields are empty
            if (
                modPartName.getText().isEmpty() ||
                modPartInv.getText().isEmpty() ||
                modPartPrice.getText().isEmpty() ||
                modPartMax.getText().isEmpty() ||
                modPartMin.getText().isEmpty() ||
                modPartMachineID.getText().isEmpty())
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
                //Check part type, create a new part and replace information
                if (isInHouse) {
                    InHouse newPartIn = new InHouse();        
                    newPartIn.setId(partID);
                    newPartIn.setName(name);
                    newPartIn.setStock(Integer.parseInt(stock));
                    newPartIn.setPrice(Double.parseDouble(price));
                    newPartIn.setMax(Integer.parseInt(max));
                    newPartIn.setMin(Integer.parseInt(min));
                    newPartIn.setMachineID(Integer.parseInt(machID));
            
                    //Need to find index to remove part
                    modPart(partID - 1, newPartIn);  
                }
        
                //Same process for outsourced parts
                else {
                    OutSourced newPartOut = new OutSourced();             
                    newPartOut.setId(partID);
                    newPartOut.setName(name);
                    newPartOut.setStock(Integer.parseInt(stock));
                    newPartOut.setPrice(Double.parseDouble(price));
                    newPartOut.setMax(Integer.parseInt(max));
                    newPartOut.setMin(Integer.parseInt(min));
                    newPartOut.setCompanyName(machID);            
                    modPart(partID - 1, newPartOut);
                }
        
                //Return to the main screen
                Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) modPartSave.getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        }
        
        //catch for type errors
        catch (IOException | NumberFormatException e) {
            Alert nullalert = new Alert(Alert.AlertType.ERROR);
            nullalert.setTitle("Type error");
            nullalert.setContentText("Verify that all fields are the correct data type");
            nullalert.showAndWait();
        }
    
    } 
    
    /*
    * Method to handle the cancel button action, returns to the main screen
    */
    @FXML  
    public void cancelButtonAction() throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) modPartCancel.getScene().getWindow();
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
        modPartInHouse.setToggleGroup(partType);
        modPartOutsourced.setToggleGroup(partType);  
        
        Part selectedPart = getSelectedPart();
        int ID = getSelectedPart().getId();
        
        modPartID.setText("Auto Gen - " + ID);
        modPartName.setText(selectedPart.getName()); 
        modPartInv.setText(Integer.toString(selectedPart.getStock()));
        modPartPrice.setText(Double.toString(selectedPart.getPrice()));
        modPartMax.setText(Integer.toString(selectedPart.getMax()));
        modPartMin.setText(Integer.toString(selectedPart.getMin()));
        
        if (selectedPart instanceof InHouse) {
            modPartInHouse.setSelected(true);
            machineIdText.setText("Machine ID");
            modPartMachineID.setText(Integer.toString(((InHouse)selectedPart).getMachineID()));
        }
        else {
            modPartOutsourced.setSelected(true);
            machineIdText.setText("Company");
            modPartMachineID.setText(((OutSourced)selectedPart).getCompanyName());
        }
    }       
}
