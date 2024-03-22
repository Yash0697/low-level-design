package com.yash.design.vending_machine.states;

import com.yash.design.vending_machine.CandyVendingMachine;


public class CandySoldState implements State {

    private CandyVendingMachine candyVendingMachine;
    public CandySoldState(CandyVendingMachine candyVendingMachine) {
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
        // sold state: check if we have more candies left, if yes set the state to noCoinState
        // otherwise to SoldOutState
        this.candyVendingMachine.releaseCandy();
        if(candyVendingMachine.getNumOfCandies() > 0)
            this.candyVendingMachine.setState(this.candyVendingMachine.getNoCoinState());
        else {
            System.out.println("No more candies left now");
            this.candyVendingMachine.setState(this.candyVendingMachine.getSoldOutState());
        }
    }
    
}
