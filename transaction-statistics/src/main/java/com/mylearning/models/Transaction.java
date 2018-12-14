package com.mylearning.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    @JsonProperty("amount")
    @NotNull
    private String amount;

    @JsonProperty("timestamp")
    @NotNull
    private String timestamp;

    public Transaction() {
    }

    public Transaction(String timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(String amount, String timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("Transaction[amount=%s, timestamp=%s]", amount, timestamp);
    }

}
