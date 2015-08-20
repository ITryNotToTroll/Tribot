package scripts.TrollMakeLeather.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.TrollMakeLeather.Constants;
import scripts.TrollMakeLeather.Variables;

public class BankHandler {

	public static void bank() {

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(200, 250);
				return Banking.isBankScreenOpen();
			}
		}, General.random(1000, 1500))) {
			
			Banking.depositAllExcept(Constants.RUNES[0], Constants.RUNES[1], Variables.leather.getID());

			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200, 250);
					return Inventory.getAll().length <= 3 && Banking.getAll().length > 0;
				}
			}, General.random(2000, 2500))) {

				RSItem[] leather = Banking.find(Variables.leather.getID());

				Variables.running = leather.length > 0 && leather[0].getStack() > 5;

				if(Banking.withdraw(0, Variables.leather.getID())) {

					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(200, 250);
							return Inventory.isFull();
						}
					}, General.random(1000, 1500));
				}
			}
		}
	}

}
