package com.yash.design.atm.dto;

public class BankTransactionRequestParams {
	private CardDetails cardDetails;
	private String transaction;
	private Account account;
	private String pin;
	private String amount;
	public CardDetails getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public BankTransactionRequestParams(CardDetails cardDetails, String transaction, Account account, String pin,
			String amount) {
		super();
		this.cardDetails = cardDetails;
		this.transaction = transaction;
		this.account = account;
		this.pin = pin;
		this.amount = amount;
	}
	
}
