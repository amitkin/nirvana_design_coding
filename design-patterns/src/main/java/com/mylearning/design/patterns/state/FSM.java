package com.mylearning.design.patterns.state;

// 1. The "wrapper"
public class FSM {
    // 2. States array
    private State[] states  = {new State_A(), new State_B(), new State_C()};

    // 3. Current state
    private int currentState = 0;

    // 4. Delegation and pass this pointer
    public void on()  {
        states[currentState].on(this);
    }

    public void off() {
        states[currentState].off(this);
    }

    public void ack() {
        states[currentState].ack(this);
    }

    public void changeState(int index) {
        currentState = index;
    }
}
