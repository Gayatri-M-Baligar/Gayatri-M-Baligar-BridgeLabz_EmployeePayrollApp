package com.bridgeLabz.EmployeePayroll;

import java.util.Scanner;

// Abstract class
abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Abstract method
    public abstract boolean authenticate(String username, String password);
}

// Employee class
class Employeee extends User {

    public Employeee(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean authenticate(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)) {

            System.out.println("\nLogin Successful");
            System.out.println("Role : EMPLOYEE");
            return true;
        }

        System.out.println("Invalid Credentials");
        return false;
    }
}

// Manager class
class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean authenticate(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)) {

            System.out.println("\nLogin Successful");
            System.out.println("Role : MANAGER");
            return true;
        }

        System.out.println("Invalid Credentials");
        return false;
    }
}

public class EmployeePayrollUc2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Dummy users
        Employeee employee = new Employeee("gayatri", "gayatri123");
        Manager manager = new Manager("bridgelabz", "bridgelabz123");
        System.out.println(" EMPLOYEE PAYROLL LOGIN ");
        System.out.println("1. Employee Login");
        System.out.println("2. Manager Login");
        System.out.print("Enter Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        User user = null;
        if (choice == 1) {
            user = employee;
        } else if (choice == 2) {
            user = manager;
        } else {
            System.out.println("Invalid Choice");
            sc.close();
            return;
        }
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("\nEnter Username : ");
            String username = sc.nextLine();
            System.out.print("Enter Password : ");
            String password = sc.nextLine();
            if (user.authenticate(username, password)) {
                System.out.println("\nSession Active");
                System.out.println("Welcome " + username);
                // Employee Dashboard
                if (user instanceof Employeee) {
                    System.out.println("\n===== EMPLOYEE DASHBOARD =====");
                    System.out.println("1. View Payslip");
                    System.out.println("2. Update Profile");
                    System.out.println("3. Logout");
                }
                // Manager Dashboard
                else if (user instanceof Manager) {
                    System.out.println("\n===== MANAGER DASHBOARD =====");
                    System.out.println("1. View Employee Details");
                    System.out.println("2. Generate Payroll");
                    System.out.println("3. Logout");

                }
                break;
            }
            attempts--;
            if (attempts > 0) {
                System.out.println("Attempts Left : " + attempts);
            } else {
                System.out.println("\nMaximum Login Attempts Reached.");
                System.out.println("Login Failed.");
            }
        }
    }
}