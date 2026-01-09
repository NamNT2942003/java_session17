package Kha1.Entity;

import java.util.Scanner;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;

    public Movie() {
    }

    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return String.format("| %-4d | %-30s | %-20s | %-6d |", id, title, director, year);
    }
    public void inputData(Scanner scanner) {
        System.out.println("Nhập tiêu đề phim: ");
        this.title = scanner.nextLine();
        System.out.println("Nhập tên đạo diễn: ");
        this.director = scanner.nextLine();
        do {
            try{
                System.out.println("Nhập năm sản xuất: ");
                this.year = Integer.parseInt(scanner.nextLine());
                if (this.year >0){
                    break;
                }else {
                    System.out.println("Năm sản xuất phải lớn hơn 0!");
                }

            }catch(Exception e){
                System.out.println("Lỗi: vui lòng nhập số nguyên hợp lệ cho năm! ");
            }

        }while (true);
    }
}
