package code.bmsp.Bank;

import java.io.Serializable;

public class User implements Serializable {
    // fields
    private String name;
    private String SSN;
    private String phone;


    // default constructor
    public User() {
        this.phone = "N/A";
        this.name = "N/A";
        this.SSN = "N/A";
    }
    // Constructor
    public User(String name, String SSN, String phone) {
        this.name = name;

        this.SSN = SSN;
        this.phone = phone;
    }
    // getters and setters
    // 1- phone number
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    // 2- SSN
    public void setSNN(String SSN){
        this.SSN = SSN;
    }
    public String getSNN(){
        return SSN;
    }

    // 3- first and last name
    public  void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public String getSSN() {
        return SSN;
    }
}
