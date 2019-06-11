package ddd.logic;
import static ddd.logic.Money.None;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public final class SnackMachine extends AggregateRoot {
	
	private Money moneyInside;
	private float moneyInTransaction;
	private List<Slot> slots;
	
	public SnackMachine() {
		this.moneyInside = None;
		this.moneyInTransaction = 0;
		this.slots = new ArrayList<>();
		
		slots.add(new Slot(this, 1));
		slots.add(new Slot(this, 2));
		slots.add(new Slot(this, 3));
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
		
		moneyInTransaction += money.getAmount();
		
		moneyInside = Money.add(money, moneyInside);
	}
	
	public void returnMoney() {
		Money moneyToReturn = moneyInside.allocate(moneyInTransaction);
		moneyInside = moneyInside.subtract(moneyToReturn);
		this.moneyInTransaction = 0;
	}
	
	public void buySnack(int position) {
		
		Slot slot = this.getSlot(position);
		
		if(slot != null) {
			if(slot.getSnackPile().getPrice() > this.moneyInTransaction) {
				throw new IllegalStateException();
			}
			slot.setSnackPile(slot.getSnackPile().subtractOne());
			moneyInTransaction = 0;
		}
	}

	public float getMoneyInTransaction() {
		return this.moneyInTransaction;
	}

	public Money getMoneyInside() {
		return this.moneyInside;
	}
	
	public void setMoneyInside(Money money) {
		this.moneyInside = money;
	}
	
	public void setMoneyInTransaction(float money) {
		this.moneyInTransaction = money;
	}

	public SnackMachineDto convertToSnackMachineDto() {
		SnackMachineDto snackMachineDto = new SnackMachineDto();
		snackMachineDto.setId(this.id);
		snackMachineDto.setMoneyInTransaction(moneyInTransaction);
		List<SlotDto> slotDtoList = new ArrayList();
		for(Slot slot: slots) {
			slotDtoList.add(slot.convertToSlotDto());
		}
		snackMachineDto.setSlotDtoList(slotDtoList);
		snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());
		snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());
		snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());
		snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());
		snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
		snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
		
		return snackMachineDto;
	}
	
	public void loadSnacks(int position, SnackPile snackPile) {
		Slot slot = this.getSlot(position);

		if(slot != null) {
			slot.setSnackPile(snackPile);
		}
	}
	
	public void loadMoney(Money money) {
		this.moneyInside = money;
	}
	
	public SnackPile getSnackPile(int position) {
		
		Slot slot = this.getSlot(position);
		if(slot != null) {
			return slot.getSnackPile();
		}
		
		return SnackPile.None;
				
	}
	
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	
	private Slot getSlot(int position) {
		return slots.stream()
				.filter(x -> x.getPosition() == position)
				.findAny()
				.orElse(null);
	}

}
