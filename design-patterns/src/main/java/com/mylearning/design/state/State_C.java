package com.mylearning.design.state;

// 7. Only override some messages
public class State_C extends State {
    // 8. "call back to" the wrapper class
    public void on(FSM fsm) {
        System.out.println("State_C + on  = State_B");
        fsm.changeState(1);
    }
}