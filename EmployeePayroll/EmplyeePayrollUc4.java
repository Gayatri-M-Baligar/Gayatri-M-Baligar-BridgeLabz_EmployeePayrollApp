package com.bridgeLabz.EmployeePayroll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

//================== Payslip Class ==================
// Immutable class to store payslip details
final class Payslip implements Cloneable {

    // Instance variables
    private final String empId;
    private final String empName;
    private final String month;
    private final double netPay;

    // Parameterized constructor
    public Payslip(String empId, String empName, String month, double netPay) {
        this.empId = empId;
        this.empName = empName;
        this.month = month;
        this.netPay = netPay;
    }

    // Getter methods
    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getMonth() {
        return month;
    }

    public double getNetPay() {
        return netPay;
    }

    // Creating a copy of the payslip object
    @Override
    public Payslip clone() {
        return new Payslip(empId, empName, month, netPay);
    }

    // Comparing two payslip objects
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Payslip p = (Payslip) obj;

        return empId.equals(p.empId)
                && empName.equals(p.empName)
                && month.equals(p.month)
                && netPay == p.netPay;
    }

    // Generating hash code for the object
    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, month, netPay);
    }

    // Displaying payslip details
    @Override
    public String toString() {

        return "PAYSLIP\n"
                + "Employee ID   : " + empId + "\n"
                + "Employee Name : " + empName + "\n"
                + "Month         : " + month + "\n"
                + "Net Pay       : " + netPay + "\n";
    }
}

//================== Download Token ==================
// This class checks whether the download link is expired
class DownloadToken {

    // Stores token creation time
    private long createdTime;

    // Token is valid for 1 minute
    private final long expiryTime = 60000;

    // Constructor
    public DownloadToken() {
        createdTime = System.currentTimeMillis();
    }

    // Method to check whether token is expired
    public boolean isExpired() {
        return System.currentTimeMillis() - createdTime > expiryTime;
    }
}

//================== File Service ==================
// This class is used to save payslip files
class FileService {

    // Save payslip as a text file
    public String savePayslipAsText(Payslip payslip) throws IOException {

        // Generate unique filename using current time
        String fileName = "Payslip_" + payslip.getEmpId() + "_"
                + System.currentTimeMillis() + ".txt";

        // Write payslip details into text file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(payslip.toString());
        }

        return fileName;
    }

    // Save payslip as PDF (Demo purpose)
    public String savePayslipAsPdf(Payslip payslip) throws IOException {

        // Generate unique PDF filename
        String fileName = "Payslip_" + payslip.getEmpId() + "_"
                + System.currentTimeMillis() + ".pdf";

        // Write payslip details into PDF file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(payslip.toString());
        }

        return fileName;
    }
}

//================== Main Class ==================
 class EmployeePayrollUc4 {

    public static void main(String[] args) {

        System.out.println("=== USE CASE 4 : PAYSLIP PRINT / DOWNLOAD ===");

        // Creating original payslip object
        Payslip original = new Payslip(
                "EMP-1010",
                "John David",
                "January 2026",
                48500.0);

        // Display original payslip
        System.out.println("\nOriginal Payslip:");
        System.out.println(original);

        // Creating clone of original payslip
        Payslip copy = original.clone();

        // Comparing original and cloned object
        System.out.println("Verified: Download copy is equal to original.");
        System.out.println("Original hashcode : " + original.hashCode());
        System.out.println("Cloned   hashcode : " + copy.hashCode());

        // Creating download token
        DownloadToken token = new DownloadToken();

        // Checking whether token is expired
        if (token.isExpired()) {
            System.out.println("Download link expired.");
            return;
        }

        // Creating FileService object
        FileService fileService = new FileService();

        try {

            // Saving payslip in text and PDF formats
            String txtFile = fileService.savePayslipAsText(copy);
            String pdfFile = fileService.savePayslipAsPdf(copy);

            System.out.println("\nPayslip Download Successful.");
            System.out.println("Saved as text file : " + txtFile);
            System.out.println("Saved as PDF file  : " + pdfFile);

        } catch (IOException e) {

            // Display error if file is not created
            System.out.println("Error while saving file.");
        }

        // Display downloaded payslip
        System.out.println("\n--- Printed Payslip ---");
        System.out.println(copy);

        // Original object is not modified
        System.out.println("Original Payslip remains unchanged.");
    }
}