package org.example;

public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double availBalance;

    public BankAccount(String accountNumber, String accountHolderName, double initialDepositAmount){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.availBalance = initialDepositAmount;
    }

    public BankAccount(String accountNumber, String accountHolderName){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    public void deposit(double depositMoney){
        this.availBalance += depositMoney;
    }

    public void withdraw(double withdrawMoney){
        this.availBalance -= withdrawMoney;
    }

    public void displayInformation(){
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder Name: " + getAccountHolderName());
        System.out.printf("Available Balance: %.2f%n", getAvailBalance());
    }


    //Getters and Setters
    public double getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(double availBalance) {
        this.availBalance = availBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}