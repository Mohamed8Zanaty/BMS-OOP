package code.bmsp.Bank;

import code.bmsp.Enums.CardType;
import code.bmsp.Enums.Grade;
import code.bmsp.Tools.Utils;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee extends User{
    // fields
    private static ArrayList<Client> clientsRegistered = new ArrayList<Client>();
    private static HashMap<String, String> cardRequests = new HashMap<>();
    private static ArrayList<Client> acceptedClients = new ArrayList<Client>();
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static HashMap<String, ArrayList<Account>> clientAccounts = new HashMap<>();
    private Account account;
    private String graduateCollege;
    private Year yearOfGraduation;
    private Grade totalGrade;
    private Address address;


    public Employee(){
        this.graduateCollege = "N/A";
        this.yearOfGraduation = null;
        this.totalGrade = Grade.UNKNOWN;
    }
    public Employee(String name,String SSN, String phone, String graduateCollege, int yearOfGraduation, Grade totalGrade){
        super(name,SSN,phone);

        this.graduateCollege = graduateCollege;
        this.yearOfGraduation = Year.of(yearOfGraduation);
        this.totalGrade = totalGrade;
    }

    public Employee( String graduateCollege, int yearOfGraduation, Grade totalGrade){
        this.graduateCollege = graduateCollege;
        this.yearOfGraduation = Year.of(yearOfGraduation);
        this.totalGrade = totalGrade;
    }


    // Setters and getters

    public void setGraduateCollege(String graduateCollege) {
        this.graduateCollege = graduateCollege;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = Year.of(yearOfGraduation);
    }

    public void setTotalGrade(Grade totalGrade) {
        this.totalGrade = totalGrade;
    }

    public static ArrayList<Client> acceptedClients() {
        return acceptedClients;
    }

    public String getGraduateCollege() {
        return graduateCollege;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation.getValue();
    }

    public Grade getTotalGrade() {
        return totalGrade;
    }

    public static ArrayList<Client> getAcceptedClients() {
        return acceptedClients;
    }
    public static ArrayList<Transaction> getListOfTransactions() {
        return transactions;
    }

    // methods

    public static void createClientAccount(String id, Account account) {
        clientAccounts.get(id).add(account);
    }

    public static void requestCard(String accountNumber, String pin) {
        cardRequests.put(accountNumber, pin);
    }

    public static void processCardRequest() {
        cardRequests.forEach((accountNumber, pin) -> {
            acceptedClients.stream()
                    .filter(client -> client.getAccountNumber().equals(accountNumber)
                    )
                    .forEach(client -> {
                        client.getAccount().createCreditCard(new CreditCard(client.getName(), CardType.REGULAR,pin));

                    });

        });
    }

    public static void acceptClient(Client client) {
        acceptedClients.add(client);
        if(clientAccounts.get(client.getSSN()) == null) {
            ArrayList<Account> firstAccount = new ArrayList<Account>();
            firstAccount.add(client.getAccount());
            clientAccounts.put(client.getSSN(), firstAccount);
        } else {
            clientAccounts.get(client.getSSN()).add(client.getAccount());
        }
    }


    public static void requestRegistration(Client client) {
        clientsRegistered.add(client);
    }

    public static Client findClient(String accountNumber) {
        return acceptedClients.stream()
                .filter(client -> client.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }
    public static Client findClientByUserName(String username) {
        for (Client client : acceptedClients) {
            for(Account account : client.getAccounts()) {
                if(account.getUserName().equals(username))
                    return client;
            }
        }
        return null;
    }
    public static Client findClientBySSN(String SSN) {
        return acceptedClients.stream()
                .filter(client -> client.getSSN().equals(SSN))
                .findFirst()
                .orElse(null);
    }

    public static boolean transferAccepted(String senderAccountNumber, String resverAccountNumber, float amount) {
        Client sender = findClient(senderAccountNumber);
        Client receiver = findClient(resverAccountNumber);
        if(sender == null || receiver == null || sender.getTotalBalance() < amount)
            return false;
        sender.getAccount().updateBalance(-amount);
        receiver.getAccount().updateBalance(amount);
        return true;
    }
    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }


    public Account getAccount() {
        return account;
    }
    public void deleteAccountClient(String SSN){
        acceptedClients().stream()
                .filter(client->client.getSSN().equals(SSN))
                .forEach(client -> {
                    Employee.acceptedClients().remove(client);
                });
    }

    // edit address
    public void editAddress(Address address) {
        this.address = address;
    }

    // create client account logic
    private boolean checkPassword(String password) {
        return Utils.checkPattern(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[A-Za-z\\d!@#$%^&*()]{8,}$\n");
    }
    private boolean validSSN(String SSN) {
        return Utils.checkPattern(SSN, "^[23]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{4}\\d{2}$\n");
    }
    private boolean checkUserName(String userName) {
        return findClientByUserName(userName) == null;
    }
    private boolean checkSSNExists(String ssn) {
        return findClientBySSN(ssn)  != null;
    }
    private boolean validPhoneNumber(String phoneNumber) {
        return Utils.checkPattern(phoneNumber, "^01[0125]\\d{8}$\n");
    }
    private boolean checkPhoneNumberExist(String phoneNumber) {
        for(Client client : acceptedClients)
            if(client.getPhone().equals(phoneNumber))
                return true;
        return false;
    }

    public boolean createNewClientAccount(String name, String SSN, String phone, String userName
            , String password, String phoneNumber, float balance) {
        if(!validSSN(SSN) || checkSSNExists(SSN) || !checkPassword(password) || !validPhoneNumber(phoneNumber) || checkPhoneNumberExist(phoneNumber))
            return false;
        Client client = new Client(name, SSN, phoneNumber, new Account(Utils.generateRandomString(8), userName, password, balance));
        requestRegistration(client);
        return true;
    }
    public void createAnotherClientAccount(String SSN, String userName, String password, float balance) {
        clientAccounts.get(SSN).add(new Account(Utils.generateRandomString(8), userName, password, balance));
    }
    public boolean editClientPhoneNumber(String SSN, String newPhoneNumber) {
        for(Client client : acceptedClients)
            if(client.getSSN().equals(SSN)) {
                client.setPhone(newPhoneNumber);
                return true;
            }
        return false;
    }

    // show transactions
    public ArrayList<Transaction> getTransactions(LocalDate date) {
        ArrayList<Transaction> newTransactions = new ArrayList<Transaction>();
        transactions.stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .forEach(transaction -> newTransactions.add(transaction));
        return newTransactions;
    }
    public ArrayList<Transaction> getTransactions(String ssn) {
        ArrayList<Transaction> newTransactions = new ArrayList<Transaction>();
        transactions.stream()
                .filter(transaction -> transaction.getClientSSN().equals(ssn))
                .forEach(transaction -> newTransactions.add(transaction));
        return newTransactions;
    }
}
