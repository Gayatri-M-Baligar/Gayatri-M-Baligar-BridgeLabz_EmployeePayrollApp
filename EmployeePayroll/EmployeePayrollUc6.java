package com.bridgeLabz.EmployeePayroll;

import java.util.*;

// Parent exception class
class ValidationExceptions extends Exception{
    public ValidationExceptions(String message){
        super(message);
    }
}

// Exception for invalid email
class EmailValidationException extends ValidationExceptions{
    public EmailValidationException(String message) {
        super(message);
    }
}

// Exception for invalid phone number
class phoneValidationException extends ValidationExceptions{
    public phoneValidationException(String message) {
        super(message);
    }
}

// Exception for invalid password
class passwordValidationException extends ValidationExceptions{
    public passwordValidationException(String message) {
        super(message);
    }
}

// Exception for invalid employee ID
class EmployeeIdValidationException extends ValidationExceptions{
    public EmployeeIdValidationException(String message) {
        super(message);
    }
}

// Validation class
class ValidationService {

    // Removes extra spaces from input
    private static String sanitize(String input) {

        if (input == null) {
            return "";
        }

        return input.trim();
    }

    // Validate Email
    public static void validateEmail(String email)
            throws EmailValidationException {

        email = sanitize(email);

        String regex = "^[A-Za-z0-9][A-Za-z0-9+_.-]*@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new EmailValidationException("Invalid Email Address.");
        }
    }

    // Validate Phone Number
    public static void validatePhone(String phone)
            throws phoneValidationException {

        phone = sanitize(phone);

        String regex = "^[6-9][0-9]{9}$";

        if (!phone.matches(regex)) {
            throw new phoneValidationException(
                    "Invalid Phone Number. Must be 10 digits starting with 6-9."
            );
        }
    }

    // Validate Password
    public static void validatePassword(String password)
            throws passwordValidationException {

        password = sanitize(password);

        String regex =
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (!password.matches(regex)) {
            throw new passwordValidationException(
                    "Password must contain uppercase, lowercase, digit, special character and minimum 8 characters."
            );
        }
    }

    // Validate Employee ID
    public static void validateEmployeeId(String empId)
            throws EmployeeIdValidationException {

        empId = sanitize(empId);

        String regex = "^EMP-[0-9]{4}$";

        if (!empId.matches(regex)) {
            throw new EmployeeIdValidationException(
                    "Employee ID must be in EMP-XXXX format."
            );
        }
    }
}

// Main class
class EmployeePayrollUc6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Use Case 6 : Input Validation App ===");

        try {

            // Read Employee ID
            System.out.print("Enter Employee ID: ");
            String empID = sc.next();
            ValidationService.validateEmployeeId(empID);

            // Read Email
            System.out.print("Enter Email ID: ");
            String email = sc.next();
            ValidationService.validateEmail(email);

            // Read Phone Number
            System.out.print("Enter Phone Number: ");
            String phonenum = sc.next();
            ValidationService.validatePhone(phonenum);

            // Read Password
            System.out.print("Enter Password: ");
            String password = sc.next();
            ValidationService.validatePassword(password);

            // Display success message
            System.out.println("\nValidation Successful.");
            System.out.println("Login/Register can proceed.");

        } catch (ValidationExceptions e) {

            // Display validation error
            System.out.println("\nValidation Failed.");
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}