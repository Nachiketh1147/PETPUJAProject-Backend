package com.petpuja.controller;

import com.petpuja.dto.PaymentRequestDTO;
import com.petpuja.model.Payment;
import com.petpuja.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // PAYMENT POST
    @PostMapping
    public Payment makePayment(@RequestBody PaymentRequestDTO dto) {

        return paymentService.makePayment(dto);
    }

    // PAYMENT GET
    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {

        return paymentService.getPayment(id);
    }

    // PAYMENT STATUS UPDATE
    @PutMapping("/status")
    public Payment updatePaymentStatus(
            @RequestParam Long paymentId,
            @RequestParam String status) {

        return paymentService.updatePaymentStatus(
                paymentId,
                status
        );
    }
}