import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Setup Initial Data
        Customer dummyCustomer = new Customer(101, "Micheal De Santa", "2864 Portola Drive", "328-555-0108");
        
        // We'll use a SavingsAccount for this example
        Account myAccount = new SavingsAccount(dummyCustomer, "SAV12345", 5000.0);

        boolean exit = false;

        System.out.println("Welcome to the Maze Bank International");
        System.out.println("Customer: " + dummyCustomer.getCustID() + " | Account: " + myAccount.accNum);

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Deposit
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    try {
                        myAccount.deposit(amt);
                        System.out.println("Success!");
                    } catch (InvalidAmountException e) {
                        System.out.println("Invalid Input: " + e.getMessage());
                    }
                    break;

                case 2: // Withdraw
                    System.out.print("Enter amount: ");
                    double wAmt = sc.nextDouble();
                    try {
                        myAccount.withdraw(wAmt);
                        System.out.println("Success!");
                    } catch (InsufficientFundsException | InvalidAmountException e) {
                        // You can catch both types of errors here
                        System.out.println("Transaction Failed: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Current Balance: Rs." + myAccount.getBalance());
                    break;

                case 4:
                    myAccount.showHistory();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Thank you for banking with us!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}