package com.mylearning.lld.splitwise.settlement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentGraph {
    private List<PaymentNode> transactions;

    //Settling Expenses
    public PaymentGraph(List<PaymentNode> transactions) {
        this.transactions = transactions;
    }
}
