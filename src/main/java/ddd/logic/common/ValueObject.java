package ddd.logic.common;

public abstract class ValueObject<T> {
	
	@Override
	public boolean equals(Object obj) {
		T valueObject = (T) obj;
		
		if(valueObject == null)
			return false;
		
		return equalsCore(valueObject);
	}
	
	@Override
	public int hashCode() {
		
		return getHashCodeCore();
	}
	
	public abstract boolean equalsCore(T valueObject);
	public abstract int getHashCodeCore();
}
