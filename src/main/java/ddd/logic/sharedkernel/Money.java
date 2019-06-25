package ddd.logic.sharedkernel;

import ddd.logic.common.ValueObject;

public class Money extends ValueObject<Money>{
	
	public static Money None = new Money(0,0,0,0,0,0);
	public static Money Cent = new Money(1,0,0,0,0,0);
	public static Money TenCent = new Money(0,1,0,0,0,0);
	public static Money Quarter = new Money(0,0,1,0,0,0);
	public static Money Dollar = new Money(0,0,0,1,0,0);
	public static Money FiveDollar = new Money(0,0,0,0,1,0);
	public static Money TwentyDollar = new Money(0,0,0,0,0,1);
	
	private final int oneCentCount;
	private final int tenCentCount;
	private final int quarterCount;
	private final int oneDollarCount;
	private final int fiveDollarCount;
	private final int twentyDollarCount;
	
	public static Money add(Money aMoney, Money other) {
		return new Money(
				aMoney.oneCentCount + other.oneCentCount,
				aMoney.tenCentCount + other.tenCentCount,
				aMoney.quarterCount + other.quarterCount,
				aMoney.oneDollarCount + other.oneDollarCount,
				aMoney.fiveDollarCount + other.fiveDollarCount,
				aMoney.twentyDollarCount + other.twentyDollarCount);
	}
	
	public boolean canAllocate(float amount) {
		Money money = allocateCore(amount);
		return money.getAmount() == amount;
	}
	
	public Money allocate(float amount) {
		if(!canAllocate(amount))
			throw new IllegalStateException();
		return allocateCore(amount);
	}
	
	private Money allocateCore(float amount) {
		int twentyDollarCount = Math.min((int) (amount / 20), this.twentyDollarCount);
		amount = amount - twentyDollarCount * 20;
		int fiveDollarCount = Math.min((int) (amount / 5), this.fiveDollarCount);
		amount = amount - fiveDollarCount * 5;
		int oneDollarCount = Math.min((int) amount, this.oneDollarCount);
		amount = amount - oneDollarCount;
		
		int quarterCount = Math.min((int) (amount / 0.25f), this.quarterCount);
		amount = amount - quarterCount * 0.25f;
		int tenCentCount = Math.min((int) (amount / 0.10f), this.tenCentCount);
		amount = amount - tenCentCount * 0.10f;
		int oneCentCount = Math.min((int) (amount / 0.01f), this.oneCentCount);
		amount = amount - oneCentCount * 0.01f;
		
		return new Money(oneCentCount,
				tenCentCount,
				quarterCount,
				oneDollarCount,
				fiveDollarCount,
				twentyDollarCount);
	}
	
	public Money(int oneCentCount,
			int tenCentCount,
			int quarterCount,
			int oneDollarCount,
			int fiveDollarCount,
			int twentyDollarCount) {
		
		if(oneCentCount < 0)
			throw new IllegalArgumentException();
		
		if(tenCentCount < 0)
			throw new IllegalArgumentException();
		
		if(quarterCount < 0)
			throw new IllegalArgumentException();
		
		if(oneDollarCount < 0)
			throw new IllegalArgumentException();
		
		if(fiveDollarCount < 0)
			throw new IllegalArgumentException();
		
		if(twentyDollarCount < 0)
			throw new IllegalArgumentException();
		
		this.oneCentCount = oneCentCount;
		this.tenCentCount = tenCentCount;
		this.quarterCount = quarterCount;
		this.oneDollarCount = oneDollarCount;
		this.fiveDollarCount = fiveDollarCount;
		this.twentyDollarCount = twentyDollarCount;
	}
	
	public Money add(Money other) {
		return new Money(
				this.oneCentCount + other.oneCentCount,
				this.tenCentCount + other.tenCentCount,
				this.quarterCount + other.quarterCount,
				this.oneDollarCount + other.oneDollarCount,
				this.fiveDollarCount + other.fiveDollarCount,
				this.twentyDollarCount + other.twentyDollarCount);
	}
	
	public Money subtract(Money other) {
		return new Money(
				this.oneCentCount - other.oneCentCount,
				this.tenCentCount - other.tenCentCount,
				this.quarterCount - other.quarterCount,
				this.oneDollarCount - other.oneDollarCount,
				this.fiveDollarCount - other.fiveDollarCount,
				this.twentyDollarCount - other.twentyDollarCount);
	}

	@Override
	public boolean equalsCore(Money valueObject) {
		
		return this.oneCentCount == valueObject.oneCentCount
				&& this.tenCentCount == valueObject.tenCentCount
				&& this.quarterCount == valueObject.quarterCount
				&& this.oneDollarCount == valueObject.oneDollarCount
				&& this.fiveDollarCount == valueObject.fiveDollarCount
				&& this.twentyDollarCount == valueObject.twentyDollarCount;
	}

	@Override
	public int getHashCodeCore() {
		int hashCode = this.oneCentCount;
		hashCode = (hashCode * 397) ^ this.tenCentCount;
		hashCode = (hashCode * 397) ^ this.quarterCount;
		hashCode = (hashCode * 397) ^ this.oneDollarCount;
		hashCode = (hashCode * 397) ^ this.fiveDollarCount;
		hashCode = (hashCode * 397) ^ this.twentyDollarCount;
		return hashCode;
	}

	public int getOneCentCount() {
		return oneCentCount;
	}

	public int getTenCentCount() {
		return tenCentCount;
	}

	public int getQuarterCount() {
		return quarterCount;
	}

	public int getOneDollarCount() {
		return oneDollarCount;
	}

	public int getFiveDollarCount() {
		return fiveDollarCount;
	}

	public int getTwentyDollarCount() {
		return twentyDollarCount;
	}
	
	public float getAmount() {
		return this.oneCentCount * 0.01f +
				this.tenCentCount * 0.10f +
				this.quarterCount * 0.25f +
				this.oneDollarCount * 1f +
				this.fiveDollarCount * 5f +
				this.twentyDollarCount * 20f;
	}
	
	@Override
	public String toString() {
		if(getAmount() < 1) {
			return "c" + getAmount() * 100; 
		} else {
			return "$" + getAmount();
		}
	}
}
