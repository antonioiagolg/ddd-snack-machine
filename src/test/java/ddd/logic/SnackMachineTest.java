package ddd.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnackMachineTest {
	
	@Test
	public void return_money_empties_money_in_transaction() {
		SnackMachine snackMachine = new SnackMachine();
		Money dollar = new Money(0,0,0,1,0,0);
		snackMachine.insertMoney(dollar);
		snackMachine.returnMoney();
		
		assertEquals(snackMachine.getMoneyInTransaction().getAmount(), 0, 0);
	}
}
