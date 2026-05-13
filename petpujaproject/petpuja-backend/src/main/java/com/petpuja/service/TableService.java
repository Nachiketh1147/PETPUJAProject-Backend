package com.petpuja.service;

import com.petpuja.dto.ScanResponseDTO;
import com.petpuja.model.RestaurantTable;
import com.petpuja.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    // CREATE TABLE + QR
    public RestaurantTable createTable(Long tableId) {

        RestaurantTable table =
                new RestaurantTable();

        table.setTableId(tableId);

        // AUTO DETECT SERVER + PORT
        String baseUrl =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .build()
                        .toUriString();

        // AUTO QR URL
        String qrUrl =
                baseUrl + "/scan/" + tableId;

        table.setQrCode(qrUrl);

        // SAVE
        return tableRepository.save(table);
    }

    // QR SCAN
    public ScanResponseDTO scanTable(Long tableId) {

        RestaurantTable table =
                tableRepository.findById(tableId)
                        .orElse(null);

        if(table == null) {
            return null;
        }

        // AUTO DETECT SERVER
        String baseUrl =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .build()
                        .toUriString();

        ScanResponseDTO response =
                new ScanResponseDTO();

        // MESSAGE
        response.setWelcomeMessage(
                "Welcome to PETPUJA 🍽️"
        );

        // TABLE NUMBER
        response.setTableNumber(
                table.getTableId()
        );

        // MENU API
        response.setMenuApi(
                baseUrl + "/menu"
        );

        return response;
    }
}