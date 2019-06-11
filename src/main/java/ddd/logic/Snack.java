package ddd.logic;

public class Snack extends AggregateRoot {
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
