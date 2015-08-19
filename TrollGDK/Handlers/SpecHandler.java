package scripts.TrollGDK.Handlers;


import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.TrollGDK.Variables;

public class SpecHandler {

	public static boolean canSpec() {
		return Variables.useSpec && getSpecPercentage() >= Variables.minSpecPercentage;
	}
	
	private static int getSpecPercentage() {
		return Game.getSettingsArray()[300] / 10;
	}

	public static boolean switchWeapon() {
		RSItem[] weapon = Inventory.find(Variables.specWeaponID);
		if(weapon.length > 0 && weapon[0] != null) {
			final int itemCount = Inventory.getCount(Variables.specWeaponID);
			weapon[0].click();
			return Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return itemCount < Inventory.getCount(Variables.specWeaponID);
				}
			}, General.random(1000, 2000));
		}
		return false;
	}
	
	public static boolean isSpecEnabled() {
		return Game.getSetting(301) == 1;
	}
	
	public static boolean useSpec() {
		GameTab.open(TABS.COMBAT);
		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return GameTab.getOpen() == TABS.COMBAT;
			}
		}, General.random(1000, 1500))) {
			Mouse.clickBox(580, 419, 705, 424, 0);
			return Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(20, 30);
					return isSpecEnabled();
				}
			}, General.random(1500, 2000));
		}
		return false;
	}
}
