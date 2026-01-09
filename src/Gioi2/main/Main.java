package Gioi2.main;

import Gioi2.entity.Customer;
import Gioi2.entity.Order;
import Gioi2.entity.Product;
import Gioi2.manager.OrderManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    static OrderManager manager = new OrderManager();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== QUẢN LÝ CỬA HÀNG ===");
            System.out.println("1. Thêm Sản Phẩm");
            System.out.println("2. Thêm Khách Hàng");
            System.out.println("3. Cập nhật Khách Hàng");
            System.out.println("4. 🛒 TẠO ĐƠN HÀNG");
            System.out.println("5. Danh sách Đơn Hàng");
            System.out.println("6. Tìm đơn hàng của Khách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    Product p = new Product();
                    p.inputData(sc);
                    manager.addProduct(p);
                    break;
                case 2:
                    Customer c = new Customer();
                    c.inputData(sc);
                    manager.addCustomer(c);
                    break;
                case 3:
                    System.out.print("Nhập ID khách cần sửa: ");
                    int cid = Integer.parseInt(sc.nextLine());
                    Customer cUpdate = new Customer();
                    cUpdate.inputData(sc);
                    manager.updateCustomer(cid, cUpdate);
                    break;
                case 4:
                    handleCreateOrder();
                    break;
                case 5:
                    List<Order> orders = manager.listAllOrders();
                    if (orders.isEmpty()) System.out.println("Chưa có đơn hàng nào.");
                    for (Order o : orders) System.out.println(o);
                    break;
                case 6:
                    System.out.print("Nhập ID khách hàng: ");
                    int findId = Integer.parseInt(sc.nextLine());
                    List<Order> cusOrders = manager.getOrdersByCustomer(findId);
                    if (cusOrders.isEmpty()) System.out.println("Khách này chưa mua gì.");
                    for (Order o : cusOrders) System.out.println(o);
                    break;
                case 0: return;
            }
        }
    }

    // Hàm xử lý tạo đơn hàng riêng cho gọn
    public static void handleCreateOrder() {
        // 1. Hiện danh sách khách để chọn
        System.out.println("--- Chọn Khách Hàng ---");
        List<Customer> customers = manager.getAllCustomers();
        for (Customer c : customers) System.out.println(c);

        System.out.print("=> Nhập ID Khách hàng mua: ");
        int cusId = Integer.parseInt(sc.nextLine());

        // 2. Hiện danh sách sản phẩm để chọn
        System.out.println("--- Chọn Sản Phẩm ---");
        List<Product> products = manager.getAllProducts();
        for (Product p : products) System.out.println(p);

        System.out.print("=> Nhập ID Sản phẩm muốn mua: ");
        int proId = Integer.parseInt(sc.nextLine());

        System.out.print("=> Nhập Số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        // 3. Tính toán tổng tiền
        double price = manager.getProductPrice(proId);
        if (price == 0) {
            System.out.println("❌ ID Sản phẩm không tồn tại!");
            return;
        }

        double total = price * quantity;

        // 4. Lưu vào DB
        manager.createOrder(cusId, total);
    }
}