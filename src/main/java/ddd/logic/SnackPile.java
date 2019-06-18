package ddd.logic;

public class SnackPile extends ValueObject<SnackPile>{
	
	public static SnackPile None = new SnackPile(Snack.None, 0, 0);
	
	private Snack snack;
	private int quantity;
	private float price;

	public SnackPile() {}
	
	public SnackPile(Snack snack, int quantity, float price) {
		if(quantity < 0) {
			throw new IllegalStateException();
		}
		
		if(price < 0) {
			throw new IllegalStateException();
		}
		
		this.snack = snack;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public boolean equalsCore(SnackPile valueObject) {
		// TODO Auto-generated method stub
		return this.snack.equals(valueObject.snack) &&
				this.quantity == valueObject.quantity &&
				this.price == valueObject.price;
	}

	@Override
	public int getHashCodeCore() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + quantity;
		result = prime * result + ((snack == null) ? 0 : snack.hashCode());
		
		return result;
	}
	
	public SnackPile subtractOne() {
		return new SnackPile(this.snack, this.quantity - 1, this.price);
	}

	public Snack getSnack() {
		return snack;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getPrice() {
		return price;
	}
	
}
