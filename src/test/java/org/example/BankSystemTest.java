package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BankSystemTest {

    @BeforeEach
    void initializeBankAccount(){
        BankSystem.accountNumberAndAccount.clear();
        BankSystem.bankAccounts.clear();
    }

    @Test
    void depositValidAmount(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String simulatedDeposit = "101\n100";
        BankSystem.deposit(new Scanner(simulatedDeposit));

        double expectedBalance = 1100.00;
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);
    }

    @Test
    void depositInvalidAmount(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        //deposit with invalid amount
        String simulatedDeposit = "101\n-100";
        BankSystem.deposit(new Scanner(simulatedDeposit));
        BankSystem.viewAllAccounts();
        double expectedBalance = 1000.00;
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);

        //deposit to invalid account number
        String simulatedDeposit2 = "102\n100";
        BankSystem.deposit(new Scanner(simulatedDeposit2));
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);

    }

    @Test
    void withdrawValidAmount(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String simulatedDeposit = "101\n200";
        BankSystem.withdraw(new Scanner(simulatedDeposit));

        double expectedBalance = 800.00;
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);
    }

    @Test
    void withdrawInvalidAmount(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String simulatedDeposit = "101\n100000";
        BankSystem.withdraw(new Scanner(simulatedDeposit));

        double expectedBalance = 1000.00;
        //balance should not be deducted after withdrawing using invalid amount
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);
    }

    @Test
    void withdrawExceedingBalanceAmount(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String simulatedDeposit = "101\n100000";
        BankSystem.withdraw(new Scanner(simulatedDeposit));

        double expectedBalance = 1000.00;
        //balance should not be deducted after withdrawing money greater than current balance
        assertEquals(expectedBalance, BankSystem.accountNumberAndAccount.get("101").getAvailBalance(), 0.01);
    }

    @Test
    void gettingCorrectAccountNumber(){
        String simulatedInput = "101\nAlice\nyes\n1000\nno";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String actualAccountNumber = BankSystem.bankAccounts.getFirst().getAccountNumber();
        String expectedAccountNumber = "101";

        assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    @Test
    void createAccountWithoutDeposit(){
        String simulatedInput = "101\nAlice\nno\nyes";
        BankSystem.processOptions(1, new Scanner(simulatedInput));

        String actualAccountNumber = BankSystem.bankAccounts.getFirst().getAccountNumber();
        String actualAccountHolderName = BankSystem.bankAccounts.getFirst().getAccountHolderName();

        String expectedAccountNumber = "101";
        String expectedAccountHolderName = "Alice";

        assertEquals(expectedAccountHolderName, actualAccountHolderName);
        assertEquals(expectedAccountNumber, actualAccountNumber);

        double expectedAccountBalance = 0.00;
        String checkBal = "101";
        BankSystem.checkBalance(new Scanner(checkBal));
        assertEquals(expectedAccountBalance, BankSystem.bankAccounts.getFirst().getAvailBalance());
    }

}