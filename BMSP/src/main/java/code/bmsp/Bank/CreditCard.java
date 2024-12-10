package code.bmsp.Bank;

import code.bmsp.Enums.CardType;
import code.bmsp.Tools.Utils;

import java.time.LocalDate;

public class CreditCard {
    private String cardHolderName;
    private CardType cardType;
    private String cardNumber;
    private LocalDate startDate;
    private LocalDate expirationDate;
    private String cvv;
    private String pin;
    private float balance;

    // Constructors
    public CreditCard() {
        this.cardHolderName = "N/A";
        this.cardType = CardType.REGULAR;
        this.cardNumber = "N/A";
        this.startDate = LocalDate.now();
        this.expirationDate = startDate.plusYears(4);
        this.cvv = "000";

        this.balance = 0F;
    }
    public CreditCard(String cardHolderName, CardType cardType, String pin) {
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.cardNumber = Utils.generateRandomCreditCardNumber();
        this.startDate = LocalDate.now();
        this.expirationDate = this.startDate.plusYears(10);
        this.cvv = Utils.generateCvv();
        this.pin = pin;
        this.balance = 0F;
    }
    // methods
    public void updateBalance(float amount) {
        balance += amount;
    }
    // setter and getter

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }


    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


    @Override
    public String toString() {
        return "CreditCard{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardType=" + cardType +
                ", cardNumber='" + cardNumber + '\'' +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
