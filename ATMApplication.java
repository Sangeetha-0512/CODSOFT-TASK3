import java.util.Scanner;
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (balance - amount < 1000) {
            System.out.println("Insufficient balance. Minimum balance of 1000 must be maintained.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrew " + amount);
        }
    }
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}
class ATM {
    private BankAccount account;
    private Scanner scanner;
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }
    public void start() {
        while (true) {
            System.out.println("Welcome to the ATM, " + account.getAccountHolderName());
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleCheckBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }
    private void handleCheckBalance() {
        account.checkBalance();
    }
}
public class ATMApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String accountNumber;
        String accountHolderName;
         while (true) {
            System.out.print("Enter account number (11 to 14 digits): ");
            accountNumber = scanner.nextLine();
            if (accountNumber.length() >= 11 && accountNumber.length() <= 14 && accountNumber.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid account number. Please enter a number between 11 and 14 digits.");
            }
        }
        System.out.print("Enter account holder name: ");
        accountHolderName = scanner.nextLine();
        BankAccount account = new BankAccount(accountNumber, accountHolderName, 1000);
        ATM atm = new ATM(account);
        atm.start();
    }
}
