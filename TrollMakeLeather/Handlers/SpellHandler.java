package scripts.TrollMakeLeather.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Magic;
import org.tribot.api2007.types.RSItem;

import scripts.TrollMakeLeather.Variables;

public class SpellHandler {

	public static void useSpell() {

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return !Banking.isBankScreenOpen();
			}
		}, General.random(1000, 1500))) {

			if(Inventory.getCount(Variables.itemsNotDeposited[0]) < 2
					|| Inventory.getCount(Variables.itemsNotDeposited[1]) == 0)
				Variables.running = false;

			if(GameTab.getOpen() != TABS.MAGIC)
				GameTab.open(TABS.MAGIC);

			if(Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return GameTab.getOpen() == TABS.MAGIC;
				}
			}, General.random(1000, 1500))) {

				Magic.selectSpell("Tan Leather");

				//General.println("Click ");

				int start = Inventory.getCount(Variables.leather.getID());

				//General.println("Before " + start);

				if(Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
					
						Variables.abc.performTimedActions(SKILLS.MAGIC);

						return start > Inventory.getCount(Variables.leather.getID());
					}
				}, General.random(2500, 3000))) {

					Variables.itemsMade += 5;

				}

				//General.println("After " + Inventory.getCount(Variables.leather.getID()));

			}

		}

	}


}
