package com.yash.design.atm.dto;

public class Transaction {

	private String amount;
	private String date;
	public Transaction(String amount, String date) {
		super();
		this.amount = amount;
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
