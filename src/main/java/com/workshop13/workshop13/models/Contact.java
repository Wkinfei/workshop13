package com.workshop13.workshop13.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message = "Name cannot be null")
    @Size(min=2, max=32, message="Name must be between 2 and 32 characters")
    private String name;

    @Email(message="Must be a valid email")
    private String email;

    @Min(value=7, message="phone number must contain at least 7 digits")
    private String phoneNumber;

    
    private String dateOfBirth;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //Create a method to validate DOB return boolean
    // DateTimeFormatter (DD-MM-YYYY)
    //LocalDate.parse
    //FieldError(String objectName, String field, String defaultMessage)
    public boolean invalidDateOfBirth(BindingResult bindingResult) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        // System.out.println("testing dob");
        LocalDate date = null;
        FieldError err = null;
        
        // Check Format dd-MMM-yyyy
        try {
            date = LocalDate.parse(this.getDateOfBirth(), formatter);
            
        } catch (DateTimeParseException e) {
            // System.out.println("failed");
            err = new FieldError("fieldError","dateOfBirth", "date must be in dd-Mmm-yyyy");
            bindingResult.addError(err);
            return true;
        }

        // Check past
        if(date.isAfter(LocalDate.now())) {
            err = new FieldError("fieldError","dateOfBirth", "date must be past");
            bindingResult.addError(err);
            return true;
        }
            
        // Check Age between 10 and 100  (now = 2023)
        LocalDate latestDate = LocalDate.now().minusYears(10); // 2013
        LocalDate earliestDate = LocalDate.now().minusYears(100); // 1923
        if(date.isBefore(earliestDate) || date.isAfter(latestDate)) {
            err = new FieldError("fieldError","dateOfBirth", "you are not between 10-100 years old");
            bindingResult.addError(err);
            return true;
        }

        return false;

    }
}

