package ddd.logic;

public class Slot extends Entity {
	private SnackMachine snackMachine;
	private int position;
	private SnackPile snackPile;
	
	public Slot() {}
	
	public Slot(SnackMachine snackMachine,
			int position) {
		this.snackMachine = snackMachine;
		this.position = position;
		this.snackPile = SnackPile.None;
	}

	public SnackMachine getSnackMachine() {
		return snackMachine;
	}

	public void setSnackMachine(SnackMachine snackMachine) {
		this.snackMachine = snackMachine;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public SnackPile getSnackPile() {
		return snackPile;
	}

	public void setSnackPile(SnackPile snackPile) {
		this.snackPile = snackPile;
	}
	
	public SlotDto convertToSlotDto() {
		return null;
	}
}
