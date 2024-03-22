package com.yash.design.vending_machine;

public class App {
    public static void main(String[] args) {
        CandyVendingMachine candyVendingMachine = new CandyVendingMachine(10);
        candyVendingMachine.insertCoin();
        candyVendingMachine.pressKey();
        candyVendingMachine.insertCoin();
        candyVendingMachine.pressKey();
    }
}
