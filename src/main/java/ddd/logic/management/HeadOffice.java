package ddd.logic.management;

import ddd.logic.atm.Atm;
import ddd.logic.common.AggregateRoot;
import ddd.logic.sharedkernel.Money;
import ddd.logic.snackmachine.SnackMachine;

public class HeadOffice extends AggregateRoot {
	
	private float balance;
	private Money cash = Money.None;
	
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
	
	public void changeBalance(float delta) {
		balance += delta;
	}
	
	public HeadOfficeDto convertToHeadOfficeDto() {
		HeadOfficeDto headOfficeDto = new HeadOfficeDto();
		headOfficeDto.setId(id);
		headOfficeDto.setBalance(balance);
		headOfficeDto.setOneCentCount(cash.getOneCentCount());
		headOfficeDto.setTenCentCount(cash.getTenCentCount());
		headOfficeDto.setQuarterCount(cash.getQuarterCount());
		headOfficeDto.setOneDollarCount(cash.getOneDollarCount());
		headOfficeDto.setFiveDollarCount(cash.getFiveDollarCount());
		headOfficeDto.setTwentyDollarCount(cash.getTwentyDollarCount());
		
		return headOfficeDto;
	}
	
	public void unloadCashFromSnackMachine(SnackMachine snackMachine) {
		Money money = snackMachine.unloadMoney();
		cash = cash.add(money);
	}
	
	public void loadCashToAtm(Atm atm) {
		atm.loadMoney(cash);
		cash = Money.None;
	}
}
