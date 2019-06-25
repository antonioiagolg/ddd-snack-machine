package ddd.logic.management;

import ddd.logic.common.AggregateRoot;
import ddd.logic.sharedkernel.Money;

public class HeadOffice extends AggregateRoot {
	
	private float balance;
	private Money cash;
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public Money getCash() {
		return cash;
	}
	public void setCash(Money cash) {
		this.cash = cash;
	}
}
