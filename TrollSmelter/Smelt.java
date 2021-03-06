package scripts.TrollSmelter;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.Skills.SKILLS;

import scripts.TrollSmelter.Handlers.BankHandler;
import scripts.TrollSmelter.Handlers.SmeltingHandler;
import scripts.api.navigation.Navigator;

public class Smelt {

	enum State {
		BANK,
		WALK_TO_FURNACE,
		WALK_TO_BANK,
		SMELT,
		SLEEP;
		
	}
	
	private static State getState() {
		
		if(BankHandler.needToBank()) 
			return BankHandler.isInBank() ? State.BANK : State.WALK_TO_BANK;
		
		if(SmeltingHandler.isSmelting())
			return State.SLEEP;
		
		return SmeltingHandler.isInFurnace() ? State.SMELT : State.WALK_TO_FURNACE;
		
	}
	
	public static void run() {
		
		switch(getState()) {
		case SLEEP:
			
			Variables.abc.performTimedActions(SKILLS.MAGIC);
			
			break;
		case BANK:
			
			if(!Banking.isBankScreenOpen()) {
				if(Banking.openBank()) {
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(200, 250);
							return Banking.isBankScreenOpen();
						}
					}, General.random(1500, 2000));
				}
			}
			
			BankHandler.bank();
			
			break;
			
		case WALK_TO_FURNACE:
			
			Navigator.walkNearby(Variables.location.getFurnaceTile());
			
			break;
			
		case WALK_TO_BANK:
			
			Navigator.walkNearby(Variables.location.getBankTile());
		
			break;
			
		case SMELT:
			
			SmeltingHandler.smelt();
			
			break;
			
		}
		
	}
	
}
