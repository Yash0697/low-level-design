package com.yash.design.atm;

import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;
import com.yash.design.atm.states.AccountEmptyState;
import com.yash.design.atm.states.AtmState;
import com.yash.design.atm.states.CashWithdrawnState;
import com.yash.design.atm.states.HasCardState;
import com.yash.design.atm.states.NoCardState;
import com.yash.design.atm.states.PinState;

public class CashDispenser {

	private AtmState hasCardState;
	private AtmState noCardState;
	private AtmState pinState;
	private AtmState accountEmptyState;
	private AtmState cashWithdrawnState;
	
	private AtmState state;
	private Account account;
	public CashDispenser(Account account) {
		this.account = account;
		this.hasCardState = new HasCardState(this.account, this);
		this.noCardState = new NoCardState(account, this);
		this.pinState = new PinState(this.account, this);
		this.accountEmptyState = new AccountEmptyState(this);
		this.cashWithdrawnState = new CashWithdrawnState(this.account, this);
		this.state = noCardState;
	}
	
	public void setState(AtmState state) {
		this.state = state;
	}
	
	public void insertCard(CardDetails cardDetails) {
		this.state.insertCard(cardDetails);
	}

	public void enterPin(String pin) {
		this.state.enterPin(pin);
	}

	public void dispenseCash(String amount) {
		this.state.dispenseCash(amount);
	}

	public void ejectCard() {
		this.state.ejectCard();
	}

	public AtmState getHasCardState() {
		return hasCardState;
	}

	public AtmState getNoCardState() {
		return noCardState;
	}

	public AtmState getPinState() {
		return pinState;
	}

	public AtmState getAccountEmptyState() {
		return accountEmptyState;
	}

	public AtmState getCashWithdrawnState() {
		return cashWithdrawnState;
	}

	public AtmState getState() {
		return state;
	}
	
	
}
