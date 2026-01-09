package Gioi1.entity;

import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishedYear;
    private double price;
    public Book() {}

    public Book(int id, String title, String author, int publishedYear, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-30s | %-20s | %-6d | %-10.2f |",
                id, title, author, publishedYear, price);
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập tiêu đề sách: ");
        this.title = scanner.nextLine().trim();

        System.out.print("Nhập tên tác giả: ");
        this.author = scanner.nextLine().trim();

        while (true) {
            try {
                System.out.print("Nhập năm xuất bản: ");
                this.publishedYear = Integer.parseInt(scanner.nextLine().trim());
                if (this.publishedYear > 0) break;
                System.out.println("⚠️ Năm phải lớn hơn 0!");
            } catch (Exception e) {
                System.out.println("⚠️ Nhập sai định dạng năm!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập giá tiền: ");
                this.price = Double.parseDouble(scanner.nextLine().trim());
                if (this.price >= 0) break;
                System.out.println("⚠️ Giá không được âm!");
            } catch (Exception e) {
                System.out.println("⚠️ Nhập sai định dạng giá!");
            }
        }
    }
}