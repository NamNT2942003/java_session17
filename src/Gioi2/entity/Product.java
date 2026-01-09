package Gioi2.entity;

import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public void inputData(Scanner sc) {
        System.out.print("Nhập tên sản phẩm: ");
        this.name = sc.nextLine();
        System.out.print("Nhập giá: ");
        this.price = Double.parseDouble(sc.nextLine());
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %.0f VNĐ", id, name, price);
    }
}
