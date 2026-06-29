package com.bridgeLabz.EmployeePayroll;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
class Employee{
    //private variables
    private String empId;
    private String name;
    private String email;
    private String phone;

    //default constructor
    public Employee() {
        System.out.print("Zero parameterized constructor");
    }
    // Parameterized constructor
    public Employee(String empId, String name, String email, String phone) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    // Getter methods
    public String getEmpId() {
        return empId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    // toString() method
    @Override
    public String toString() {
        return empId + "," + name + "," + email + "," + phone;
    }
}
class UserAccount{
    private String username;
    private String password;
    // Constructor
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
public class EmployeePayrollUc1 {

    // Method to validate Employee ID
    public static boolean isValidEmpId(String id) {
        return Pattern.matches("EMP-[0-9]{4}", id);
    }

    // Method to validate Email
    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    // Method to validate Phone Number
    public static boolean isValidPhone(String phone) {
        return Pattern.matches("[6-9][0-9]{9}", phone);
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== USE CASE 1 : EMPLOYEE REGISTRATION ===");

        // Employee ID
        String empId;

        while (true) {
            System.out.print("Enter Employee ID (EMP-XXXX): ");
            empId = sc.nextLine();

            if (isValidEmpId(empId))
                break;

            System.out.println("Invalid Employee ID!");
        }

        // Name
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        // Email
        String email;

        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();

            if (isValidEmail(email))
                break;

            System.out.println("Invalid Email!");
        }

        // Phone
        String phone;

        while (true) {
            System.out.print("Enter Phone Number: ");
            phone = sc.nextLine();

            if (isValidPhone(phone))
                break;

            System.out.println("Invalid Phone Number!");
        }

        // Username
        System.out.print("Create Username: ");
        String username = sc.nextLine();

        // Password
        System.out.print("Create Password: ");
        String password = sc.nextLine();

        // Creating objects
        Employee employee = new Employee(empId, name, email, phone);
        UserAccount account = new UserAccount(username, password);

        // Save data into file
        try {

            FileWriter writer = new FileWriter("employee_data.txt", true);

            writer.write(employee.getEmpId() + ",");
            writer.write(employee.getName() + ",");
            writer.write(employee.getEmail() + ",");
            writer.write(employee.getPhone() + ",");
            writer.write(account.getUsername() + ",");
            writer.write(account.getPassword());
            writer.write("\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error while saving file.");
        }

        // Display output
        System.out.println("\n-------------------------------------");
        System.out.println("Employee Registered Successfully!");
        System.out.println("-------------------------------------");

        System.out.println("Employee ID : " + employee.getEmpId());
        System.out.println("Name        : " + employee.getName());
        System.out.println("Email       : " + employee.getEmail());
        System.out.println("Phone       : " + employee.getPhone());
        System.out.println("Username    : " + account.getUsername());

        System.out.println("\nData persisted in file : employee_data.txt");

        sc.close();
    }
}
