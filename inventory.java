package demo;

import java.sql.*;
import java.util.*;

public class InventoryManagement {

    // Database Connection
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";            // change this
    private static final String PASSWORD = "yourpassword"; // change this

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ---------------- PRODUCT OPERATIONS ----------------
    public static void insertProduct() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.print("Enter category: ");
            String category = sc.nextLine();
            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            System.out.print("Enter supplier ID: ");
            int sid = sc.nextInt();

            String sql = "INSERT INTO products (product_name, category, price, quantity, supplier_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.setInt(5, sid);
            ps.executeUpdate();

            System.out.println("✅ Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter product ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();
            System.out.print("Enter new quantity: ");
            int qty = sc.nextInt();

            String sql = "UPDATE products SET price=?, quantity=? WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);
            ps.executeUpdate();

            System.out.println("✅ Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter product ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("🗑️ Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- SUPPLIER OPERATIONS ----------------
    public static void insertSupplier() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter supplier name: ");
            String name = sc.nextLine();
            System.out.print("Enter contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();

            String sql = "INSERT INTO suppliers (supplier_name, contact_number, email, address) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.executeUpdate();

            System.out.println("✅ Supplier added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSupplier() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter supplier ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter new contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter new email: ");
            String email = sc.nextLine();

            String sql = "UPDATE suppliers SET contact_number=?, email=? WHERE supplier_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, contact);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.executeUpdate();

            System.out.println("✅ Supplier updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSupplier() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter supplier ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM suppliers WHERE supplier_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("🗑️ Supplier deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- PURCHASE OPERATIONS ----------------
    public static void insertPurchase() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter product ID: ");
            int pid = sc.nextInt();
            System.out.print("Enter supplier ID: ");
            int sid = sc.nextInt();
            System.out.print("Enter quantity purchased: ");
            int qty = sc.nextInt();
            System.out.print("Enter total cost: ");
            double cost = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter purchase date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            String sql = "INSERT INTO purchases (product_id, supplier_id, quantity_purchased, purchase_date, total_cost) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.setInt(2, sid);
            ps.setInt(3, qty);
            ps.setString(4, date);
            ps.setDouble(5, cost);
            ps.executeUpdate();

            System.out.println("✅ Purchase recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- SALES OPERATIONS ----------------
    public static void insertSale() {
        try (Connection con = getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter product ID: ");
            int pid = sc.nextInt();
            System.out.print("Enter quantity sold: ");
            int qty = sc.nextInt();
            System.out.print("Enter total price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter customer name: ");
            String cname = sc.nextLine();
            System.out.print("Enter sale date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            String sql = "INSERT INTO sales (product_id, quantity_sold, sale_date, customer_name, total_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.setInt(2, qty);
            ps.setString(3, date);
            ps.setString(4, cname);
            ps.setDouble(5, price);
            ps.executeUpdate();

            System.out.println("✅ Sale recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- MAIN MENU ----------------
    public static void inventory(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Insert Supplier");
            System.out.println("5. Update Supplier");
            System.out.println("6. Delete Supplier");
            System.out.println("7. Insert Purchase");
            System.out.println("8. Insert Sale");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> insertProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProduct();
                case 4 -> insertSupplier();
                case 5 -> updateSupplier();
                case 6 -> deleteSupplier();
                case 7 -> insertPurchase();
                case 8 -> insertSale();
                case 9 -> {
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}

