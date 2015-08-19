package scripts.api.looting;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.util.DPathNavigator;

import scripts.api.navigation.Navigator;

public class Looting {

	public static boolean pickUp(int[] ids){
		if (ids != null) {
			RSGroundItem groundItem = getClosest(ids);
			if (groundItem != null) {
				if (groundItem.isOnScreen()) {
					final int invy = Inventory.getCount(ids);
					if (Clicking.click("Take " + groundItem.getDefinition().getName(), groundItem)) {
						waiting(groundItem);
						return Inventory.getCount(ids) > invy;
					}
				} else {
					Navigator.walkNearby(groundItem.getPosition());
					pickUp(ids);
				}
			}
		}
		return false;
	}

	public static boolean pickUp(RSGroundItem groundItem){
		if (groundItem != null) {
			if (groundItem.isOnScreen()) {
				final int invy = Inventory.getCount(groundItem.getID());
				if (Clicking.click("Take " + groundItem.getDefinition().getName(), groundItem)) {
					waiting(groundItem);
					return Inventory.getCount(groundItem.getID()) > invy;
				}
			} else {
				Navigator.walkNearby(groundItem.getPosition());
				pickUp(groundItem);
			}
		}
		return false;
	}

	public static boolean pickUp(int[] ids, RSArea area){
		if (ids != null) {
			RSGroundItem groundItem = getClosest(ids, area);
			if (groundItem != null) {
				if (groundItem.isOnScreen()) {
					final int invy = Inventory.getCount(ids);
					if (Clicking.click("Take " + groundItem.getDefinition().getName(), groundItem)) {
						waiting(groundItem);
						return Inventory.getCount(ids) > invy;
					}
				} else {
					Navigator.walkNearby(groundItem.getPosition());
					pickUp(ids);
				}
			}
		}
		return false;
	}

	private static boolean waiting(final RSGroundItem loot){

		final int amt = GroundItems.find(new Filter<RSGroundItem>() {
			@Override
			public boolean accept(RSGroundItem groundItem) {
				return groundItem.getPosition().equals(loot.getPosition()) && loot.getID() == groundItem.getID();
			}
		}).length;

		if (amt == 0){ return false; }
		return Timing.waitCondition( new Condition() {
			@Override
			public boolean active() {
				RSGroundItem[] groundItems = GroundItems.find(new Filter<RSGroundItem>() {
					@Override
					public boolean accept(RSGroundItem groundItem) {
						return groundItem.getPosition().equals(loot.getPosition()) && loot.getID() == groundItem.getID();
					}
				});
				return amt > groundItems.length;
			}
		},5000);

	}

	public static RSGroundItem getClosest(int[] ids){
		RSGroundItem[] groundItems = GroundItems.find(ids);
		int dist = 9999;
		RSGroundItem closest = null;
		DPathNavigator dPathNavigator = new DPathNavigator();
		for (RSGroundItem x : groundItems){
			int i;
			if (x != null && (i = dPathNavigator.findPath(x).length) < dist){
				dist = i;
				closest = x;
			}
		}
		return closest;
	}

	public static RSGroundItem getClosest(int[] ids, RSArea area){
		RSGroundItem[] groundItems = GroundItems.find(ids);
		int dist = 9999;
		RSGroundItem closest = null;
		DPathNavigator dPathNavigator = new DPathNavigator();
		for (RSGroundItem x : groundItems){
			int i;
			if (x != null && (i = dPathNavigator.findPath(x).length) < dist && area.contains(x)){
				dist = i;
				closest = x;
			}
		}
		return closest;
	}

}
