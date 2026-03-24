interface Loan {
    // Allows the account to originate/create a loan
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

    // Getter function required by the Account constructor
    public int getCustID() {
        return custID;
    }
}

abstract class Account {
    protected int custID; 
    protected String accNum;
    protected double balance;

    // Constructor now takes the Customer object and extracts the ID
    public Account(Customer customer, String accNum, double startingBalance) {
        this.custID = customer.getCustID(); 
        this.accNum = accNum;
        this.balance = startingBalance;
    }

    public abstract void deposit(double amount);
    
    // The withdraw method now explicitly declares that it can throw our exception
    public abstract void withdraw(double amount) throws InsufficientFundsException;
    
    public double getBalance() {
        return balance;
    }
}

class SavingsAccount extends Account implements Loan {
    
    public SavingsAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Savings: Cannot withdraw more than current balance
        if (amount > balance) {
            throw new InsufficientFundsException("Withdrawal failed: Insufficient funds in Savings Account.");
        }
        balance -= amount;
    }

    @Override
    public void createLoan(double amount) {
        System.out.println("Processing new loan of Rs." + amount + " tied to Savings Account: " + accNum);
        // Logic to link a new loan to this account would go here
    }
} 

class CurrentAccount extends Account implements Loan {
    private double overdraftLimit = 50000.0; //overdraft limit

    public CurrentAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > (balance + overdraftLimit)) {
            throw new InsufficientFundsException("Withdrawal failed: Overdraft limit exceeded in Current Account.");
        }
        balance -= amount; 
    }

    @Override
    public void createLoan(double amount) {
        System.out.println("Processing new business loan of Rs." + amount + " tied to Current Account: " + accNum);
    }
}

class SalaryAccount extends Account {
    public SalaryAccount(Customer customer, String accNum, double startingBalance) {
        super(customer, accNum, startingBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Salary: Cannot withdraw more than current balance
        if (amount > balance) {
            throw new InsufficientFundsException("Withdrawal failed: Insufficient funds in Salary Account.");
        }
        balance -= amount;
    }
}