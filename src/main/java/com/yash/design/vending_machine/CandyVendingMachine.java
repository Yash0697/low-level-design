package com.yash.design.vending_machine;

import com.yash.design.vending_machine.states.CandySoldState;
import com.yash.design.vending_machine.states.HasCoinState;
import com.yash.design.vending_machine.states.NoCoinState;
import com.yash.design.vending_machine.states.SoldOutState;
import com.yash.design.vending_machine.states.State;
import com.yash.design.vending_machine.states.WinnerState;

import lombok.Getter;
@Getter
public class CandyVendingMachine {
    private State noCoinState;
    private State candySoldState;
    private State soldOutState;
    private State hasCoinState;
    private State winnerState;
    private State state;
    private int numOfCandies;

    public CandyVendingMachine(int numOfCandies) {
        this.numOfCandies = numOfCandies;
        this.noCoinState = new NoCoinState(this);
        this.candySoldState = new CandySoldState(this);
        this.soldOutState = new SoldOutState(this);
        this.hasCoinState = new HasCoinState(this);
        this.winnerState = new WinnerState(this);
        if(numOfCandies > 0) this.state = noCoinState; // if there are candies available, you have to put the coin
        else
            this.state = soldOutState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void insertCoin() {
        this.state.insertCoin();
    }

    public void ejectCoin() {
        this.state.ejectCoin();
    }

    public void pressKey() {
        this.state.pressKey();
        // this.state.dispenseCandy(); // user can't ask machine to dispense a candy directly, only stae should have that responsibility
    }

    public void releaseCandy() {
        System.out.println("Catch the candy from the slot......");
        this.numOfCandies = this.numOfCandies > 0 ? this.numOfCandies - 1 : this.numOfCandies;
    }
}
