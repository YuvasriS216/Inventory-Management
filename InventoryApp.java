import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * InventoryApp class that provides the main user interface for the inventory management system
 */
public class InventoryApp {
    private InventoryManager inventoryManager;
    private Scanner scanner;
    
    /**
     * Constructor to initialize the inventory app
     */
    public InventoryApp() {
        this.inventoryManager = new InventoryManager();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to start the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        InventoryApp app = new InventoryApp();
        app.start();
    }
    
    /**
     * Starts the inventory management application
     */
    public void start() {
        boolean running = true;
        
        System.out.println("===== Inventory Management System =====");
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    viewLowStockProducts();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. View All Products");
        System.out.println("5. View Low Stock Products");
        System.out.println("6. Exit");
    }
    
    /**
     * Gets integer input from the user
     * 
     * @param prompt The prompt to display
     * @return The integer input
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
    
    /**
     * Gets double input from the user
     * 
     * @param prompt The prompt to display
     * @return The double input
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
    
    /**
     * Gets string input from the user
     * 
     * @param prompt The prompt to display
     * @return The string input
     */
    private String getStringInput(String prompt) {
        scanner.nextLine(); // Clear the scanner buffer
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * Adds a product to the inventory
     */
    private void addProduct() {
        System.out.println("\n===== Add New Product =====");
        
        // Generate a new ID by incrementing the highest ID
        int id = inventoryManager.getHighestId() + 1;
        System.out.println("Product ID: " + id);
        
        String name = getStringInput("Enter product name: ");
        int quantity = getIntInput("Enter quantity: ");
        double price = getDoubleInput("Enter price: ₹");
        
        Product product = new Product(id, name, quantity, price);
        
        if (inventoryManager.addProduct(product)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product. Product with this ID already exists.");
        }
    }
    
    /**
     * Removes a product from the inventory
     */
    private void removeProduct() {
        System.out.println("\n===== Remove Product =====");
        int id = getIntInput("Enter product ID to remove: ");
        
        if (inventoryManager.removeProduct(id)) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Failed to remove product. Product not found.");
        }
    }
    
    /**
     * Updates a product in the inventory
     */
    private void updateProduct() {
        System.out.println("\n===== Update Product =====");
        int id = getIntInput("Enter product ID to update: ");
        
        Product existingProduct = inventoryManager.getProductById(id);
        if (existingProduct == null) {
            System.out.println("Product not found.");
            return;
        }
        
        System.out.println("Current product details: " + existingProduct);
        
        String name = getStringInput("Enter new product name (or press Enter to keep current): ");
        if (name.isEmpty()) {
            name = existingProduct.getName();
        }
        
        int quantity = getIntInput("Enter new quantity (or -1 to keep current): ");
        if (quantity == -1) {
            quantity = existingProduct.getQuantity();
        }
        
        double price = getDoubleInput("Enter new price (or -1 to keep current): ₹");
        if (price == -1) {
            price = existingProduct.getPrice();
        }
        
        Product updatedProduct = new Product(id, name, quantity, price);
        
        if (inventoryManager.updateProduct(updatedProduct)) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Failed to update product.");
        }
    }
    
    /**
     * Views all products in the inventory
     */
    private void viewAllProducts() {
        System.out.println("\n===== All Products =====");
        List<Product> products = inventoryManager.getAllProducts();
        
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        
        for (Product product : products) {
            System.out.println(product);
        }
    }
    
    /**
     * Views low stock products in the inventory
     */
    private void viewLowStockProducts() {
        System.out.println("\n===== Low Stock Products =====");
        List<Product> lowStockProducts = inventoryManager.getLowStockProducts();
        
        if (lowStockProducts.isEmpty()) {
            System.out.println("No low stock products.");
            return;
        }
        
        System.out.println("Products with quantity less than 5:");
        for (Product product : lowStockProducts) {
            System.out.println(product);
        }
    }
}

