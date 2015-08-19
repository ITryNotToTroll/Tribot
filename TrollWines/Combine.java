package scripts.TrollWines;

import org.tribot.api2007.Inventory;

import scripts.TrollWines.Handlers.BankHandler;
import scripts.TrollWines.Handlers.CombineHandler;



public class Combine {

	private static enum State{
		BANK,
		COMBINE;
	}
	
	private static State getState() {
		return (Inventory.getCount(Variables.itemOne) == 0 
				|| Inventory.getCount(Variables.itemTwo) == 0) 
				? State.BANK : State.COMBINE;	
	}
	
	public static void run() {
		switch(getState()) {
		case BANK:
			BankHandler.bank();
			break;
		case COMBINE:
			CombineHandler.combineItems(Variables.itemOne, Variables.itemOneName,
					Variables.itemTwo, Variables.itemTwoName);
			break;
		}
		
	}
	
}
