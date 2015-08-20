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
		return (Inventory.getCount(Items.GRAPES.getId()) == 0 
				|| Inventory.getCount(Items.JUG.getId()) == 0) 
				? State.BANK : State.COMBINE;	
	}
	
	public static void run() {
		switch(getState()) {
		case BANK:
			BankHandler.bank();
			break;
		case COMBINE:
			CombineHandler.combineItems(Items.GRAPES.getId(), Items.GRAPES.getName(),
					Items.JUG.getId(), Items.JUG.getName());
			break;
		}
		
	}
	
}
