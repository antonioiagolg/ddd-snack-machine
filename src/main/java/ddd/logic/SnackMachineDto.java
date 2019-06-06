package ddd.logic;

public class SnackMachineDto {
	private Money moneyInside;
	private Money moneyInTransaction;
	private long id;
	
	public Money getMoneyInside() {
		return moneyInside;
	}
	public void setMoneyInside(Money moneyInside) {
		this.moneyInside = moneyInside;
	}
	public Money getMoneyInTransaction() {
		return moneyInTransaction;
	}
	public void setMoneyInTransaction(Money moneyInTransaction) {
		this.moneyInTransaction = moneyInTransaction;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
