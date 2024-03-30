package com.example.timski.service;

import com.example.timski.model.Category;
import com.example.timski.model.Manufacturer;
import com.example.timski.model.Payment;
import com.example.timski.model.Product;
import com.example.timski.model.errors.CategoryNotFound;
import com.example.timski.model.errors.ManufacturerNotFound;
import com.example.timski.repository.CategoryRepository;
import com.example.timski.repository.ManufacturerRepository;
import com.example.timski.repository.PaymentRepository;
import com.example.timski.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;

    }

    public Optional<Payment> save( String cardholderName,
                                   String billingAddress,
                                   String cardNumber,
                                   String expiryDate,
                                   Integer cvv,
                                   Integer paymentAmount) {

        return Optional.of(this.paymentRepository.save(new Payment(cardholderName, billingAddress, cardNumber, expiryDate, cvv, paymentAmount)));
    }
}
