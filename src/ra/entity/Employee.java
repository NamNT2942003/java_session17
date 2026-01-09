package ra.entity;

import java.util.Scanner;

public class Employee {
    private int empId;
    private String empName;
    private int empAge;
    private double empSalary;
    private boolean empStatus;

    public Employee() {
    }

    public Employee(int empId, String empName, int empAge, double empSalary, boolean empStatus) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
        this.empSalary = empSalary;
        this.empStatus = empStatus;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public boolean isEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(boolean empStatus) {
        this.empStatus = empStatus;
    }
    public void inputData(Scanner scanner){
        System.out.println("Nhap vao ten : ");
        this.empName = scanner.nextLine();
        System.out.println("Nhap vao tuoi : ");
        this.empAge = scanner.nextInt();
        System.out.println("Nhap vao luong : ");
        this.empSalary = scanner.nextDouble();
    }
    @Override
    public String toString() {
        return String.format("");
    }
}
