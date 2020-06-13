package com.mylearning.design.patterns.state;

/*
This pattern can be observed in a vending machine.
Vending machines have states based on the inventory, amount of currency deposited, the ability to make change, the item selected, etc.
When currency is deposited and a selection is made, a vending machine will either deliver a product and no change, deliver a product and
change, deliver no product due to insufficient currency on deposit, or deliver no product due to inventory depletion.
*/
public class StateDemo {
    public static void main(String[] args) {
        FSM fsm  = new FSM();
        int[] msgs = {2, 1, 2, 1, 0, 2, 0, 0};
        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else if (msg == 2) {
                fsm.ack();
            }
        }
    }
}
