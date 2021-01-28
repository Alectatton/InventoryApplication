package Model;

/**
 *
 * @author alect
 */
public final class InHouse extends Part {
    
    private int machineID;
    
    /*
    InHouse Constructor
    */
    public InHouse() {
        
        setId(partID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMax(max);
        setMin(min);
        setMachineID(machineID);
    }
    
    /**
     *
     * @param id set the machine ID
     */
    public void setMachineID(int id) {
        this.machineID = id;
    }
    
    /**
     *
     * @return return the machine ID
     */
    public int getMachineID() {
        return machineID;
    }
}
