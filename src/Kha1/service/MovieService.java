package Kha1.service;

import Kha1.Entity.Movie;
import Kha1.dao.MoiveDAO;

import java.util.List;
import java.util.Scanner;

public class MovieService {
    private final MoiveDAO moiveDAO = new MoiveDAO();
    Scanner scanner = new Scanner(System.in);

    public List<Movie> getAllMovies() {
        return moiveDAO.getAllMovies();
    }

    public void addMovie(Movie movie) {
        movie.inputData(scanner);
        boolean result = moiveDAO.addMovie(movie);
        if (result) {
            System.out.println("✅ Thêm phim thành công!");
        } else {
            System.out.println("❌ Thêm phim thất bại!");
        }
    }

    private Movie findMovieById(int id) {
        List<Movie> movies = moiveDAO.getAllMovies();
        for (Movie m : movies) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void deleteMovie() {
        System.out.print("Nhập ID phim cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        Movie movie = findMovieById(id);

        if (movie == null) {
            System.out.println("⚠️ Không tìm thấy phim có ID = " + id);
            return;
        }

        boolean result = moiveDAO.deleteMovie(id);
        if (result) {
            System.out.println("✅ Đã xóa phim: " + movie.getTitle());
        } else {
            System.out.println("❌ Xóa thất bại!");
        }
    }

    public void updateMovie() {
        System.out.print("Nhập ID phim cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Movie movie = findMovieById(id);
        if (movie == null) {
            System.out.println("⚠️ Không tìm thấy phim có ID = " + id);
            return;
        }
        System.out.println("--- THÔNG TIN PHIM CŨ ---");
        System.out.println(movie);
        boolean isEditing = true;
        while (isEditing) {
            System.out.println("\n--- CHỌN THÔNG TIN MUỐN SỬA ---");
            System.out.println("1. Sửa Tiêu đề (Title)");
            System.out.println("2. Sửa Đạo diễn (Director)");
            System.out.println("3. Sửa Năm phát hành (Year)");
            System.out.println("0. Lưu và Thoát");
            System.out.print("Chọn: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    System.out.print("Nhập tiêu đề mới: ");
                    String newTitle = scanner.nextLine();
                    movie.setTitle(newTitle);
                    System.out.println("-> Đã cập nhật tiêu đề tạm thời.");
                    break;
                case 2:
                    System.out.print("Nhập đạo diễn mới: ");
                    String newDirector = scanner.nextLine();
                    movie.setDirector(newDirector);
                    System.out.println("-> Đã cập nhật đạo diễn tạm thời.");
                    break;
                case 3:
                    System.out.print("Nhập năm mới: ");
                    try {
                        int newYear = Integer.parseInt(scanner.nextLine());
                        movie.setYear(newYear);
                        System.out.println("-> Đã cập nhật năm tạm thời.");
                    } catch (Exception e) {
                        System.out.println("⚠️ Năm phải là số!");
                    }
                    break;
                case 0:
                    boolean result = moiveDAO.updateMovie(movie);
                    if (result) {
                        System.out.println("✅ Cập nhật phim thành công vào CSDL!");
                    } else {
                        System.out.println("❌ Cập nhật thất bại!");
                    }
                    isEditing = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }

    }

}
