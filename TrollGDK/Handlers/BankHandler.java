package scripts.TrollGDK.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;

import scripts.TrollGDK.Variables;
import scripts.api.interraction.Interaction;
import scripts.api.navigation.Navigator;

public class BankHandler {

	public static boolean needToBank() {
		return FoodHandler.needToEat() && !FoodHandler.hasFood();
	}

	public static boolean walkToBank() {
		
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				Walking.setControlClick(true);
				Walking.walkTo(new RSTile(Player.getPosition().getX(), Player.getPosition().getY() - General.random(4, 7), 0));
				if(FoodHandler.needToEat())
					FoodHandler.eat();
				Walking.setControlClick(false);
				return Integer.parseInt(Interfaces.get(381, 1).getText().substring(7)) <= 20;
			}
		}, General.random(5000, 6000));

		if (Interfaces.get(381, 1) != null
				&& Integer.parseInt(Interfaces.get(381, 1).getText().substring(7)) <= 30
				&& Equipment.isEquipped(new Filter<RSItem>() {
					@Override
					public boolean accept(
							RSItem item) {
						return item != null
								&& item.getDefinition() != null
								&& item.getDefinition()
								.getName()
								.contains(
										"Ring of dueling(");
					}
				})) {
			GameTab.open(TABS.EQUIPMENT);
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return GameTab.getOpen() == TABS.EQUIPMENT;
				}

			},General.random(1000, 1500));
			RSItem[] ring = Equipment.find(new Filter<RSItem>() {
				@Override
				public boolean accept(
						RSItem item) {
					return item != null
							&& item.getDefinition() != null
							&& item.getDefinition()
							.getName()
							.contains(
									"Ring of dueling(");
				}
			});
			if(ring.length > 0 && ring[0] != null) {
				final RSTile playerPos = Player.getPosition();
				ring[0].click("Castle wars");
				return Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Variables.castleWarsArea.contains(Player.getPosition());
					}
				},General.random(1000, 1500));
			}
		}
		
		return false;
	}

	public static void bank() {
		Banking.openBank();
		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return Banking.isBankScreenOpen();
			}
		}, General.random(2000, 3000))) {
			BagHandler.bankAll();
			Banking.depositAllExcept(Variables.specWeaponID, Variables.lootingBagID, Variables.mainWeaponID);
			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return Inventory.getAll().length <= 2;
				}
			}, General.random(1000, 2000))) {

				if(!FoodHandler.needToEat() || !Equipment.isEquipped(2552, 2554, 2556, 2558, 2570, 2562, 2564, 2566) || !Equipment.isEquipped(ids)) {
					Banking.close();
					if(Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return !Banking.isBankScreenOpen();
						}
					}, General.random(2000, 3000))) {
						
						
						
						Banking.openBank();
						
						
					}
				}

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Banking.isBankScreenOpen();
					}
				}, General.random(2000, 3000))) {
					
					for(int j = 0;j<Variables.equipment.size();j++) {
						if(Inventory.getCount(Variables.equipment.get(j)) == 0  && !Equipment.isEquipped(Variables.equipment.get(j))) {
							final int equipmentID = Variables.equipment.get(j);
							final int itemAmount = Inventory.getCount(Variables.equipment.get(j));
							Banking.withdraw(1, Variables.equipment.get(j));
							Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									General.sleep(20, 30);
									return Inventory.getCount(equipmentID) > itemAmount;
								}
							}, General.random(1000, 2000));
						}
					}

					for(int i = 0;i<Variables.pots.size();i++) {
						final int itemID = Variables.pots.get(i);
						final int itemAmount = Inventory.getCount(Variables.pots.get(i));
						Banking.withdraw(1, Variables.pots.get(i));
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(20, 30);
								return Inventory.getCount(itemID) > itemAmount;
							}
						}, General.random(1000, 2000));
					}
					
					final int foodAmount = Inventory.getCount(Variables.foodID);
					Banking.withdraw(Variables.foodAmount, Variables.foodID);
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Inventory.getCount(Variables.foodID) > foodAmount;
						}
					}, General.random(1000, 2000));
				}
			}
		}
		

	}
}
