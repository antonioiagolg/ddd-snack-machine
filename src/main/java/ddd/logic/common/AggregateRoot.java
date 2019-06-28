package ddd.logic.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEvent;

public abstract class AggregateRoot extends Entity {
	private List<ApplicationEvent> domainEvents = new ArrayList<>();
	
	protected void addDomainEvent(ApplicationEvent event) {
		domainEvents.add(event);
	}
	
	public void clearEvents() {
		domainEvents.clear();
	}
	
	public List<ApplicationEvent> getDomainEvents() {
		return this.domainEvents;
	}
}
