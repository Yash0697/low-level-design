package com.yash.design.vending_machine.states;

import com.yash.design.vending_machine.CandyVendingMachine;

public class WinnerState implements State {

    private CandyVendingMachine candyVendingMachine;
    public WinnerState(CandyVendingMachine candyVendingMachine) {
        this.candyVendingMachine = candyVendingMachine;
    }
    @Override
    public void insertCoin() {
        System.out.println("Please wait, don't insert the coin, you will get a candy now");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Sorry, you pressed the key, you will get a candy now");
    }

    @Override
    public void pressKey() {
        System.out.println("You can press only once after inserting the coin");
    }

    @Override
    public void dispenseCandy() {
        this.candyVendingMachine.releaseCandy();
        if(candyVendingMachine.getNumOfCandies() == 0)
            this.candyVendingMachine.setState(this.candyVendingMachine.getSoldOutState());
        else {
            // get two candies 
            this.candyVendingMachine.releaseCandy();
            System.out.println("You are a WINNER! You got two candies for your coin");
            if(candyVendingMachine.getNumOfCandies() > 0)
            this.candyVendingMachine.setState(this.candyVendingMachine.getNoCoinState());
        else {
            System.out.println("No more candies left now");
            this.candyVendingMachine.setState(this.candyVendingMachine.getSoldOutState());
        }
        } 
    }
    
}
