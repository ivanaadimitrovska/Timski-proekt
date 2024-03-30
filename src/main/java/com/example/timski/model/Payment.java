package com.example.timski.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardholderName;
    private String billingAddress;
    private String cardNumber;
    private String expiryDate;
    private Integer cvv;
    private Integer paymentAmount;


    public Payment() {

    }

    public Payment(String cardholderName, String billingAddress, String cardNumber, String expiryDate, Integer cvv, Integer paymentAmount) {
        this.cardholderName=cardholderName;
        this.billingAddress=billingAddress;
        this.cardNumber=cardNumber;
        this.expiryDate=expiryDate;
        this.cvv=cvv;
        this.paymentAmount=paymentAmount;
    }
}
