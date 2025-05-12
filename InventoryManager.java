import java.util.ArrayList;
import java.util.List;

/**
 * InventoryManager class that handles inventory operations
 */
public class InventoryManager {
    private List<Product> products;
    private FileHandler fileHandler;
    
    /**
     * Constructor that initializes the inventory manager
     */
    public InventoryManager() {
        this.products = new ArrayList<>();
        this.fileHandler = new FileHandler();
        loadInventory();
    }
    
    /**
     * Adds a new product to the inventory
     * 
     * @param product The product to add
     * @return true if the product was added, false if a product with the same ID already exists
     */
    public boolean addProduct(Product product) {
        // Check if product with this ID already exists
        if (getProductById(product.getId()) != null) {
            return false;
        }
        
        products.add(product);
        saveInventory();
        return true;
    }
    
    /**
     * Removes a product from the inventory
     * 
     * @param id The ID of the product to remove
     * @return true if the product was removed, false if the product was not found
     */
    public boolean removeProduct(int id) {
        Product product = getProductById(id);
        if (product == null) {
            return false;
        }
        
        products.remove(product);
        saveInventory();
        return true;
    }
    
    /**
     * Updates an existing product in the inventory
     * 
     * @param product The updated product
     * @return true if the product was updated, false if the product was not found
     */
    public boolean updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());
        if (existingProduct == null) {
            return false;
        }
        
        // Update existing product
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        
        saveInventory();
        return true;
    }
    
    /**
     * Gets a product by its ID
     * 
     * @param id The ID of the product to find
     * @return The product if found, null otherwise
     */
    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Gets all products in the inventory
     * 
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    /**
     * Gets all products that are low in stock (quantity < 5)
     * 
     * @return List of low stock products
     */
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isLowStock()) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
    
    /**
     * Loads the inventory from the file
     */
    private void loadInventory() {
        List<Product> loadedProducts = fileHandler.loadInventory();
        if (loadedProducts != null) {
            products = loadedProducts;
        }
    }
    
    /**
     * Saves the inventory to the file
     */
    private void saveInventory() {
        fileHandler.saveInventory(products);
    }
    
    /**
     * Gets the highest product ID in the inventory
     * 
     * @return The highest product ID
     */
    public int getHighestId() {
        int highestId = 0;
        for (Product product : products) {
            if (product.getId() > highestId) {
                highestId = product.getId();
            }
        }
        return highestId;
    }

    
}
