package Kha2.main;

import Kha2.entity.Task;
import Kha2.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TaskService service = new TaskService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    showList();
                    break;
                case 2:
                    service.addTask();
                    break;
                case 3:
                    service.updateTaskStatus();
                    break;
                case 4:
                    service.deleteTask();
                    break;
                case 5:
                    service.searchTask();
                    break;
                case 6:
                    service.showStatistics();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    private static void showList() {
        List<Task> list = service.getAllTasks();
        System.out.println("\n--- DANH SÁCH TO-DO ---");
        System.out.printf("| %-4s | %-40s | %-20s |\n", "ID", "TÊN CÔNG VIỆC", "TRẠNG THÁI");
        System.out.println("-------------------------------------------------------------------------");
        for (Task t : list) {
            System.out.println(t);
        }
    }

    private static void printMenu() {
        System.out.println("\n===== TO-DO LIST MANAGER =====");
        System.out.println("1. Xem danh sách công việc");
        System.out.println("2. Thêm công việc mới");
        System.out.println("3. Cập nhật trạng thái");
        System.out.println("4. Xóa công việc");
        System.out.println("5. Tìm kiếm công việc");
        System.out.println("6. Thống kê");
        System.out.println("0. Thoát");
        System.out.println("==============================");
    }

    private static int getIntInput(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }
}