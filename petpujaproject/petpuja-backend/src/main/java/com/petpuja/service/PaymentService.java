package com.petpuja.service;

import com.petpuja.dto.PaymentRequestDTO;
import com.petpuja.model.Payment;
import com.petpuja.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(PaymentRequestDTO dto) {

        Payment payment = new Payment();

        payment.setOrderId(dto.getOrderId());

        payment.setAmount(dto.getAmount());

        payment.setPaymentMethod(dto.getPaymentMethod());

        Random random = new Random();

        int result = random.nextInt(3);

        if(result == 0) {

            payment.setPaymentStatus("SUCCESS");

        } else if(result == 1) {

            payment.setPaymentStatus("FAILED");

        } else {

            payment.setPaymentStatus("BANK SERVER BUSY");
        }

        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {

        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePaymentStatus(Long paymentId,
                                       String status) {

        Payment payment =
                paymentRepository.findById(paymentId).orElse(null);

        if(payment != null) {

            payment.setPaymentStatus(status);

            return paymentRepository.save(payment);
        }

        return null;
    }
}