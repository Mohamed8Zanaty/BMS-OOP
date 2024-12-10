package code.bmsp.Bank;

public class Address {
    // fields
    private String street;
    private String city;
    private String state;
    private String zipCode;

    // constructor
    public Address() {
        this.street = "N/A";
        this.city = "N/A";
        this.state = "N/A";
        this.zipCode = "N/A";
    }
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public Address(String street, String city, String state) {
        this(street, city, state, "N/A");
    }


    // getters and setters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
