package com.bridgeLabz.EmployeePayroll;

import java.util.Scanner;

// Employee class (Aggregation)
class Employee {

    private String empId;
    private String empName;

    public Employee(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }
}

// SalaryComponents class (Composition)
class SalaryComponents {

    private double basicSalary;
    private double hra;
    private double da;
    private double allowances;

    public SalaryComponents(double basicSalary, double hra, double da, double allowances) {
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHra() {
        return hra;
    }

    public double getDa() {
        return da;
    }

    public double getAllowances() {
        return allowances;
    }
}

// Payslip class
class Payslip {

    private Employee employee;
    private SalaryComponents salary;
    private String month;

    public Payslip(Employee employee, SalaryComponents salary, String month) {
        this.employee = employee;
        this.salary = salary;
        this.month = month;
    }

    // Gross Salary
    public double calculateGrossSalary() {
        return salary.getBasicSalary()
                + salary.getHra()
                + salary.getDa()
                + salary.getAllowances();
    }

    // PF = 12% of Basic Salary
    public double calculatePF() {
        return salary.getBasicSalary() * 0.12;
    }

    // Tax = 10% of Gross Salary
    public double calculateTax() {
        return calculateGrossSalary() * 0.10;
    }
    // Net Salary
    public double calculateNetSalary() {
        return calculateGrossSalary() - calculatePF() - calculateTax();
    }
    @Override
    public String toString() {
        return "\n==========PAYSLIP========\n"
                + "Month          : " + month
                + "\nEmployee ID    : " + employee.getEmpId()
                + "\nEmployee Name  : " + employee.getEmpName()

                + "\n\n----- Earnings -----"
                + "\nBasic Salary : " + salary.getBasicSalary()
                + "\nHRA          : " + salary.getHra()
                + "\nDA           : " + salary.getDa()
                + "\nAllowances   : " + salary.getAllowances()

                + "\n\n----- Deductions -----"
                + "\nPF           : " + calculatePF()
                + "\nTax          : " + calculateTax()

                + "\n\nNet Pay      : " + calculateNetSalary()
                ;
    }
}

public class EmployeePayrollUc3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== USE CASE 3 : PAYSLIP GENERATION ===");

        System.out.print("Enter Employee ID : ");
        String empId = sc.nextLine();

        System.out.print("Enter Employee Name : ");
        String empName = sc.nextLine();

        System.out.print("Enter Month : ");
        String month = sc.nextLine();

        System.out.print("Enter Basic Salary : ");
        double basic = sc.nextDouble();

        System.out.print("Enter HRA : ");
        double hra = sc.nextDouble();

        System.out.print("Enter DA : ");
        double da = sc.nextDouble();

        System.out.print("Enter Allowances : ");
        double allowance = sc.nextDouble();
        Employee employee = new Employee(empId, empName);
        SalaryComponents salary = new SalaryComponents(
                basic,
                hra,
                da,
                allowance);
        Payslip payslip = new Payslip(employee, salary, month);
        System.out.println(payslip);
    }
}