package code.bmsp.Bank;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private float balance;
    CreditCard creditCard;
    private String password;
    private String userName;
    private boolean SavingAccount;
    private boolean CurrentAccount;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private String Email;


    // constructors
    public Account() {
        this.accountNumber = "N/A";
        this.balance = 0f;
        this.SavingAccount = false;
        this.CurrentAccount = false;
    }
    public Account(String accountNumber, String userName, String password, float balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.SavingAccount = false;
        this.CurrentAccount = false;
        this.userName = userName;
        this.password = password;
    }
    // getters and setters
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isSavingAccount() {
        return SavingAccount;
    }

    public boolean isCurrentAccount() {
        return CurrentAccount;
    }

    public void updateBalance(float amount) {
        balance += amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }
    public void setCardBalance() {
        creditCard.setBalance(balance);
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


    public void createCreditCard(CreditCard card) {
        creditCard = card;
    }

    // method
    public void disableCreditCard(String cardNumber) {
        creditCard = null;
    }

    public CreditCard returnCreditCard() {
        if(creditCard != null)
            return creditCard;
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +

                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", hasCreditCard=" + hasCreditCard() +
                ", hasSavingAccount=" + SavingAccount +
                ", hasCurrentAccount=" + CurrentAccount +
                '}';
    }
    public boolean hasCreditCard() {
        return creditCard != null;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
