package com.yash.design.atm.factory;

import java.util.HashSet;
import java.util.Set;

import com.yash.design.atm.dto.Account;
import com.yash.design.atm.dto.CardDetails;

public class AccountFactory {

	public static Set<Account> populateAccounts() {
		Set<Account> accounts = new HashSet<>();
		Account account = new Account(1234567L, "Yashendra","1422.32");
		CardDetails cardDetails = new CardDetails(456789076L, "12/02/2028", 666, "5664");
		CardDetails cardDetails2 = new CardDetails(78987654L, "03/10/2026", 974, "888");
		account.addCardDetails(cardDetails);
		Account account2 = new Account(1234565L, "Carlos","65432.32");
		account2.addCardDetails(cardDetails2);
		accounts.add(account);
		accounts.add(account2);
		return accounts;
	}
}
