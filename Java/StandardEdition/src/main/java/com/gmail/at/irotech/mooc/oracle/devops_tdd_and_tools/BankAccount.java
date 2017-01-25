package com.gmail.at.irotech.mooc.oracle.devops_tdd_and_tools;

/**
 * This object represents a bank account. A bank account has a balance
 * and the ability to deposit money, withdraw money, and charge money
 * using a debit card.
 */
public class BankAccount {
	
	/**
	 * The overdraft fee amount.
	 */
	public static final double OVERDRAFT_FEE_AMOUNT = 20;
	
	/**
	 * The bank account number.
	 */
	private final String accountNumber;

	/**
	 * The bank account balance.
	 */
	private double accountBalance;
	
	/**
	 * Whether or not the bank account has overdraft protection.
	 * Overdraft protection will allow a customer to charge an
	 * amount greater than the account balance without incurring
	 * any overdraft fees.
	 */
	private boolean overdraftProtection;
	
	/**
	 * Constructs a bank account with an account number, zero
	 * balance, and overdraft protection.
	 * 
	 * @param accountNumber the bank account number
	 * @param overdraftProtection the overdraft protection
	 */
	public BankAccount(String accountNumber, boolean overdraftProtection) {
		this.accountNumber = accountNumber;
		this.accountBalance = 0;
		this.overdraftProtection = overdraftProtection;
	}
	
	/**
	 * Deposit an amount. Deposits will increase the account balance by
	 * the amount deposited.
	 * 
	 * <p>
	 * The deposit amount must be greater than zero.
	 * 
	 * @param amount the amount to deposit
	 * @return <code>true</code> if the deposit processed successfully
	 */
	public boolean deposit(double amount) {
		if(amount > 0) {
			accountBalance += amount;
			return true;
		} else if(amount == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Withdraw an amount. Withdrawals will decrease the account balance
	 * by the amount withdrawn.
	 * 
	 * <p>
	 * The withdrawal amount must be greater than zero and may not exceed
	 * the account balance.
	 * 
	 * @param amount the amount to withdraw 
	 * @return <code>true</code> if the withdrawal processed successfully
	 */
	public boolean withdraw(double amount) {
		//TO DO
		return false;
	}
	
	/**
	 * Charge an amount via a debit card transaction. Debit charges will
	 * decrease the account balance by the amount charged, potentially
	 * causing the account balance to drop below zero. Accounts without
	 * overdraft protection will be charged the {@link #OVERDRAFT_FEE_AMOUNT}
	 * when a charge exceeds the account balance.
	 * 
	 * <p>
	 * The charge amount must be greater than zero and may exceed the
	 * account balance.
	 * 
	 * @param amount the amount to charge
	 * @return <code>true</code> if the charge processed successfully
	 */
	public boolean charge(double amount) {
		//TO DO
		return false;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * @return the accountBalance
	 */
	public double getAccountBalance() {
		return this.accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the overdraftProtection
	 */
	public boolean isOverdraftProtection() {
		return this.overdraftProtection;
	}

	/**
	 * @param overdraftProtection the overdraftProtection to set
	 */
	public void setOverdraftProtection(boolean overdraftProtection) {
		this.overdraftProtection = overdraftProtection;
	}

}
