package ddd.logic;
import static ddd.logic.Money.None;

import java.util.Arrays;
public final class SnackMachine extends Entity {
	
	private Money moneyInside = None;
	private Money moneyInTransaction = None;
	
	public SnackMachine() {
	}
	
	public void insertMoney(Money money) {
		Money[] coinsAndNotes = {Money.Cent,
				Money.TenCent,
				Money.Quarter,
				Money.Dollar,
				Money.FiveDollar,
				Money.TwentyDollar};
		
		if(!Arrays.asList(coinsAndNotes).contains(money))
			throw new IllegalStateException();
		
		moneyInTransaction = Money.add(money, moneyInTransaction);
	}
	
	public void returnMoney() {
		this.moneyInTransaction = None;
	}
	
	public void buySnack() {
		moneyInside = Money.add(moneyInside, moneyInTransaction);
		moneyInTransaction = None;
	}

	public Money getMoneyInTransaction() {
		return this.moneyInTransaction;
	}

	public Money getMoneyInside() {
		return this.moneyInside;
	}
	
	public SnackMachineDto convertToSnackMachineDto() {
		SnackMachineDto snackMachineDto = new SnackMachineDto();
		snackMachineDto.setId(this.id);
		snackMachineDto.setMoneyInTransaction(moneyInTransaction.getAmount());
		snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());
		snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());
		snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());
		snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());
		snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
		snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
		
		return snackMachineDto;
	}

}
