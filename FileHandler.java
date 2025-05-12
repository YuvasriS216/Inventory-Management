import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class that handles file operations for inventory data
 */
public class FileHandler {
    private static final String FILE_NAME = "inventory.txt";
    
    /**
     * Saves the inventory to a file
     * 
     * @param products List of products to save
     * @return true if the save was successful, false otherwise
     */
    public boolean saveInventory(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving inventory to file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Loads the inventory from a file
     * 
     * @return List of products loaded from the file, or an empty list if the file doesn't exist
     */
    public List<Product> loadInventory() {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return products;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = parseProduct(line);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory from file: " + e.getMessage());
        }
        
        return products;
    }
    
    /**
     * Parses a product from a string line
     * 
     * @param line The line to parse
     * @return The parsed product, or null if the line is invalid
     */
    private Product parseProduct(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length != 4) {
                System.err.println("Invalid product line: " + line);
                return null;
            }
            
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            double price = Double.parseDouble(parts[3]);
            
            return new Product(id, name, quantity, price);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in line: " + line);
            return null;
        }
    }

    
}
