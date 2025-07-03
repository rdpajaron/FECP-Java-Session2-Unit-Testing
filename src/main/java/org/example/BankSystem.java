package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BankSystem {

    static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    static HashMap<String, BankAccount> accountNumberAndAccount = new HashMap<>();

    public static void main(String[] args) {
        int option;
        boolean firstIteration = true;
        Scanner scanner = new Scanner(System.in);

        do {
            if(!firstIteration){
                System.out.print("Would you like to return to the menu? (yes/no): ");
                String input = scanner.nextLine().toLowerCase();
                if(!input.equals("yes")) {
                    System.out.println("Thank you!");
                    return;
                }
            }

            System.out.println("=== Bank Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.print("Enter choice (1-5): ");

            option = Integer.parseInt(scanner.nextLine());
            processOptions(option, scanner);
            firstIteration = false;
        }while(option != 6);
    }

    public static void processOptions(int option, Scanner scanner){
        switch(option){
            case 1:
                createAccount(scanner);
                break;
            case 2:
                viewAllAccounts();
                break;
            case 3:
                checkBalance(scanner);
                break;
            case 4:
                deposit(scanner);
                break;
            case 5:
                withdraw(scanner);
                break;
            case 6:
                System.out.println("Thank you!");
                break;
            default:
                System.out.println("Invalid option! Please only choose from options 1-6");
                break;
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();
        if(isAccountNotExisting(accountNumber)){
            System.out.println("Account does not exist!");
            return;
        }
        System.out.print("Withdraw Amount: ");
        double moneyToWithdraw = Double.parseDouble(scanner.nextLine());
        if(moneyToWithdraw < 0 || moneyToWithdraw > accountNumberAndAccount.get(accountNumber).getAvailBalance()){
            System.out.println("Invalid amount!");
            return;
        }
        accountNumberAndAccount.get(accountNumber).withdraw(moneyToWithdraw);
        System.out.printf("Updated Balance: %.2f%n", accountNumberAndAccount.get(accountNumber).getAvailBalance());
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();

        if(isAccountNotExisting(accountNumber)){
            System.out.println("Account does not exist!");
            return;
        }

        System.out.print("Amount to deposit: ");
        double depositMoney = Double.parseDouble(scanner.nextLine());

        if(depositMoney >= 0) {
            accountNumberAndAccount.get(accountNumber).deposit(depositMoney);
            System.out.print("Deposit money success! ");
            System.out.printf("Updated Balance: %.2f%n", accountNumberAndAccount.get(accountNumber).getAvailBalance());
        }else{
            System.out.println("Invalid amount to deposit");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();
        if(isAccountNotExisting(accountNumber)){
            System.out.println("Account does not exist!");
            return;
        }
        System.out.printf("%nCurrent Balance: %.2f%n", accountNumberAndAccount.get(accountNumber).getAvailBalance());
    }

    public static void createAccount(Scanner scanner){
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        if(!isAccountNotExisting(accountNumber)){
            System.out.println("Account number already exists!");
            return;
        }
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Initial deposit? (yes/no):");
        String initialDeposit = scanner.nextLine().toLowerCase();
        if(initialDeposit.equals("yes")){
            System.out.print("Enter initial deposit amount: ");
            int initialDepositAmount = Integer.parseInt(scanner.nextLine());
            if(initialDepositAmount >= 0){
                bankAccounts.add(new BankAccount(accountNumber, accountHolderName, initialDepositAmount));
                accountNumberAndAccount.put(accountNumber, bankAccounts.getLast());
                System.out.println("Account Created Successfully!");
            }
            return;
        }
        bankAccounts.add(new BankAccount(accountNumber, accountHolderName));
        accountNumberAndAccount.put(accountNumber, bankAccounts.getLast());
        System.out.println("Account Created Successfully!");

    }

    private static void viewAllAccounts() {
        bankAccounts.forEach(BankAccount::displayInformation);
    }

    private static boolean isAccountNotExisting(String accountNumber){
        return !accountNumberAndAccount.containsKey(accountNumber);
    }

}
