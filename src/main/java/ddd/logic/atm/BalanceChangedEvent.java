package ddd.logic.atm;

import org.springframework.context.ApplicationEvent;

public class BalanceChangedEvent extends ApplicationEvent {

	private float delta;

	public BalanceChangedEvent(Object source, float delta) {
		super(source);
		this.delta = delta;
		
	}
	
	public float getDelta() {
		return this.delta;
	}

}
