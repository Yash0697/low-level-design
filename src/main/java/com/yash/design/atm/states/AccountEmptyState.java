package com.yash.design.atm.states;

import com.yash.design.atm.CashDispenser;
import com.yash.design.atm.dto.CardDetails;

public class AccountEmptyState implements AtmState {

	private CashDispenser cashDispenser;
	public AccountEmptyState(CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
	}
	@Override
	public void insertCard(CardDetails cardDetails) {
		System.out.println("Account is empty");
	}

	@Override
	public void enterPin(String pin) {
		System.out.println("Account is empty");

	}

	@Override
	public void dispenseCash(String amount) {
		System.out.println("Account is empty");

	}

	@Override
	public void ejectCard() {
		System.out.println("Account is empty");

	}

}
