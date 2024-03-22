package com.yash.design.vending_machine.states;

public interface State {
    void insertCoin();
    void ejectCoin();
    void pressKey();
    void dispenseCandy();
}
