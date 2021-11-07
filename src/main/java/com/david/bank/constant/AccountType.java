package com.david.bank.constant;

public enum AccountType {
    SAVING("Savings"),
    CURRENT("Current");

    private final String description;

    public String getDescription(){
        return this.description;
    }

    AccountType(String desc) {
        this.description = desc;
    }
}
