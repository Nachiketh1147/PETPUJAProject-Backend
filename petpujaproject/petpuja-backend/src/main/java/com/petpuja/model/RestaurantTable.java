package com.petpuja.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class RestaurantTable {

    @Id
    private Long tableId;

    private String qrCode;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}