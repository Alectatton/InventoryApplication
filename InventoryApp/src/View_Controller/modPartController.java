package View_Controller;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alect
 */
public class modPartController implements Initializable {

    //Radio Buttons
    @FXML private RadioButton modPartInHouse;
    @FXML private RadioButton modPartOutsourced;
    
    //Buttons
    @FXML private Button modPartSave;
    @FXML private Button modPartCancel;
    
    //Text Fields
    @FXML TextField modPartID;
    @FXML TextField modPartName;
    @FXML TextField modPartInv;
    @FXML TextField modPartPrice;
    @FXML TextField modPartMax;
    @FXML TextField modPartMin;
    @FXML TextField modPartMachineID;

    
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
