package com.yash.design.atm;

import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.BankTransactionRequestParams;
import com.yash.design.atm.dto.CardDetails;

public class AtmTest {

	public static void main(String[] args) {
		ATM atm = new ATM();
		Account account = new Account(1234567L, "Yashendra","1422.32");
		CardDetails cardDetails = new CardDetails(456789076L, "12/02/2028", 666, "123");
		account.addCardDetails(cardDetails);
		BankTransactionRequestParams request = new BankTransactionRequestParams(cardDetails, "withdraw", account, "123", "14.4");
		atm.performTransaction(request);
		CardDetails incorrect = new CardDetails(456789076L, "12/02/2028", 666, "133");
	}

}
