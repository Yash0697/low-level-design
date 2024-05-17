package com.yash.design.atm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {

	private Long accountNumber;
	private String accountHolderName;
	private String amount;
	private List<Transaction> transactionHistory;
	private List<CardDetails> cardDetails;
	public Account(Long accountNumber, String accountHolderName, String amount) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.amount = amount;
		cardDetails = new ArrayList<>();
		transactionHistory = new ArrayList<>();
	}
	
	public void addCardDetails(CardDetails cardDetails) {
		List<CardDetails> cardDetails2 = this.getCardDetails();
		cardDetails2.add(cardDetails);
		this.setCardDetails(cardDetails2);
	}
	public void addTransaction(Transaction transaction) {
		List<Transaction> transaction2 = this.getTransactionHistory();
				transaction2.add(transaction);
		this.setTransactionHistory(transaction2);
	}
	
	public List<CardDetails> getCardDetails() {
		return this.cardDetails;
	}
	
	public void setCardDetails(List<CardDetails> cardDetails) {
		this.cardDetails = cardDetails;;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountNumber == other.accountNumber;
	}
	
	
}
