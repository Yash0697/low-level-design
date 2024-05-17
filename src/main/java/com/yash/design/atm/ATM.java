package com.yash.design.atm;

import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.Bank;
import com.yash.design.atm.dto.BankTransactionRequestParams;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.factory.AccountFactory;

public class ATM {

	private static Bank bank;

	public static Bank getBank() {
		if(bank == null) {
			bank = new Bank("HDFC");
			bank.setAccounts(AccountFactory.populateAccounts());
		}
		return bank;
	}
	
	public void performTransaction(BankTransactionRequestParams bankTransaction) {
		Account account = bankTransaction.getAccount();
		CardDetails cardDetails = bankTransaction.getCardDetails();
		String amount = bankTransaction.getAmount();
		String pin = bankTransaction.getPin();
		CashDispenser cashDispenser = new CashDispenser(account);
		String transaction = bankTransaction.getTransaction();
		switch(transaction) {
			case "withdraw": 
				cashDispenser.insertCard(cardDetails);
				cashDispenser.enterPin(pin);
				cashDispenser.dispenseCash(amount);
				cashDispenser.ejectCard();
				break;
//			---  more cases here
			default:
				System.out.println("Oops! not valud transaction. What have you chosen?");
		}
	}
}
