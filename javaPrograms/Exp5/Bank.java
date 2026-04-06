import java.util.ArrayList;

interface Loan {
    void createLoan(double amount);
}

class Customer {
    private final int custID;
    private final String name;
    private String address;
    private final String aadhaar;

    public Customer(int custID, String name, String address, String aadhaar) {
        this.custID = custID;
        this.name = name;
        this.address = address;
        this.aadhaar = aadhaar;
    }

    public int getCustID() {
        return custID;
    }
}

class Transactions {
    static int idCounter = 1000; // Static counter for auto-incrementing IDs
    int transID;
    String accNum;
    String type;   
    double amount;
    String date;   

    Transactions(String acc, String t, double amt, String d) {
        this.transID = idCounter++; 
        this.accNum = acc;
        this.type = t;
        this.amount = amt;
        this.date = d;
    }

    void showTransaction() {
        System.out.println(transID + " | " + type + " | " + amount + " | " + accNum + " | " + date);
    }
}

abstract class Account {
    protected int custID; 
    protected String accNum;
    protected double balance;
    
    protected ArrayList<Transactions> history = new ArrayList<>();

    public Account(Customer customer, String accNum, double startingBalance) {
        this.custID = customer.getCustID(); 
        this.accNum = accNum;
        this.balance = startingBalance;
    }

    protected void addLog(String type, double amt) {
        history.add(new Transactions(this.accNum, type, amt, "2026-04-06"));
    }

    public abstract void deposit(double amount) throws InvalidAmountException;
    public abstract void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;
    
    public void showHistory() {
        System.out.println("--- Transaction History for " + accNum + " ---");
        for (Transactions t : history) {
            t.showTransaction();
        }
    }

    public double getBalance() { return balance; }
}

class SavingsAccount extends Account implements Loan {
    public SavingsAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        addLog("Deposit", amount);
    }
    
    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
        addLog("Withdrawal", amount);
    }

    @Override
    public void createLoan(double amount) {
        addLog("Loan Issued", amount);
        System.out.println("Loan of " + amount + " processed.");
    }
}

class CurrentAccount extends Account implements Loan {
    private double overdraftLimit = 50000.0; 

    public CurrentAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        addLog("Deposit", amount);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
        addLog("Withdrawal", amount);
    }

    @Override
    public void createLoan(double amount) {
        addLog("Business Loan", amount);
    }
}

class SalaryAccount extends Account {
    public SalaryAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        addLog("Deposit", amount);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
        addLog("Withdrawal", amount);
    }
}