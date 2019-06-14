package ddd.logic;

public class Snack extends AggregateRoot {
	
	public static final Snack None = new Snack("");
	public static final Snack Chocolate = new Snack("Chocolate");
	public static final Snack Soda = new Snack("Soda");
	public static final Snack Gun = new Snack("Gun");
	
	private String name;
	
	public Snack() {}
	
	public Snack(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
