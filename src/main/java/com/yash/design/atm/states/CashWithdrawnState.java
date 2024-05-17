package com.yash.design.atm.states;

import com.yash.design.atm.CashDispenser;
import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.service.AccountService;

public class CashWithdrawnState implements AtmState {
	private CashDispenser cashDispenser;
	private AccountService accountService; 
	public CashWithdrawnState(Account account, CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
		this.accountService = new AccountService(account);
	}
	@Override
	public void insertCard(CardDetails cardDetails) {
		System.out.println("Wait if you want to get a receipt of your transaction");
	}

	@Override
	public void enterPin(String pin) {
		System.out.println("No point entering pin now");
	}

	@Override
	public void dispenseCash(String amount) {
		System.out.println("Already dispensed cash, getting you a receipt");
	}

	@Override
	public void ejectCard() {
		this.accountService.printReceipt();
		if(this.accountService.isBalanceZero()) {
			System.out.println("No cash left now");
			this.cashDispenser.setState(this.cashDispenser.getAccountEmptyState());
		}
		else
			this.cashDispenser.setState(this.cashDispenser.getNoCardState());
			
	}

}
