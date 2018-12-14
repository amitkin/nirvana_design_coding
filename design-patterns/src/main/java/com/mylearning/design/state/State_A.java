package com.mylearning.design.state;

public class State_A extends State {
    public void on(  FSM fsm ) {
        System.out.println("State_A + on  = State_C");
        fsm.changeState(2);
    }

    public void off(FSM fsm) {
        System.out.println("State_A + off = State_B");
        fsm.changeState(1);
    }

    public void ack(FSM fsm) {
        System.out.println("State_A + ack = State_A");
        fsm.changeState(0);
    }
}