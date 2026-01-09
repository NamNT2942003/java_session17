package Gioi2.manager;

import Gioi2.entity.Customer;
import Gioi2.entity.Order;
import Gioi2.entity.Product;
import Gioi2.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    // --- PHẦN SẢN PHẨM ---
    public void addProduct(Product p) {
        // Check trùng tên
        String checkSql = "SELECT count(*) FROM products WHERE name ILIKE ?";
        String insertSql = "INSERT INTO products(name, price) VALUES(?, ?)";

        try (Connection conn = ConnectionDB.openConnection()) {
            // Bước 1: Kiểm tra tồn tại
            PreparedStatement pstCheck = conn.prepareStatement(checkSql);
            pstCheck.setString(1, p.getName());
            ResultSet rs = pstCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("⚠️ Sản phẩm đã tồn tại!");
                return;
            }

            // Bước 2: Thêm mới
            PreparedStatement pstInsert = conn.prepareStatement(insertSql);
            pstInsert.setString(1, p.getName());
            pstInsert.setDouble(2, p.getPrice());
            pstInsert.executeUpdate();
            System.out.println("✅ Thêm sản phẩm thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (Exception e) {}
        return list;
    }

    // --- PHẦN KHÁCH HÀNG ---
    public void addCustomer(Customer c) {
        String sql = "INSERT INTO customers(name, email) VALUES(?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getName());
            pst.setString(2, c.getEmail());
            pst.executeUpdate();
            System.out.println("✅ Thêm khách hàng thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi: Có thể Email đã trùng!");
        }
    }

    public void updateCustomer(int id, Customer c) {
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getName());
            pst.setString(2, c.getEmail());
            pst.setInt(3, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("✅ Cập nhật khách hàng thành công!");
            else System.out.println("⚠️ Không tìm thấy khách hàng ID: " + id);
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cập nhật: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM customers")) {
            while (rs.next()) {
                list.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (Exception e) {}
        return list;
    }

    // --- PHẦN ĐƠN HÀNG (QUAN TRỌNG) ---

    // Hàm phụ: Lấy giá sản phẩm để tính tổng tiền
    public double getProductPrice(int productId) {
        String sql = "SELECT price FROM products WHERE id = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getDouble("price");
        } catch (Exception e) {}
        return 0;
    }

    public void createOrder(int customerId, double totalAmount) {
        String sql = "INSERT INTO orders(customer_id, total_amount, order_date) VALUES(?, ?, CURRENT_DATE)";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            pst.setDouble(2, totalAmount);
            pst.executeUpdate();
            System.out.println("✅ Tạo đơn hàng thành công! Tổng tiền: " + totalAmount);
        } catch (SQLException e) {
            System.out.println("❌ Lỗi tạo đơn (Kiểm tra lại ID khách hàng): " + e.getMessage());
        }
    }

    public List<Order> listAllOrders() {
        List<Order> list = new ArrayList<>();
        // JOIN bảng orders với customers để lấy tên khách hàng
        String sql = "SELECT o.id, o.customer_id, c.name, o.order_date, o.total_amount " +
                "FROM orders o " +
                "JOIN customers c ON o.customer_id = c.id " +
                "ORDER BY o.id DESC";

        try (Connection conn = ConnectionDB.openConnection();
             ResultSet rs = conn.createStatement().executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("name"), // Lấy tên từ bảng customers
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getOrdersByCustomer(int customerId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.id, o.customer_id, c.name, o.order_date, o.total_amount " +
                "FROM orders o " +
                "JOIN customers c ON o.customer_id = c.id " +
                "WHERE o.customer_id = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {}
        return list;
    }
}
