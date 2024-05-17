package com.yash.design.atm.states;

import com.yash.design.atm.CashDispenser;
import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.service.AccountService;

public class HasCardState implements AtmState {

	@SuppressWarnings("unused")
	private CashDispenser cashDispenser;
	private Account account;
	private AccountService accountService;
	public HasCardState(Account account, CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
		this.account = account;
		accountService = new AccountService(account);
	}
	
	@Override
	public void insertCard(CardDetails cardDetails) {
		System.out.println("Machine already has a card");

	}

	@Override
	public void enterPin(String pin) {
		// authenticate the card details
		boolean authenticated = accountService.authenticateCreds(pin);
		if(authenticated)
			this.cashDispenser.setState(this.cashDispenser.getPinState());
		else {
			System.out.println("You entered the wrong pin");
			this.cashDispenser.setState(this.cashDispenser.getNoCardState());
		}
		
		
	}

	@Override
	public void dispenseCash(String amount) {
		System.out.println("You can only take cash once the pin is entered");
	}

	@Override
	public void ejectCard() {
		System.out.println("You haven't received the cash yet");
	}

}
