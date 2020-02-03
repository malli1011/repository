package com.Interview.questions;

import java.text.MessageFormat;
import java.util.*;


public class BlackRock_HackerRank {
	

	    private static final String TEXT =  "I am a {0} account with {1,number,#} units of {2} currency";

	    public static void main(String args[] ) throws Exception {

	        List<BankAccount> accounts = new ArrayList<BankAccount>();
	        accounts.add(new SavingsAccount("USD",3));//Savings
	        accounts.add(new SavingsAccount("EUR",2));//Savings
	        accounts.add(new CheckingAccount("HUF",100));//Checking
	        accounts.add(new CheckingAccount("COP",10000));//Checking
	        accounts.add(new BrokerageAccount("GBP",2));//Brokerage
	        accounts.add(new BrokerageAccount("INR",600));//Brokerage
	        
	        accounts.stream().forEach(
	                                    account -> System.out.println(
	                                        MessageFormat.format(TEXT,
	                                            new Object[]{
	                                            account.getAccountType().getName(),//make this work
	                                            account.getUnits(),//make this work
	                                            account.getCurrency()//make this work
	                                                           })));
	    }
	

	
}


class BankAccount {
	AccountType accountType;
	String currency;
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	int units;
	
	public BankAccount(String curr,int unit) {
		this.currency=curr;
		this.units = unit;
	}
	
	public  AccountType getAccountType() {
		return this.accountType;
	};
	
}

class SavingsAccount extends  BankAccount{
	public SavingsAccount(String curr,int unit) {
		super(curr,unit);
		this.accountType = new AccountType("Savings");
	}
	
	
}

class CheckingAccount extends  BankAccount {
	public CheckingAccount(String curr,int unit) {
		super(curr,unit);
		this.accountType = new AccountType("Checking");
	}
	
	
}
class BrokerageAccount extends  BankAccount {
	public BrokerageAccount(String curr,int unit) {
		super(curr,unit);
		this.accountType = new AccountType("Brokerage");
	}
}

class AccountType{
	public AccountType(String type){
		this.name=type;
	}
	String name;
	public String getName() {
		return this.name;
	}
}

