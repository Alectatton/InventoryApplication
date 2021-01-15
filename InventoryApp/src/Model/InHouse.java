package Model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author alect
 */
public final class InHouse extends Part {
    
    private int machineID;
    
    public InHouse() {
        
        setId(partID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMax(max);
        setMin(min);
        setMachineID(machineID);
    }
    
    public void setMachineID(int id) {
        this.machineID = id;
    }
    
    public int getMachineID() {
        return machineID;
    }
}
