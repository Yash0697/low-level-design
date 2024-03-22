package com.yash.design.vending_machine.states;

import com.yash.design.vending_machine.CandyVendingMachine;

public class NoCoinState implements State {

    private CandyVendingMachine candyVendingMachine;
    public NoCoinState(CandyVendingMachine candyVendingMachine) {
        this.candyVendingMachine = candyVendingMachine;
    }


    @Override
    public void insertCoin() {
        System.out.println("You have now inserted the coin, press the key to get the candy");
        candyVendingMachine.setState(candyVendingMachine.getHasCoinState());
    }

    @Override
    public void ejectCoin() {
        System.out.println("You can't eject , you haven't inserted the coin yet");
    }

    @Override
    public void pressKey() {
        System.out.println("You pressed the key, please insert a coin first");
    }

    @Override
    public void dispenseCandy() {
        System.out.println("You can only get the candy once you insert a coin");
    }
}