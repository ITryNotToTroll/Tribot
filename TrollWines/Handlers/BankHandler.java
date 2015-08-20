package scripts.TrollWines.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.TrollWines.Items;
import scripts.TrollWines.Variables;

public class BankHandler {

	public static boolean needToBank() {
		return Inventory.getCount(Items.GRAPES.getId()) == 0 || Inventory.getCount(Items.JUG.getId()) == 0;
	}

	public static void bank() {

		if(!Banking.isBankScreenOpen()) {
			if(Banking.openBank()) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(200, 250);
						return Banking.isBankScreenOpen();
					}
				}, General.random(2000, 3000)); 
			}
		}

		if(Banking.depositAll() > 0) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200, 250);
					return Inventory.getAll().length == 0;
				}
			}, General.random(2000, 3000));
		}

		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(200, 250);
				return Banking.getAll().length > 0;
			}
		}, General.random(2000, 300));

		if(Banking.withdraw(14, Items.JUG.getId()) &&  Banking.withdraw(14, Items.GRAPES.getId())) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200, 250);
					return Inventory.isFull();
				}
			}, General.random(2000, 3000));
		} else Variables.running = false;

		if(Banking.close()) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200, 250);
					return !Banking.isBankScreenOpen();
				}
			}, General.random(2000, 3000));
		}

	}
}
