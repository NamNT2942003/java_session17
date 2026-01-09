package Gioi1.service;

import Gioi1.entity.Book;
import Gioi1.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private boolean isBookExist(String title, String author) {
        String sql = "SELECT count(*) FROM books WHERE title ILIKE ? AND author ILIKE ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addBook(Book book) {
        if (isBookExist(book.getTitle(), book.getAuthor())) {
            System.out.println("⚠️ Sách này đã tồn tại (Trùng tên và tác giả)!");
            return;
        }

        String sql = "INSERT INTO books (title, author, published_year, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setDouble(4, book.getPrice());

            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Thêm sách thành công!");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi thêm sách: " + e.getMessage());
        }
    }

    public void updateBook(int id, Book book) {
        String sql = "UPDATE books SET title=?, author=?, published_year=?, price=? WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setDouble(4, book.getPrice());
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật thành công!");
            } else {
                System.out.println("⚠️ Không tìm thấy sách có ID = " + id);
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi cập nhật: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Đã xóa sách ID " + id);
            } else {
                System.out.println("⚠️ Không tìm thấy sách ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi xóa sách: " + e.getMessage());
        }
    }

    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author ILIKE ?";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + authorName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> listAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY id";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}