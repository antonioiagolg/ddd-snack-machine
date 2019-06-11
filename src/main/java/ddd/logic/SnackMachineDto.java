package ddd.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SnackMachineDto {
	
	@Id
	@GeneratedValue
	private long id;
	private int oneCentCount;
	private int tenCentCount;
	private int quarterCount;
	private int oneDollarCount;
	private int fiveDollarCount;
	private int twentyDollarCount;
	private float moneyInTransaction;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOneCentCount() {
		return oneCentCount;
	}
	public void setOneCentCount(int oneCentCount) {
		this.oneCentCount = oneCentCount;
	}
	public int getTenCentCount() {
		return tenCentCount;
	}
	public void setTenCentCount(int tenCentCount) {
		this.tenCentCount = tenCentCount;
	}
	public int getQuarterCount() {
		return quarterCount;
	}
	public void setQuarterCount(int quarterCount) {
		this.quarterCount = quarterCount;
	}
	public int getOneDollarCount() {
		return oneDollarCount;
	}
	public void setOneDollarCount(int oneDollarCount) {
		this.oneDollarCount = oneDollarCount;
	}
	public int getFiveDollarCount() {
		return fiveDollarCount;
	}
	public void setFiveDollarCount(int fiveDollarCount) {
		this.fiveDollarCount = fiveDollarCount;
	}
	public int getTwentyDollarCount() {
		return twentyDollarCount;
	}
	public void setTwentyDollarCount(int twentyDollarCount) {
		this.twentyDollarCount = twentyDollarCount;
	}
	public float getMoneyInTransaction() {
		return moneyInTransaction;
	}
	public void setMoneyInTransaction(float moneyInTransaction) {
		this.moneyInTransaction = moneyInTransaction;
	}
	
	public SnackMachine convertToSnackMachine() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.setMoneyInside(new Money(this.oneCentCount,
				this.tenCentCount,
				this.quarterCount,
				this.oneDollarCount,
				this.fiveDollarCount,
				this.twentyDollarCount));
		snackMachine.setMoneyInTransaction(this.convertMoneyInTransactionToMoney());
		return snackMachine;
	}
	
	public Money convertMoneyInTransactionToMoney() {
		
		int cents = (int) ((this.moneyInTransaction - (Math.ceil(this.moneyInTransaction))) * 100);
		
		int twentyDollarCount = (int) Math.ceil(this.moneyInTransaction) / 20;
		int restTwentyDollarCount = (int) Math.ceil(this.moneyInTransaction) % 20;
		int fiveDollarCount = restTwentyDollarCount / 5;
		int oneDollarCount = restTwentyDollarCount % 5;
		
		int quarterCount = cents / 25;
		int restQuarterCount = cents % 25;
		int tenCentCount = restQuarterCount / 10;
		int centCount = tenCentCount % 10;
		
		return new Money(centCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
	}
	
}
