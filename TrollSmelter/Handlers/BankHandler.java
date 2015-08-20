package scripts.TrollSmelter.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;

import scripts.TrollSmelter.Variables;

public class BankHandler {

	public static boolean needToBank() {

		for(int id : Variables.item.getReqItems()) {
			if(Inventory.getCount(id) == 0)
				return true;
		}

		for(int id : Variables.item.getBankItems()) {
			if(Inventory.getCount(id) == 0)
				return true;
		}

		if(Variables.item.getBankItems().length == 2) {
			if(Inventory.getCount(Variables.item.getBankItems()[1]) < Variables.item.getMinMaterials())
				return true;
		}
		return false;
	}

	public static boolean isInBank() {
		return Variables.location.getBankArea().contains(Player.getPosition());
	}

	public static void bank() {

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(200, 250);
				return Banking.isBankScreenOpen();
			}
		}, General.random(1500, 2000))) {

			if(Variables.item.getReqItems().length > 0)
				Banking.depositAllExcept(Variables.item.getReqItems());
			else
				Banking.depositAll();


			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200, 250);
					return Inventory.getAll().length == Inventory.getCount(Variables.item.getReqItems()) && Banking.getAll().length > 0;
				}
			}, General.random(1500, 2000))) {

				if(Variables.item.getReqItems().length > 0) {
					if(Inventory.getCount(Variables.item.getReqItems()) != 1) {
						Banking.depositAll();
						if(Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(200, 250);
								return Inventory.getAll().length == 0;
							}
						}, General.random(1500, 2000))) {

							Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									General.sleep(200, 250);
									return Banking.getAll().length > 0;
								}
							}, General.random(2000, 300));

							RSItem[] one = Banking.find(Variables.item.getReqItems()[0]);

							if(one.length == 0)
								Variables.running = false;

							Banking.withdraw(1, Variables.item.getReqItems()[0]);

							Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									General.sleep(200, 250);
									return Inventory.getAll().length == Inventory.getCount(Variables.item.getReqItems());
								}
							}, General.random(1500, 2000));

						}

					}
				}

				RSItem[] one = Banking.find(Variables.item.getBankItems()[0]);

				if(one.length == 0)
					Variables.running = false;

				Banking.withdraw(Variables.item.getBankItemsAmount()[0], Variables.item.getBankItems()[0]);

				if(Variables.item.getBankItems().length == 2) {
					RSItem[] two = Banking.find(Variables.item.getBankItems()[1]);
					if(two.length == 0)
						Variables.running = false;

					Banking.withdraw(0, Variables.item.getBankItems()[1]);
				}

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(200, 250);
						return Inventory.getAll().length == Inventory.getCount(Variables.item.getReqItems()) + Inventory.getCount(Variables.item.getBankItems());
					}
				}, General.random(1500, 2000))) {

					if(Banking.close()) {

						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(200, 250);
								return !Banking.isBankScreenOpen();
							}
						}, General.random(1500, 2000));
					}
				}

			}

		}

	}

}
