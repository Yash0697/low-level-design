package com.yash.design.atm.service;

import java.time.format.DateTimeFormatter;

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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm:ss");
		String date = dtf2.format(dtf.parse(trans.getDate()));
		System.out.println("Last transaction was of amount "+trans.getAmount()+" and happened on "+date);
	}

	public boolean isBalanceZero() {
		return bank.isBalanceZero(account);
	}
}
