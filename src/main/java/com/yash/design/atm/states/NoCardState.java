package com.yash.design.atm.states;

import com.yash.design.atm.CashDispenser;
import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.service.AccountService;

public class NoCardState implements AtmState {
	@SuppressWarnings("unused")
	private CashDispenser cashDispenser;
	private AccountService accountService;
	public NoCardState(Account account, CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
		accountService = new AccountService(account);
	}
	
	@Override
	public void insertCard(CardDetails cardDetails) {
		boolean accountExists = accountService.accountExists(cardDetails);
		if(accountExists)
			this.cashDispenser.setState(this.cashDispenser.getHasCardState());
		else
			System.out.println("Account with given card details doesn't exists");
	}

	@Override
	public void enterPin(String pin) {
		System.out.println("No card inserted....");
	}

	@Override
	public void dispenseCash(String amount) {
		System.out.println("Insert the card and enter the pin before getting cash");
	}

	@Override
	public void ejectCard() {
		System.out.println("There is no card in the machine");
	}

}
