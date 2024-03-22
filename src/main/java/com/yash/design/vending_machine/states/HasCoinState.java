package com.yash.design.vending_machine.states;

import java.util.Random;

import com.yash.design.vending_machine.CandyVendingMachine;

public class HasCoinState implements State{
    private Random randomWinner = new Random(System.currentTimeMillis());
    private CandyVendingMachine candyVendingMachine;
    public HasCoinState(CandyVendingMachine candyVendingMachine) {
        this.candyVendingMachine = candyVendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Machine already has a coin, you can't insert one more");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Coin ejected");
        this.candyVendingMachine.setState(this.candyVendingMachine.getNoCoinState());
    }

    @Override
    public void pressKey() {
        System.out.println("Key Pressed");
        int winner = randomWinner.nextInt(10);
        if(winner == 0 && this.candyVendingMachine.getNumOfCandies() > 1)
            this.candyVendingMachine.setState(this.candyVendingMachine.getWinnerState());
        else
            this.candyVendingMachine.setState(this.candyVendingMachine.getCandySoldState());
        this.candyVendingMachine.getState().dispenseCandy();
    }

    @Override
    public void dispenseCandy() {
        System.out.println("Sorry, can't dispense candy before pressing key");
    }
    
}
