package com.gmail.at.irotech.mooc.oracle.devops_tdd_and_tools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Performs unit tests for the BankAccount object.
 */
public class BankAccountTester {
	
	/**
	 * The bank account to be tested.
	 */
	private BankAccount bankAccount;
	
	/**
	 * Prepares a bank account for the test cases.
	 */
	@Before
	public void prepareBankAccount() {
		this.bankAccount = new BankAccount("123456789", false);
		this.bankAccount.setAccountBalance(100);
	}

	/**
	 * Tests a scenario in which a negative amount is deposited. A negative deposit will
	 * not process successfully and the account balance will not be modified.
	 */
	@Test
	public void testDepositNegativeAmount() {
		Assert.assertFalse(bankAccount.deposit(-10));
	}
	
	/**
	 * Tests a scenario in which a zero amount is deposited. A zero deposit will process
	 * successfully and the account balance will not be modified.
	 */
	@Test
	public void testDepositZeroAmount() {
		Assert.assertTrue(bankAccount.deposit(0));
		Assert.assertEquals(100, bankAccount.getAccountBalance(), 0.001);
	}
	
	/**
	 * Tests a scenario in which a positive amount is deposited. A positive deposit will
	 * process successfully and the account balance will increase by the amount deposited.
	 */
	@Test
	public void testDepositPositiveAmount() {
		Assert.assertTrue(this.bankAccount.deposit(20));
		Assert.assertEquals(120, this.bankAccount.getAccountBalance(), 0.001);
	}

	/**
	 * TO DO
	 * Methods and tests for Deposit, Withdraw, Charge
	 */
}
