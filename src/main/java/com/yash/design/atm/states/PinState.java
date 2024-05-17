package com.yash.design.atm.states;

import com.yash.design.atm.CashDispenser;
import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.service.AccountService;

public class PinState implements AtmState {

	@SuppressWarnings("unused")
	private CashDispenser cashDispenser;
	private AccountService accountService;
	public PinState(Account account, CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
		this.accountService = new AccountService(account);
	}
	
	@Override
	public void insertCard(CardDetails cardDetails) {
		System.out.println("machine already has a card");
	}

	@Override
	public void enterPin(String pin) {
		System.out.println("You have entered the pin already");
	}

	@Override
	public void dispenseCash(String amount) {
		boolean dispensed = this.accountService.dispenseCash(amount);
		if(dispensed) 
			this.cashDispenser.setState(this.cashDispenser.getCashWithdrawnState());
		else {
			System.out.println("Your account does not have enough cash. Take out your card");
			this.cashDispenser.setState(this.cashDispenser.getNoCardState());
		}
	}

	@Override
	public void ejectCard() {
		System.out.println("Wait before you can take your card after getting the cash");
	}

}
