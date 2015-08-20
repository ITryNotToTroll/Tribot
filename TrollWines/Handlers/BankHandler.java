package scripts.TrollWines.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.TrollWines.Items;
import scripts.TrollWines.Variables;

public class BankHandler {

	public static boolean needToBank() {
		
		return Inventory.getCount(Items.GRAPES.getId()) == 0 || Inventory.getCount(Items.JUG.getId()) == 0;
	}
	
	public static void bank() {
		
		if(!Banking.isBankScreenOpen())
			Banking.openBank();

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return Banking.isBankScreenOpen();
			}
		}, General.random(2000, 3000))) {
			
			if(Inventory.getAll().length != 0)
				Banking.depositAll();
			
			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return Inventory.getAll().length == 0;
				}
			}, General.random(2000, 3000))) {
				
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Banking.getAll().length > 0;
					}
				}, General.random(2000, 300));
				
				RSItem[] one = Banking.find(Items.JUG.getId());
				RSItem[] two = Banking.find(Items.GRAPES.getId());
				
				if(one.length == 0 || two.length == 0)
					Variables.running = false;
				
				Banking.withdraw(14, Items.JUG.getId());
					
				Banking.withdraw(14, Items.GRAPES.getId());
				
				
				
				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Inventory.isFull();
					}
				}, General.random(2000, 3000))) {
					
					Banking.close();
					
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return !Banking.isBankScreenOpen();
						}
					}, General.random(2000, 3000));
					
				}
				
			}
			
		}
		
	}
	
}
