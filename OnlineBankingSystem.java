import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Transaction {
    String type;
    double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
}

class BankAccount {
    String accountNumber;
    String accountHolder;
    double balance;
    List<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (balance >= amount) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + receiver.accountNumber, amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.type + ": " + transaction.amount);
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

class Database {
    private static Map<String, BankAccount> accounts = new HashMap<>();

    public static BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public static void addAccount(BankAccount account) {
        accounts.put(account.accountNumber, account);
    }
}

public class OnlineBankingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two sample accounts
        BankAccount account1 = new BankAccount("001", "raju");
        BankAccount account2 = new BankAccount("002", "rani");

        // Add accounts to the database
        Database.addAccount(account1);
        Database.addAccount(account2);

        // Sample transactions
        account1.deposit(20000);
        account1.withdraw(1000);
        account1.transfer(account2, 5000);

        // Sample user interactions
        account1.displayBalance();
        account1.viewTransactionHistory();

        account2.displayBalance();
        account2.viewTransactionHistory();

        // Access an account using the database
        BankAccount retrievedAccount = Database.getAccount("001");
        if (retrievedAccount != null) {
            retrievedAccount.displayBalance();
        }

        scanner.close();
    }
}


   