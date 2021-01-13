package Model;
import Model.Part;

/**
 *
 * @author alect
 */
public class OutSourced extends Part {
    
    private String companyName;

    public OutSourced(int partID, String name, double price, int stock, int min, int max, String companyName) {
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    


}