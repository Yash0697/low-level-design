package com.yash.design.atm.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bank {

	Set<Account> accounts;
	Map<String, Account> accountsMap;
	private String bankName;

	public Bank(String name) {
		this.accounts = new HashSet<>();
		this.bankName = name;
		accountsMap = new HashMap<>();
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
		for(Account account: accounts) {
			accountsMap.put(String.valueOf(account.getAccountNumber()), account);
		}
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
		accountsMap.put(String.valueOf(account.getAccountNumber()), account);
	}

	public boolean doesAccountExist(CardDetails cardDetails) {
		for (Account account : accounts) {
			List<CardDetails> cardDetails2 = account.getCardDetails();
			for (CardDetails cardDetails21 : cardDetails2) {
				if (cardDetails21.getCardNumber().longValue()==cardDetails.getCardNumber().longValue())
					return true;
			}
		}
		return false;

	}

	public boolean performTransaction(Account account, String amount) {
		if (accountsMap.containsKey(String.valueOf(account.getAccountNumber()))) {
			double currentAccountBalance = Double.parseDouble(account.getAmount());
			double transAmount = Double.parseDouble(amount);
			if (currentAccountBalance < transAmount) {
				System.out.println("Not enough balance");
				return false;
			}
			account.setAmount(String.valueOf(currentAccountBalance - transAmount));
			Transaction trans = new Transaction(amount, LocalDateTime.now().toString());
			account.addTransaction(trans);
			return true;
		}
		System.out.println("Account doesn't exist");
		return false;
	}

	public boolean isBalanceZero(Account account) {
		if (accountsMap.containsKey(String.valueOf(account.getAccountNumber()))) {
			return Double.parseDouble(account.getAmount()) == 0.0;
		}
		System.out.println("Account doesn't exist");
		return false;
	}

	public Transaction printReceipt(Account account) {
		if (accountsMap.containsKey(String.valueOf(account.getAccountNumber()))) {
			return !account.getTransactionHistory().isEmpty()
					? account.getTransactionHistory().get(account.getTransactionHistory().size() - 1)
					: new Transaction("", "");
		}
		System.out.println("Account doesn't exist");
		return new Transaction("", "");
	}

}
