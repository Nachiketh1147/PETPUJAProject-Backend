package com.petpuja.controller;

import com.petpuja.dto.OrderRequestDTO;
import com.petpuja.model.Orders;
import com.petpuja.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // =====================================
    // CREATE ORDER
    // =====================================

    @PostMapping
    public Orders createOrder(
            @RequestBody OrderRequestDTO dto) {

        return orderService
                .createOrder(dto);
    }

    // =====================================
    // GET ORDER
    // =====================================

    @GetMapping("/{id}")
    public Orders getOrder(
            @PathVariable Long id) {

        return orderService
                .getOrder(id);
    }

    // =====================================
    // UPDATE ORDER STATUS
    // =====================================

    @PutMapping("/status")
    public String updateStatus(
            @RequestParam Long orderId,
            @RequestParam String status) {

        return orderService
                .updateOrderStatus(
                        orderId,
                        status);
    }

    // =====================================
    // AI PREPARATION TIME
    // =====================================

    @GetMapping("/estimate-time")
    public String estimateTime(
            @RequestParam Integer itemCount,
            @RequestParam boolean rushHour){

        return orderService
                .estimatePreparationTime(
                        itemCount,
                        rushHour);
    }

    // =====================================
    // SMART WAITING TIME
    // =====================================

    @GetMapping("/waiting-time")
    public String waitingTime(){

        return orderService
                .estimateWaitingTime();
    }
}