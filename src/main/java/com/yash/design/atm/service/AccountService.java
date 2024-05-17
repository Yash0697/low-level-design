package com.yash.design.atm.service;

import com.yash.design.atm.ATM;
import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.Bank;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.dto.Transaction;

public class AccountService {

	private Account account;
	private Bank bank;
	public AccountService(Account account) {
		this.account = account;
		bank = ATM.getBank();
	}
	
	public boolean authenticateCreds(String pin) {
		return this.account
				.getCardDetails()
				.stream()
				.filter(a ->  a.getPin().equals(pin)).findAny().isPresent();
				
	}

	public boolean accountExists(CardDetails cardDetails) {
		return this.bank.doesAccountExist(cardDetails);
	}

	public boolean dispenseCash(String amount) {
		return bank.performTransaction(account, amount);
		
	}

	public void printReceipt() {
		Transaction trans = this.bank.printReceipt(account);
		System.out.println("Last transaction was of amount "+trans.getAmount()+" and happened on "+trans.getDate());
	}

	public boolean isBalanceZero() {
		return bank.isBalanceZero(account);
	}
}
