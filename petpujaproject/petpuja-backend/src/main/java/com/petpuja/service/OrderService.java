package com.petpuja.service;

import com.petpuja.dto.OrderRequestDTO;
import com.petpuja.model.Orders;
import com.petpuja.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // =====================================
    // CREATE ORDER
    // =====================================

    public Orders createOrder(OrderRequestDTO dto) {

        Orders order = new Orders();

        // TABLE ID
        order.setTableId(
                dto.getTableId());

        // TOTAL PRICE
        order.setTotalPrice(
                dto.getTotalPrice());

        // DEFAULT STATUS
        order.setStatus(
                "PLACED");

        // CREATED TIME
        order.setCreatedTime(
                LocalDateTime.now());

        // SAVE ORDER
        return orderRepository
                .save(order);
    }

    // =====================================
    // GET ORDER
    // =====================================

    public Orders getOrder(Long id) {

        return orderRepository
                .findById(id)
                .orElse(null);
    }

    // =====================================
    // UPDATE ORDER STATUS
    // =====================================

    public String updateOrderStatus(
            Long orderId,
            String status) {

        Orders order =
                orderRepository
                .findById(orderId)
                .orElse(null);

        // ORDER NOT FOUND

        if(order == null){

            return "❌ Order Not Found";
        }

        // UPDATE STATUS

        order.setStatus(status);

        orderRepository.save(order);

        // READY MESSAGE

        if(status.equalsIgnoreCase("READY")){

            return "✅ Your order is ready. Please collect your order.";
        }

        // PREPARING MESSAGE

        else if(status.equalsIgnoreCase("PREPARING")){

            return "👨‍🍳 Your food is being prepared.";
        }

        // DELIVERED MESSAGE

        else if(status.equalsIgnoreCase("DELIVERED")){

            return "🍽️ Order delivered successfully.";
        }

        // CANCELLED MESSAGE

        else if(status.equalsIgnoreCase("CANCELLED")){

            return "❌ Your order has been cancelled.";
        }

        // DEFAULT MESSAGE

        return "✅ Order status updated to : " + status;
    }

    // =====================================
    // AI PREPARATION TIME PREDICTION
    // =====================================

    public String estimatePreparationTime(
            Integer itemCount,
            boolean rushHour) {

        // ACTIVE ORDERS

        long activeOrders =
                orderRepository.findAll()
                .stream()
                .filter(order ->
                        "PLACED".equals(order.getStatus()))
                .count();

        // BASE PREPARATION TIME

        int basePrepTime =
                itemCount * 5;

        // KITCHEN LOAD

        int kitchenLoad =
                (int) activeOrders * 2;

        // RUSH HOUR FACTOR

        int rushFactor = 0;

        if(rushHour){

            rushFactor = 10;
        }

        // FINAL TOTAL TIME

        int totalTime =
                basePrepTime +
                kitchenLoad +
                rushFactor;

        return "⏳ Your order will be ready in "
                + totalTime + " mins";
    }

    // =====================================
    // SMART WAITING TIME
    // =====================================

    public String estimateWaitingTime() {

        // TOTAL TABLES

        long totalTables = 10;

        // OCCUPIED TABLES

        long occupiedTables =
                orderRepository.findAll()
                .stream()
                .filter(order ->
                        "PLACED".equals(order.getStatus()))
                .count();

        // AVAILABLE TABLES

        long availableTables =
                totalTables - occupiedTables;

        // QUEUE LENGTH

        long queueLength =
                occupiedTables;

        // AVERAGE DINING TIME

        int averageDiningTime = 7;

        int waitingTime;

        // WAIT TIME CALCULATION

        if(availableTables <= 0){

            waitingTime =
                    (int)(queueLength *
                            averageDiningTime);

        } else {

            waitingTime =
                    (int)((queueLength *
                            averageDiningTime)
                            / availableTables);
        }

        return "🚶 Estimated waiting time : "
                + waitingTime + " mins";
    }
}