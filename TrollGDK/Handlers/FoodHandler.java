package scripts.TrollGDK.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSItem;

import scripts.TrollGDK.Variables;

public class FoodHandler {

	public static boolean needToEat() {
		return ((Player.getRSPlayer().getHealth()/100) * Skills.getActualLevel(SKILLS.HITPOINTS)) <= Variables.eatAtPercentage;
	}
	
	public static boolean hasFood() {
		return Inventory.getCount(Variables.foodID) > 0;
	}
	
	public static boolean eat() {
		final int foodAmount = Inventory.getCount(Variables.foodID);
		RSItem[] food = Inventory.find(Variables.foodID);
		
		if(GameTab.getOpen() != TABS.INVENTORY)
			GameTab.open(TABS.INVENTORY);
		
		if(food.length > 0 && food[0] != null)
			food[0].click();
		return Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20, 30);
				return Inventory.getCount(Variables.foodID) < foodAmount;
			}
		}, General.random(1000, 2000));
	}
}
