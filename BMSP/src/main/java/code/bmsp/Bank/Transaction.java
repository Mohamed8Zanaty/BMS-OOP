package code.bmsp.Bank;

import code.bmsp.Enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
    // attributes
    private String accountNumber;
    private String transactionID;
    private LocalDate date;
    private double amount;
    private TransactionType type;
    private String clientSSN;
    private int employeeID;

    // parametrised constructor
    public Transaction(String transactionID, LocalDate date, double amount, TransactionType type, String clientSSN, int employeeID) {
        this.transactionID = transactionID;
        this.date =  date;
        this.amount = amount;
        this.type = type;
        this.clientSSN = clientSSN;
        this.employeeID = employeeID;
    }
    // setters and getters
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDate getDate() {
        return  date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getClientSSN() {
        return clientSSN;
    }

    public void setClientID(String clientSSN) {
        this.clientSSN = clientSSN;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
