# Inventory-Management
# Java Inventory Management System

A console-based inventory management application built in Java that allows users to track products, manage stock levels, and receive alerts for low inventory.

## Features

- Product Management: Add, update, and remove products
- Inventory Tracking: View all products and their details (ID, name, quantity, price)
- Low Stock Alerts: Automatic flagging of items with quantity below 5
- Data Persistence: Inventory data saved to and loaded from a file (inventory.txt)

## Usage

Once the application is running, we'll see a menu with the following options:

1. Add Product: Add a new product to the inventory
2. Remove Product: Remove a product from the inventory using its ID
3. Update Product: Update details of an existing product
4. View All Products: Display all products in the inventory
5. View Low Stock Products: Display only products with quantity less than 5
6. Exit: Close the application

## Project Structure

- `InventoryApp.java`: Main application with user interface
- `InventoryManager.java`: Core business logic for inventory operations
- `Product.java`: Product class with properties and methods
- `FileHandler.java`: Handles reading from and writing to the inventory file
