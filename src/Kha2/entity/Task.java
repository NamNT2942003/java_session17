package Kha2.entity;

import java.util.Scanner;

public class Task {
    private int id;
    private String taskName;
    private String status;

    public Task() {}

    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-40s | %-20s |", id, taskName, status);
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên công việc: ");
        this.taskName = scanner.nextLine();
        while (true) {
            System.out.print("Nhập trạng thái (1: Chưa hoàn thành, 2: Đã hoàn thành): ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                this.status = "Chưa hoàn thành";
                break;
            } else if (choice.equals("2")) {
                this.status = "Đã hoàn thành";
                break;
            } else {
                System.out.println("⚠️ Vui lòng chỉ chọn 1 hoặc 2!");
            }
        }
    }
}
