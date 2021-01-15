package Model;

/**
 *
 * @author alect
 */
public abstract class Part {
    
    public int partID;
    public String name;
    public double price;
    public int stock;
    public int max;
    public int min;
    
    public Part() {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;   
    }

    /**
     * @return the id
     */
    public int getId() {
        return this.partID;
    }

    /**
     * @param partID
     */
    public void setId(int partID) {
        this.partID = partID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return this.min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return this.max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
