package ddd.logic;

import static ddd.logic.Money.Dollar;
import static ddd.logic.Money.Cent;
import static ddd.logic.Money.None;
import static ddd.logic.Money.Quarter;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnackMachineTest {
	
	@Test
	public void return_money_empties_money_in_transaction() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.insertMoney(Dollar);
		snackMachine.returnMoney();
		
		assertEquals(snackMachine.getMoneyInTransaction(), 0, 0);
	}
	
	@Test
	public void inserted_money_goes_to_money_in_transaction() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.insertMoney(Cent);
		snackMachine.insertMoney(Dollar);
		
		assertEquals(snackMachine.getMoneyInTransaction(), 1.01f, 0);
	}
	
	@Test(expected = IllegalStateException.class)
	public void cannot_insert_more_than_one_coin_or_note_at_a_time() {
		SnackMachine snackMachine = new SnackMachine();
		Money twoCent = Money.add(Cent, Cent);
		
		snackMachine.insertMoney(twoCent);
	}
	
	@Test
	public void buySnack_trades_inserted_money_for_a_snack() {
		SnackMachine snackMachine = new SnackMachine();
		SnackPile snackPile = new SnackPile(new Snack("Some snack"), 10, 1);
		snackMachine.loadSnacks(1, snackPile);
		snackMachine.insertMoney(Dollar);
		
		snackMachine.buySnack(1);
		
		assertEquals(snackMachine.getMoneyInTransaction(), None);
		assertEquals(snackMachine.getMoneyInside().getAmount(), 1, 0.5);
		SnackPile pileFrom1 = snackMachine.getSnackPile(1);
		
		assertEquals(pileFrom1.getQuantity(), 9);
	}
	
	@Test(expected = IllegalStateException.class)
	public void load_slot_negative_quantity_snackPile() {
		SnackMachine snackMachine = new SnackMachine();
		SnackPile snackPile = new SnackPile(new Snack("Some snack"), -10, 1);
		snackMachine.loadSnacks(1, snackPile);
	}
	
	@Test(expected = IllegalStateException.class)
	public void load_slot_negative_price_snackPile() {
		SnackMachine snackMachine = new SnackMachine();
		SnackPile snackPile = new SnackPile(new Snack("Some snack"), 10, -1);
		snackMachine.loadSnacks(1, snackPile);
	}
	
	@Test(expected = IllegalStateException.class)
	public void cannot_make_purchase_when_there_is_no_snacks() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.buySnack(1);
	}
	
	@Test(expected = IllegalStateException.class)
	public void cannot_make_purchaase_if_not_enough_money_inserted() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.loadSnacks(1, new SnackPile(new Snack("Some Snack"), 1, 2));
		snackMachine.insertMoney(Dollar);
		snackMachine.buySnack(1);
	}
	
	@Test
	public void snack_machine_return_money_with_highest_denomination_first() {
		SnackMachine snackMachine = new SnackMachine();
		snackMachine.loadMoney(Dollar);
		snackMachine.insertMoney(Quarter);
		snackMachine.insertMoney(Quarter);
		snackMachine.insertMoney(Quarter);
		snackMachine.insertMoney(Quarter);
		snackMachine.returnMoney();
		
		assertEquals(snackMachine.getMoneyInside().getQuarterCount(), 4);
		assertEquals(snackMachine.getMoneyInside().getOneDollarCount(), 0);
	}
}
