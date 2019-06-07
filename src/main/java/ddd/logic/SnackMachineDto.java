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
	
}
