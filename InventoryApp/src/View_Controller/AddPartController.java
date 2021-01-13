package View_Controller;

import Model.InHouse;
import Model.Part;
import Model.Inventory;
import static Model.Inventory.addPart;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML TextField addPartID;
    @FXML TextField addPartName;
    @FXML TextField addPartInv;
    @FXML TextField addPartPrice;
    @FXML TextField addPartMax;
    @FXML TextField addPartMin;
    @FXML TextField addPartMachineID;
    
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
    
    @FXML
    void handleSaveAction() throws IOException {
        
        String name = addPartName.getText();
        String stock = addPartInv.getText();
        String price = addPartPrice.getText();
        String max = addPartMax.getText();
        String min = addPartMin.getText();
        String machID = addPartMachineID.getText();
        int partId = 1;
        
        
        
        InHouse newPart = new InHouse(
                partId,
                name,
                Double.parseDouble(price),
                Integer.parseInt(stock),
                Integer.parseInt(max),
                Integer.parseInt(min),
                Integer.parseInt(machID)
        );
        
        newPart.setId(partId);
        newPart.setName(name);
        newPart.setStock(Integer.parseInt(stock));
        newPart.setPrice(Double.parseDouble(price));
        newPart.setMax(Integer.parseInt(max));
        newPart.setMin(Integer.parseInt(min));
        newPart.setMachineID(Integer.parseInt(machID));
        
        addPart(newPart);
        
        Parent loader = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) addPartSave.getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }
        
    
    
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
        this.addPartInHouse.setToggleGroup(partType);
        this.addPartOutsourced.setToggleGroup(partType);
        
    }    
    
}
