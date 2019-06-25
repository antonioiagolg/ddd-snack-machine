package ddd.logic.snackmachine;

import ddd.logic.common.AggregateRoot;

public class Snack extends AggregateRoot {
	
	public static final Snack None = new Snack(0, "");
	public static final Snack Chocolate = new Snack(1, "Chocolate");
	public static final Snack Soda = new Snack(2, "Soda");
	public static final Snack Gun = new Snack(3, "Gun");
	
	private String name;
	private long id;
	
	private Snack(long id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public SnackDto convertToSnackDto() {
		SnackDto snackDto = new SnackDto();
		snackDto.setId(id);
		snackDto.setName(name);
		
		return snackDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
