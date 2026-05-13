package com.petpuja.controller;

import com.petpuja.dto.ScanResponseDTO;
import com.petpuja.model.RestaurantTable;
import com.petpuja.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private TableService tableService;

    // CREATE TABLE QR
    @PostMapping("/table/{tableId}")
    public RestaurantTable createTable(
            @PathVariable Long tableId) {

        return tableService.createTable(tableId);
    }
    

    // SCAN QR
    @GetMapping("/scan/{tableId}")
    public ScanResponseDTO scanQR(
            @PathVariable Long tableId) {

        return tableService.scanTable(tableId);
    }
}