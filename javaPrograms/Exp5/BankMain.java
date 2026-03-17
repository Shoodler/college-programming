import java.util.ArrayList;
import java.util.Scanner;

public class BankMain {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();
    static int nextAccNo = 101, nextLoanId = 1, nextFdId = 1;

    public static void main(String[] args) {
        System.out.println("=== Welcome to HeHeHe-Bank ===");
        while (true) {
            System.out.println("\n1.Create Account  2.Account Ops  3.Transfer  4.List Accounts  0.Exit");
            int c = readInt("Choice: ");
            if (c == 0) { System.out.println("Goodbye!"); break; }
            switch (c) {
                case 1 -> createAccount();
                case 2 -> accountMenu(findAccount());
                case 3 -> transfer();
                case 4 -> listAccounts();
            }
        }
        sc.close();
    }

    static void createAccount() {
        System.out.print("Holder name: ");
        String name = sc.nextLine().trim();
        Account acc = new Account(nextAccNo++, name);
        accounts.add(acc);
        System.out.println("Account created. No: " + acc.getAccountNumber());
    }

    static void accountMenu(Account acc) {
        if (acc == null) return;
        while (true) {
            System.out.println("\n[" + acc.getHolderName() + "] 1.Deposit  2.Withdraw  3.Balance  4.Add Loan  5.Loans  6.Add FD  7.FDs  0.Back");
            int c = readInt("Choice: ");
            if (c == 0) break;
            try {
                switch (c) {
                    case 1 -> { acc.deposit(readDouble("Amount: "));  System.out.printf("Balance: %.2f%n", acc.getBalance()); }
                    case 2 -> { acc.withdraw(readDouble("Amount: ")); System.out.printf("Balance: %.2f%n", acc.getBalance()); }
                    case 3 -> System.out.printf("Balance: %.2f%n", acc.getBalance());
                    case 4 -> addLoan(acc);
                    case 5 -> acc.getLoans().forEach(l -> System.out.printf("  [%d] %s | Principal: %.2f | EMI: %.2f%n", l.getLoanId(), l.getClass().getSimpleName(), l.getPrincipal(), l.calculateEMI()));
                    case 6 -> addFD(acc);
                    case 7 -> acc.getDeposits().forEach(f -> System.out.printf("  [%d] Amount: %.2f | Maturity: %.2f%n", f.getDepositId(), f.getAmount(), f.calculateMaturityAmount()));
                }
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    static void transfer() {
        Account from = findAccountByNo(readInt("From account no: "));
        Account to   = findAccountByNo(readInt("To account no: "));
        if (from == null || to == null) { System.out.println("Account not found."); return; }
        try {
            Transaction.transfer(from, to, readDouble("Amount: "));
            System.out.println("Transfer successful.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void listAccounts() {
        if (accounts.isEmpty()) { System.out.println("No accounts."); return; }
        System.out.printf("%-10s %-20s %s%n", "Acc No.", "Name", "Balance");
        accounts.forEach(a -> System.out.printf("%-10d %-20s %.2f%n", a.getAccountNumber(), a.getHolderName(), a.getBalance()));
    }

    static void addLoan(Account acc) {
        System.out.println("Loan type: 1.Home(6.5%)  2.Car(9%)  3.Personal(12.5%)");
        int type = readInt("Type: ");
        double p = readDouble("Principal: ");
        int t    = readInt("Tenure (years): ");
        Loan loan = switch (type) {
            case 1 -> new HomeLoan(nextLoanId++, p, t);
            case 2 -> new CarLoan(nextLoanId++, p, t);
            case 3 -> new PersonalLoan(nextLoanId++, p, t);
            default -> null;
        };
        if (loan == null) { System.out.println("Invalid type."); return; }
        acc.addLoan(loan);
        System.out.printf("Loan added. EMI: %.2f/month%n", loan.calculateEMI());
    }

    static void addFD(Account acc) {
        double amount = readDouble("Amount: ");
        double rate   = readDouble("Rate (%): ");
        int duration  = readInt("Duration (years): ");
        FixedDeposit fd = new FixedDeposit(nextFdId++, amount, rate, duration);
        acc.addDeposit(fd);
        System.out.printf("FD added. Maturity: %.2f%n", fd.calculateMaturityAmount());
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    static Account findAccount() {
        return findAccountByNo(readInt("Account no: "));
    }

    static Account findAccountByNo(int no) {
        return accounts.stream().filter(a -> a.getAccountNumber() == no).findFirst().orElse(null);
    }

    static int readInt(String prompt) {
        while (true) { try { System.out.print(prompt); return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Enter a valid number."); } }
    }

    static double readDouble(String prompt) {
        while (true) { try { System.out.print(prompt); return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Enter a valid amount."); } }
    }
}