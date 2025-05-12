/**
 * Product class representing an inventory item
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    
    /**
     * Constructor for creating a new product
     * 
     * @param id Unique identifier for the product
     * @param name Name of the product
     * @param quantity Available quantity of the product
     * @param price Price of the product
     */
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Checks if the product is low in stock (quantity < 5)
     * 
     * @return true if the product is low in stock, false otherwise
     */
    public boolean isLowStock() {
        return quantity < 5;
    }
    
    /**
     * Converts the product to a string format for storing in a file
     * Format: id,name,quantity,price
     * 
     * @return String representation of the product
     */
    public String toFileString() {
        return id + "," + name + "," + quantity + "," + price;
    }
    
    /**
     * Returns a formatted string representation of the product
     * 
     * @return String representation of the product
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Quantity: %d | Price: ₹%.2f %s", 
                id, name, quantity, price, (isLowStock() ? "- LOW STOCK!" : ""));
    }
}