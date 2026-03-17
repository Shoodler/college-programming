class InvalidAmountException extends Exception {
    InvalidAmountException(String message) { super(message); }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) { super(message); }
}