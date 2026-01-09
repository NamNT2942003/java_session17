package Kha1.main; // Hoặc package Kha1; tùy bạn

import Kha1.Entity.Movie;
import Kha1.service.MovieService;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Khởi tạo Service và Scanner dùng chung
    private static final MovieService movieService = new MovieService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Mời bạn chọn chức năng: ");

            switch (choice) {
                case 1:
                    showAllMovies();
                    break;
                case 2:
                    addNewMovie();
                    break;
                case 3:
                    // Logic update đã nằm hết trong Service, chỉ việc gọi
                    movieService.updateMovie();
                    break;
                case 4:
                    // Logic delete đã nằm hết trong Service
                    movieService.deleteMovie();
                    break;
                case 0:
                    System.out.println("👋 Tạm biệt! Hẹn gặp lại.");
                    return; // Kết thúc chương trình
                default:
                    System.out.println("⚠️ Chức năng không tồn tại! Vui lòng chọn lại.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("\n================ QUẢN LÝ PHIM ================");
        System.out.println("1. 📋 Xem danh sách phim");
        System.out.println("2. ➕ Thêm phim mới");
        System.out.println("3. ✏️ Sửa thông tin phim");
        System.out.println("4. 🗑️ Xóa phim");
        System.out.println("0. 🚪 Thoát");
        System.out.println("==============================================");
    }

    private static void showAllMovies() {
        List<Movie> list = movieService.getAllMovies();

        if (list.isEmpty()) {
            System.out.println("⚠️ Danh sách phim đang trống!");
            return;
        }

        System.out.println("\n--- DANH SÁCH PHIM ---");
        System.out.printf("| %-4s | %-30s | %-20s | %-6s |\n", "ID", "TÊN PHIM", "ĐẠO DIỄN", "NĂM");
        System.out.println("-------------------------------------------------------------------------");

        for (Movie m : list) {
            System.out.println(m.toString());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    private static void addNewMovie() {
        System.out.println("\n--- THÊM PHIM MỚI ---");
        Movie newMovie = new Movie();
        movieService.addMovie(newMovie);
    }
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
    }
}