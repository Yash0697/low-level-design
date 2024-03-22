package com.yash.design.vending_machine.states;

import com.yash.design.vending_machine.CandyVendingMachine;

public class SoldOutState implements State {

    @SuppressWarnings("unused")
    private CandyVendingMachine candyVendingMachine;
    public SoldOutState(CandyVendingMachine candyVendingMachine) {
        this.candyVendingMachine = candyVendingMachine;
    }
    @Override
    public void insertCoin() {
        System.out.println("You can't insert a coin, there are no more candies left");
    }

    @Override
    public void ejectCoin() {
        System.out.println("You can't eject , you haven't inserted the coin yet");

    }

    @Override
    public void pressKey() {
        System.out.println("You pressed the key but there are no more candies left");
    }

    @Override
    public void dispenseCandy() {
        System.out.println("No candies left to be dispensed");
    }
    
}
