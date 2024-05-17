package com.yash.design.atm.states;

import com.yash.design.atm.dto.CardDetails;

public interface AtmState {
	void insertCard(CardDetails cardDetails);
	void enterPin(String pin);
	void dispenseCash(String amount);
	void ejectCard();
}
