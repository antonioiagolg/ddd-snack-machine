package ddd.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "snackMachineId")
	private List<SlotDto> slotDtoList;
	
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
		snackMachine.setMoneyInTransaction(this.moneyInTransaction);
		List<Slot> slotList = new ArrayList<>();
		for(SlotDto slotDto: slotDtoList) {
			slotList.add(slotDto.convertToSlot());
		}
		snackMachine.setSlots(slotList);
		return snackMachine;
	}
	
	public List<SlotDto> getSlotDtoList() {
		return slotDtoList;
	}
	
	public void setSlotDtoList(List<SlotDto> slotDtoList) {
		this.slotDtoList = slotDtoList;
	}
	
	
	
}
