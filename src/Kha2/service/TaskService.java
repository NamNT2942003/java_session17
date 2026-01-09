package Kha2.service;

import Kha2.entity.Task;
import Kha2.dao.TaskDAO;

import java.util.List;
import java.util.Scanner;

public class TaskService {
    private final TaskDAO taskDAO = new TaskDAO();
    private final Scanner scanner = new Scanner(System.in);

    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    private Task findTaskById(int id) {
        List<Task> list = taskDAO.getAllTasks();
        for (Task t : list) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public void addTask() {
        Task t = new Task();
        t.inputData(scanner); // Tự nhập liệu

        if (t.getTaskName().trim().isEmpty()) {
            System.out.println("❌ Tên công việc không được để trống!");
            return;
        }

        if (taskDAO.addTask(t)) {
            System.out.println("✅ Thêm công việc thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    public void updateTaskStatus() {
        System.out.print("Nhập ID công việc cần đổi trạng thái: ");
        int id = Integer.parseInt(scanner.nextLine());

        Task currentTask = findTaskById(id);
        if (currentTask == null) {
            System.out.println("⚠️ Không tìm thấy ID: " + id);
            return;
        }
        System.out.println("Trạng thái hiện tại: " + currentTask.getStatus());
        System.out.print("Chọn trạng thái mới (1: Chưa hoàn thành, 2: Đã hoàn thành): ");
        String choice = scanner.nextLine();
        String newStatus = choice.equals("2") ? "Đã hoàn thành" : "Chưa hoàn thành";

        if (taskDAO.updateStatus(id, newStatus)) {
            System.out.println("✅ Cập nhật trạng thái thành công!");
        } else {
            System.out.println("❌ Lỗi khi cập nhật!");
        }
    }

    public void deleteTask() {
        System.out.print("Nhập ID công việc cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (findTaskById(id) == null) {
            System.out.println("⚠️ Không tìm thấy ID: " + id);
            return;
        }

        if (taskDAO.deleteTask(id)) {
            System.out.println("✅ Đã xóa công việc!");
        }
    }

    public void searchTask() {
        System.out.print("Nhập tên công việc cần tìm: ");
        String keyword = scanner.nextLine();

        List<Task> results = taskDAO.searchTasksByName(keyword);
        if (results.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy công việc nào chứa: " + keyword);
        } else {
            System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
            for (Task t : results) System.out.println(t);
        }
    }

    public void showStatistics() {
        int[] stats = taskDAO.getStatistics();
        System.out.println("\n--- THỐNG KÊ CÔNG VIỆC ---");
        System.out.println("✅ Đã hoàn thành : " + stats[0]);
        System.out.println("⏳ Chưa hoàn thành: " + stats[1]);
        System.out.println("--------------------------");
    }
}