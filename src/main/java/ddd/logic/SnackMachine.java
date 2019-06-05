package ddd.logic;
import static ddd.logic.Money.None;
public final class SnackMachine extends Entity {
	
	private Money moneyInside = None;
	private Money moneyInTransaction = None;
	
	public SnackMachine() {
	}
	
	public void insertMoney(Money money) {
		moneyInTransaction = moneyInTransaction.add(money);
	}
	
	public void returnMoney() {
		this.moneyInTransaction = None;
	}
	
	public void buySnack() {
		moneyInside = moneyInside.add(moneyInTransaction);
	}

	public Money getMoneyInTransaction() {
		return this.moneyInTransaction;
	}

}
