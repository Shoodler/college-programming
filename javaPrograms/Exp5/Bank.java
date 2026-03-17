import java.util.ArrayList;
import java.util.List;

abstract class Loan {

    private final int loanId;
    private final double principal;
    private final double interestRate;
    private final int tenure;

    Loan(int loanId, double principal, double interestRate, int tenure) {
        this.loanId = loanId;
        this.principal = principal;
        this.interestRate = interestRate;
        this.tenure = tenure;
    }

    int getLoanId()          { return loanId; }
    double getPrincipal()    { return principal; }
    double getInterestRate() { return interestRate; }

    double calculateEMI() {
        double r = interestRate / (12 * 100);
        int n = tenure * 12;
        return (principal * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }
}

class HomeLoan extends Loan {
    private static final double INTEREST_RATE = 6.5;
    HomeLoan(int loanId, double principal, int tenure) {
        super(loanId, principal, INTEREST_RATE, tenure);
    }
}

class CarLoan extends Loan {
    private static final double INTEREST_RATE = 9.0;
    CarLoan(int loanId, double principal, int tenure) {
        super(loanId, principal, INTEREST_RATE, tenure);
    }
}

class PersonalLoan extends Loan {
    private static final double INTEREST_RATE = 12.5;
    PersonalLoan(int loanId, double principal, int tenure) {
        super(loanId, principal, INTEREST_RATE, tenure);
    }
}


class FixedDeposit {

    private final int depositId;
    private final double amount;
    private final double interestRate;
    private final int duration;

    FixedDeposit(int depositId, double amount, double interestRate, int duration) {
        this.depositId = depositId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
    }

    int getDepositId()    { return depositId; }
    double getAmount()    { return amount; }

    double calculateMaturityAmount() {
        return amount * Math.pow(1 + interestRate / 100, duration);
    }
}


class Account {

    private final int accountNumber;
    private final String holderName;
    private double savingsBalance;

    private final ArrayList<Loan> loans = new ArrayList<>();
    private final ArrayList<FixedDeposit> deposits = new ArrayList<>();

    Account(int accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
    }

    void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Invalid deposit amount");
        savingsBalance += amount;
    }

    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) throw new InvalidAmountException("Invalid withdraw amount");
        if (amount > savingsBalance) throw new InsufficientFundsException("Not enough balance");
        savingsBalance -= amount;
    }

    void addLoan(Loan loan)            { loans.add(loan); }
    void addDeposit(FixedDeposit fd)   { deposits.add(fd); }
    double getBalance()                { return savingsBalance; }
    int getAccountNumber()             { return accountNumber; }
    String getHolderName()             { return holderName; }
    List<Loan> getLoans()              { return loans; }
    List<FixedDeposit> getDeposits()   { return deposits; }
}


class Transaction {

    private Transaction() {}

    static void transfer(Account sender, Account receiver, double amount)
            throws InvalidAmountException, InsufficientFundsException {
        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}