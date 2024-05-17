package com.yash.design.atm.dto;

import java.util.Objects;

public final class CardDetails {
	private Long cardNumber;
	private String expiry;
	private int cvv;
	private String pin;
	public CardDetails(Long cardNumber, String expiry, int cvv, String pin) {
		super();
		this.cardNumber = cardNumber;
		this.expiry = expiry;
		this.cvv = cvv;
		this.pin = pin;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public String getExpiry() {
		return expiry;
	}
	public int getCvv() {
		return cvv;
	}
	public String getPin() {
		return pin;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, cvv, expiry, pin);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardDetails other = (CardDetails) obj;
		return Objects.equals(cardNumber, other.cardNumber) && cvv == other.cvv && Objects.equals(expiry, other.expiry)
				&& Objects.equals(pin, other.pin);
	}
	
}
