package scripts.TrollMakeLeather;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.TrollMakeLeather.Handlers.BankHandler;
import scripts.TrollMakeLeather.Handlers.SpellHandler;

public class MakeLeather {

	private enum State {
		BANK,
		MAKE_LEATHER;
	}
	
	private static State getState() {
		return Inventory.getCount(Variables.leather.getID()) >= 5 ? State.MAKE_LEATHER : State.BANK;
	}
	
	public static void run() {
		
		switch(getState()) {
		
		case BANK:
			
			if(!Banking.isBankScreenOpen())
				Banking.openBank();
			
			BankHandler.bank();
			return;
		
		case MAKE_LEATHER:
			
			if(Banking.isBankScreenOpen())
				Banking.close();
			
			SpellHandler.useSpell();
			
			return;
		}
		
	}
	
}
