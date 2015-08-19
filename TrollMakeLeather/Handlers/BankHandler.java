package scripts.TrollMakeLeather.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.TrollMakeLeather.Variables;

public class BankHandler {

	public static void bank() {

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return Banking.isBankScreenOpen();
			}
		}, General.random(1000, 1500))) {

			Banking.depositAllExcept(Variables.itemsNotDeposited[0], Variables.itemsNotDeposited[1], Variables.leather.getID());

			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return Inventory.getAll().length <= 3;
				}
			}, General.random(1000, 1500))) {


				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Banking.getAll().length > 0;
					}
				}, General.random(2000, 300));

				RSItem[] leather = Banking.find(Variables.leather.getID());

				if(leather.length > 0 && leather[0] != null) {
					if(leather[0].getStack() <= 5) 
						Variables.running = false;
				} else
					Variables.running = false;

				General.println("3");
				Banking.withdraw(0, Variables.leather.getID());

				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Inventory.isFull();
					}
				}, General.random(1000, 1500));

			}

		}
	}

}
