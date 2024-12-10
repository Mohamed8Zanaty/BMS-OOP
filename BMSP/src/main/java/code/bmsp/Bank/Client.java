package code.bmsp.Bank;

import java.io.Serializable;
import java.util.ArrayList;

public class Client extends User implements Serializable {
    // fields
    private String currentUserName;
    private ArrayList<Account> accounts = new ArrayList<>();
    private boolean active;
    private boolean accepted;
    private float totalBalance;
    private int loyaltyPoints;

    public Client() {
        this.currentUserName = "N/A";
        this.accepted = false;
        this.active = false;
        this.totalBalance = 0.0F;
        this.loyaltyPoints = 0;
    }

    public Client(String name, String SSN, String phone, Account account) {
        super(name, SSN, phone);
        this.active = false;
        accounts.add(account);
    }

    // methods
    public void addAccount(Account account) {
        accounts.add(account);
    }
    public Account getAccount() {
        return
                accounts.stream()
                        .filter(acc -> acc.getUserName().equals(currentUserName))
                        .findFirst()
                        .orElse(null);
    }
    private void editAccount(String userName, String email, String password) {
        for(int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getUserName().equals(currentUserName)) {
                accounts.get(i).setEmail(email);
                accounts.get(i).setPassword(password);
                accounts.get(i).setUserName(userName);
                currentUserName = userName;
                return;
            }
        }
    }
    // method to edit personal information
    public void editPersonalInfo(String name,String phoneNumber, String username,  String email, String password) {
        setName(name);
        editAccount(username, email, password);

    }

    // display details
    public String toString() {
        return "\t\t" + getName() +  "\n" +
                "SSN:\t" + getSSN() + "\n" +
                "Email:\t" + getAccount().getEmail() + "\n" +
                "Phone:\t" + getPhone() + "\n" +
                "User Name:\t" + currentUserName + "\n" +
                "accountNumber:\t" + getAccount().getAccountNumber() + '\n' +
                "balance:\t" + totalBalance + "\n" +
                "loyaltyPoints:\t" + loyaltyPoints + "\n" +
                '}';
    }

    public static void sendRegistration(Client client) {
        Employee.requestRegistration(client);
    }
    public void sendCardRequest(String pin ) {
        Employee.requestCard(getAccount().getAccountNumber(), pin);
    }
    public  boolean transferMoney(String accountNumber, float amount){
        return Employee.transferAccepted(getAccount().getAccountNumber(), accountNumber, amount);
    }
    public void addLoyaltyPoints (int points){
        loyaltyPoints += points;
    }
    public void addMoneyToCredit( float amount) {
        if(getAccount().hasCreditCard()) {
            getAccount().returnCreditCard().updateBalance(amount);
        }
    }
    public void payWithCredit(float amount) {
        if(getAccount().hasCreditCard() && getCard().getBalance() >= amount) {
            getAccount().returnCreditCard().updateBalance(-amount);
            addLoyaltyPoints(1);
        }
    }
    public String getAccountNumber() {
        return getAccount().getAccountNumber();
    }
    public float getTotalBalance() {
        float totalBalance = 0F;
        for(Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public CreditCard getCard() {
        return getAccount().returnCreditCard();
    }
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

}
