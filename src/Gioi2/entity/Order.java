package Gioi2.entity;
import java.sql.Date;

public class Order {
    private int id;
    private int customerId;
    private String customerName; // Dùng để hiển thị tên khi Join bảng
    private Date orderDate;
    private double totalAmount;

    public Order() {}

    public Order(int id, int customerId, String customerName, Date orderDate, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return String.format("| ID: %-4d | KH: %-20s | Ngày: %-10s | Tổng: %-12.0f |",
                id, customerName, orderDate, totalAmount);
    }
}