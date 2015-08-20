package scripts.TrollWines.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;

import scripts.TrollWines.Variables;



public class CombineHandler {

	public static void combineItems(int itemOne, String itemOneName, int itemTwo, String itemTwoName) {

		if(Banking.isBankScreenOpen()) {
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

		RSItem[] item1 = Inventory.find(itemOne);
		RSItem[] item2 = Inventory.find(itemTwo);

		if(item1.length > 0 && item1[item1.length - 1] != null && item2.length > 0 && item2[0] != null) {

			if(item1[item1.length - 1].click("Use " + itemOneName)) {

				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(10, 20);
						return Game.getItemSelectionState() == 1 && Game.getSelectedItemName().contains(itemOneName);
					}
				}, General.random(2000, 2500));
				General.sleep(Variables.abc.DELAY_TRACKER.ITEM_INTERACTION.next());
			}

			if(item2[0].click("Use " + itemOneName + " -> " + itemTwoName)) {

				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(200, 250);
						RSInterface inter = Interfaces.get(309, 6);
						return inter != null && !inter.isHidden();
					}
				}, General.random(2000,  3000));
			}

			RSInterface inter = Interfaces.get(309, 6);

			if(inter != null && !inter.isHidden()) {

				if(inter.click("Make All")) {

					final int CURR_LEVEL = Skills.getActualLevel(SKILLS.COOKING);

					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(200, 250);

							Variables.abc.performTimedActions(SKILLS.MAGIC);

							return Inventory.getCount(itemOne) == 0 
									|| Inventory.getCount(itemTwo) == 0
									|| Skills.getActualLevel(SKILLS.COOKING) != CURR_LEVEL;
						}
					}, General.random(20000,  25000));

				}
			}

		}

	}

}

