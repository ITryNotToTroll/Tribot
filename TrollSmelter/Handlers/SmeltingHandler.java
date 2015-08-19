package scripts.TrollSmelter.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import scripts.TrollSmelter.Variables;

public class SmeltingHandler {

	public static boolean isSmelting() {

		if(!Player.getPosition().equals(Variables.location.getFurnaceTile()))
			return false;

		final int startItems = Inventory.getCount(Variables.item.getBankItems()[0]);

		return Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return Player.getAnimation() != -1 || startItems != Inventory.getCount(Variables.item.getBankItems()[0]);
			}
		}, General.random(7000, 8000));

	}

	public static boolean isInFurnace() {
		return Variables.location.getFurnaceArea().contains(Player.getPosition());
	}

	public static void smelt() {
		
		RSObject[] furnace;
		
		while(Player.isMoving())
			General.sleep(20, 30);
		
		switch(Variables.item.getItemType()) {

		case BAR:

			furnace = Objects.find(10, Variables.FURNACE_NAME);

			if(furnace.length > 0 && furnace[0] != null) {

				furnace[0].click("Smelt Furnace");

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						RSInterface i = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());
						return i != null && !i.isHidden();
					}
				}, General.random(1500, 2500))) {

					RSInterface makeAll = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());

					if(makeAll != null && !makeAll.isHidden()) {
						
						switch(Variables.item.getMakeInt()) {
						
						case 0:
							
							makeAll.click("Smelt X");
							
							if(Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									General.sleep(20, 30);
									RSInterface i = Interfaces.get(Variables.AMOUNT_PARENT, Variables.AMOUNT_CHILD);
									return i != null && !i.isHidden();
								}
							}, General.random(2000, 3000))) {
								
								Keyboard.typeSend(General.random(28, 99) + "");
								
								Timing.waitCondition(new Condition() {
									@Override
									public boolean active() {
										General.sleep(20, 30);
										RSInterface i = Interfaces.get(Variables.AMOUNT_PARENT, Variables.AMOUNT_CHILD);
										return i == null || i.isHidden();
									}
								}, General.random(2000, 3000));
								
							}
							
							break;
							
						case 5:
							
							makeAll.click("Smelt 5");
							
							break;
							
						case 10:
							
							makeAll.click("Smelt 10");
							
							break;
						
						}

					}

				}

			}

			break;

		case CANNONBALL:

			if(Game.getItemSelectionState() == 0) {

				RSItem[] bars = Inventory.find(Variables.ItemID.STEEL_BAR.getID());

				if(bars.length > 0 && bars[0] != null) {

					bars[0].click();

				}

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Game.getItemSelectionState() == 1 && Game.getSelectedItemName().equals("Steel bar");
					}
				}, General.random(500, 1000))) {

					furnace = Objects.find(10, Variables.FURNACE_NAME);

					if(furnace.length > 0 && furnace[0] != null) {

						furnace[0].click("Use Steel bar -> Furnace");

						if(Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(20, 30);
								RSInterface i = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());
								return i != null && !i.isHidden();
							}
						}, General.random(1500, 2500))) {

							RSInterface makeAll = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());

							if(makeAll != null && !makeAll.isHidden()) {
								makeAll.click();
							}

						}

					}

				}

			}

			break;

		case JEWELLERY:

			if(Game.getItemSelectionState() == 0) {

				RSItem[] bars = Inventory.find(Variables.ItemID.GOLD_BAR.getID());

				if(bars.length > 0 && bars[0] != null) {

					bars[0].click();

				}

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Game.getItemSelectionState() == 1 && Game.getSelectedItemName().equals("Gold bar");
					}
				}, General.random(500, 1000))) {

					furnace = Objects.find(10, Variables.FURNACE_NAME);

					if(furnace.length > 0 && furnace[0] != null) {

						furnace[0].click("Use Gold bar -> Furnace");

						if(Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(20, 30);
								RSInterface i = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());
								return i != null && !i.isHidden();
							}
						}, General.random(1500, 2500))) {

							RSInterface makeAll = Interfaces.get(Variables.item.getParentInterface(), Variables.item.getChildInterface());

							if(makeAll != null && !makeAll.isHidden()) {
								
								makeAll.click("Make-X");
								
								if(Timing.waitCondition(new Condition() {
									@Override
									public boolean active() {
										General.sleep(20, 30);
										RSInterface i = Interfaces.get(Variables.AMOUNT_PARENT, Variables.AMOUNT_CHILD);
										return i != null && !i.isHidden();
									}
								}, General.random(2000, 3000))) {
									
									Keyboard.typeSend(General.random(28, 99) + "");
									
									Timing.waitCondition(new Condition() {
										@Override
										public boolean active() {
											General.sleep(20, 30);
											RSInterface i = Interfaces.get(Variables.AMOUNT_PARENT, Variables.AMOUNT_CHILD);
											return i == null || i.isHidden();
										}
									}, General.random(2000, 3000));
									
								}

							}

						}

					}

				}

			}
			break;
		}
		
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return SmeltingHandler.isSmelting();
			}
		}, General.random(3000, 4000));
		
	}
}
