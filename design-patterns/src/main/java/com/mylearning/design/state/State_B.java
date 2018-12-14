package com.mylearning.design.state;

public class State_B extends State {
    public void on(FSM fsm) {
        System.out.println("State_B + on  = State_A");
        fsm.changeState(0);
    }

    public void off(FSM fsm) {
        System.out.println("State_B + off = State_C");
        fsm.changeState(2);
    }
}
