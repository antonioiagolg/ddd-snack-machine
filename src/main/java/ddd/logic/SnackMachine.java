package ddd.logic;

public final class SnackMachine {
	
	private Money moneyInside;
	private Money moneyInTransaction;
	
	public void insertMoney(Money money) {
		moneyInTransaction = Money.add(moneyInTransaction, money);
	}
	
	public void returnMoney() {
		
	}
	
	public void buySnack() {
		moneyInside = Money.add(moneyInside, moneyInTransaction);
	}

}
