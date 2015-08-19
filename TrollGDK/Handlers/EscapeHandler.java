package scripts.TrollGDK.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSPlayer;
import org.tribot.api2007.types.RSTile;

import scripts.TrollGDK.Variables;
import scripts.TrollGDK.Variables.Preset;

public class EscapeHandler {

	public static boolean needToEscape() {
		RSPlayer[] players = Players.getAll();
		RSCharacter[] attackers = Combat.getAttackingEntities();
		for(RSPlayer player:players) {
			for(RSCharacter attacker:attackers) {
				if(player.getName().equals(attacker.getName()))
					return true;
			}
		}
		return false;
	}

	public static boolean escape(Preset p) {

		switch(p) {
		case GREEN_DRAGONS:

			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					Walking.setControlClick(true);
					Walking.walkTo(new RSTile(Player.getPosition().getX(), Player.getPosition().getY() - General.random(4, 7), 0));
					if(FoodHandler.needToEat())
						FoodHandler.eat();
					Walking.setControlClick(false);
					return Integer.parseInt(Interfaces.get(381, 1).getText().substring(7)) <= 30;
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
											"Amulet of glory (");
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
				RSItem[] glory = Equipment.find(new Filter<RSItem>() {
					@Override
					public boolean accept(
							RSItem item) {
						return item != null
								&& item.getDefinition() != null
								&& item.getDefinition()
								.getName()
								.contains(
										"Amulet of glory (");
					}
				});
				if(glory.length > 0 && glory[0] != null) {
					final RSTile playerPos = Player.getPosition();
					glory[0].click("Edgeville");
					if(Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Variables.gloryTeleportArea.contains(Player.getPosition());
						}
					},General.random(1000, 1500))) {
						BankHandler.bank();
						return true;
					}
				}
			}

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
					if(Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Variables.castleWarsArea.contains(Player.getPosition());
						}
					},General.random(1000, 1500))) {
						BankHandler.bank();
						return true;
					}
				}
			}
			
			break;
			
		case TAVERLY_DRUIDS:
			
			if(Inventory.getCount("Falador teleport") > 0) {
				RSItem[] tab = Inventory.find("Falador teleport");
				if(tab.length > 0 && tab[0] != null) {
					final int tabAmount = Inventory.getCount("Falador teleport");
					tab[0].click();
					Timing.waitCondition(new Condition() {
						@Override 
						public boolean active() {
							General.sleep(20, 30);
							return tabAmount > Inventory.getCount("Falador teleport");
						}
					},General.random(1000, 1500));
				}
			}
			
		}
		return false;
	}

}