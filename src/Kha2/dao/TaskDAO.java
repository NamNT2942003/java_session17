package Kha2.dao;

import Kha2.entity.Task;
import Kha2.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private List<Task> mapResultSetToTasks(String query) {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("out_id"));
                t.setTaskName(rs.getString("out_name"));
                t.setStatus(rs.getString("out_status"));
                tasks.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Read): " + e.getMessage());
        }
        return tasks;
    }


    public List<Task> getAllTasks() {
        return mapResultSetToTasks("SELECT * FROM list_tasks()");
    }

    public List<Task> searchTasksByName(String keyword) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM search_task_by_name(?)";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, keyword);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getInt("out_id"),
                        rs.getString("out_name"),
                        rs.getString("out_status")
                );
                tasks.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Search): " + e.getMessage());
        }
        return tasks;
    }

    public boolean addTask(Task task) {
        String sql = "call add_task(?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, task.getTaskName());
            cstmt.setString(2, task.getStatus());
            cstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Add): " + e.getMessage());
            return false;
        }
    }

    public boolean updateStatus(int id, String status) {
        String sql = "call update_task_status(?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, id);
            cstmt.setString(2, status);
            cstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Update): " + e.getMessage());
            return false;
        }
    }

    public boolean deleteTask(int id) {
        String sql = "call delete_task(?)";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, id);
            cstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Delete): " + e.getMessage());
            return false;
        }
    }

    public int[] getStatistics() {
        int[] stats = {0, 0};
        String sql = "SELECT * FROM task_statistics()";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                stats[0] = rs.getInt("total_done");
                stats[1] = rs.getInt("total_pending");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAO (Stats): " + e.getMessage());
        }
        return stats;
    }
}