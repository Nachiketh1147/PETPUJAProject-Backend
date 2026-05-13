package com.petpuja.dto;

public class ScanResponseDTO {

    private String welcomeMessage;

    private Long tableNumber;

    private String menuApi;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Long tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMenuApi() {
        return menuApi;
    }

    public void setMenuApi(String menuApi) {
        this.menuApi = menuApi;
    }
}