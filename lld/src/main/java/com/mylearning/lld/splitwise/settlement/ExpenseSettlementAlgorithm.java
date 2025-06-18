package com.mylearning.lld.splitwise.settlement;

import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.Expense;
import com.mylearning.lld.splitwise.model.User;

import java.util.*;

public class ExpenseSettlementAlgorithm {

    public static PaymentGraph computeSettlement(List<Expense> expenses) {
        Map<String, Integer> balanceMap = new HashMap<>();

        for (Expense expense : expenses) {
            for (Map.Entry<User, Balance> entry : expense.getUserBalances().entrySet()) {
                balanceMap.put(entry.getKey().getUserID(),
                        balanceMap.getOrDefault(entry.getKey().getUserID(), 0) + entry.getValue().getAmount());
            }
        }

        PriorityQueue<PaymentNode> transactions = new PriorityQueue<>(Comparator.comparingInt(t -> t.amount));

        // Separate payers and receivers
        PriorityQueue<Node> positiveHeap = new PriorityQueue<>(Comparator.comparingInt(Node::getFinalBalance).reversed());
        PriorityQueue<Node> negativeHeap = new PriorityQueue<>(Comparator.comparingInt(Node::getFinalBalance));

        for (Map.Entry<String, Integer> entry : balanceMap.entrySet()) {
            if (entry.getValue() > 0) positiveHeap.add(new Node(entry.getKey(), entry.getValue()));
            else if (entry.getValue() < 0) negativeHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (!positiveHeap.isEmpty() && !negativeHeap.isEmpty()) {
            Node receiver = positiveHeap.poll();
            Node sender = negativeHeap.poll();
            int amount = Math.min(receiver.getFinalBalance(), -sender.getFinalBalance());
            transactions.add(new PaymentNode(sender.getUserID(), receiver.getUserID(), amount));

            receiver.finalBalance -= amount;
            sender.finalBalance += amount;

            if (receiver.finalBalance > 0) positiveHeap.add(receiver);
            if (sender.finalBalance < 0) negativeHeap.add(sender);
        }

        return new PaymentGraph(new ArrayList<>(transactions));
    }
}
