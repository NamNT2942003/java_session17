package Gioi1.main;

import Gioi1.entity.Book;
import Gioi1.service.BookManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BookManager manager = new BookManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    System.out.println("\n--- THÊM SÁCH ---");
                    Book newBook = new Book();
                    newBook.inputData(scanner);
                    manager.addBook(newBook);
                    break;

                case 2:
                    System.out.println("\n--- CẬP NHẬT SÁCH ---");
                    int updateId = getIntInput("Nhập ID sách cần sửa: ");
                    Book infoBook = new Book();
                    infoBook.inputData(scanner);
                    manager.updateBook(updateId, infoBook);
                    break;

                case 3:
                    System.out.println("\n--- XÓA SÁCH ---");
                    int deleteId = getIntInput("Nhập ID sách cần xóa: ");
                    manager.deleteBook(deleteId);
                    break;

                case 4:
                    System.out.println("\n--- TÌM KIẾM THEO TÁC GIẢ ---");
                    System.out.print("Nhập tên tác giả: ");
                    String authorKey = scanner.nextLine();
                    List<Book> foundList = manager.findBooksByAuthor(authorKey);
                    displayList(foundList);
                    break;

                case 5:
                    System.out.println("\n--- DANH SÁCH TOÀN BỘ SÁCH ---");
                    List<Book> allList = manager.listAllBooks();
                    displayList(allList);
                    break;

                case 0:
                    System.out.println("Tạm biệt!");
                    return;

                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    private static void displayList(List<Book> list) {
        if (list.isEmpty()) {
            System.out.println("(Không tìm thấy dữ liệu)");
            return;
        }
        System.out.printf("| %-4s | %-30s | %-20s | %-6s | %-10s |\n",
                "ID", "TỰA SÁCH", "TÁC GIẢ", "NĂM", "GIÁ");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Book b : list) {
            System.out.println(b);
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    private static void printMenu() {
        System.out.println("\n====== QUẢN LÝ THƯ VIỆN ======");
        System.out.println("1. Thêm sách mới");
        System.out.println("2. Cập nhật thông tin sách");
        System.out.println("3. Xóa sách");
        System.out.println("4. Tìm kiếm theo tác giả");
        System.out.println("5. Hiển thị tất cả sách");
        System.out.println("0. Thoát");
        System.out.println("==============================");
    }

    private static int getIntInput(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }
}