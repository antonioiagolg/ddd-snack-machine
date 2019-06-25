package ddd.logic.snackmachine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SnackDto {
	
	@Id
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Snack convertToSnack() {
		if(id == 0) return Snack.None;
		if(id == 1) return Snack.Chocolate;
		if(id == 2) return Snack.Gun;
		return Snack.Gun;
	}
}
