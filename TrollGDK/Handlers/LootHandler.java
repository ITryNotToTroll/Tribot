package scripts.TrollGDK.Handlers;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSItem;

import scripts.TrollGDK.Variables;
import scripts.api.camera.CameraCalculations;
import scripts.api.navigation.Navigator;

public class LootHandler {

	public static boolean isLootAvaliable() {
		return GroundItems.find(new Filter<RSGroundItem>() {
			@Override
			public boolean accept(RSGroundItem item) {
				if(item != null && item.getDefinition() != null  && item.getDefinition().getName() != null) {
					for(int i=0;i<Variables.lootItems.size();i++) {
						if(item.getDefinition().getName().equals(Variables.lootItems.get(i)))
								return true;
					}
				}
				return false;
			}
		}).length > 0;
	}
	
	public static boolean loot() {
		RSGroundItem[] groundItems = GroundItems.findNearest(new Filter<RSGroundItem>() {
			@Override
			public boolean accept(RSGroundItem item) {
				if(item != null && item.getDefinition() != null  && item.getDefinition().getName() != null) {
					for(int i=0;i<Variables.lootItems.size();i++) {
						if(item.getDefinition().getName().equals(Variables.lootItems.get(i)))
								return true;
					}
				}
				return false;
			}
		});
		
		if(groundItems.length > 0 && groundItems[0] != null) {
			 try {
	                if (Projection.isInViewport(groundItems[0].getModel().getCentrePoint())) {
	                	final int itemCount = Inventory.getCount(groundItems[0].getID());
	                	final int itemID = groundItems[0].getID();
	                    if (Clicking.click("Take", groundItems[0])) {
	                        return Timing.waitCondition(new Condition() {
	                        	@Override
	                        	public boolean active() {
	                        		General.sleep(20, 30);
	                        		return itemCount < Inventory.getCount(itemID);
	                        	}
	                        }, General.random(3000, 5000));
	                    }
	                } else {
	                    Camera.turnToTile(groundItems[0]);
	                    Camera.setCameraAngle(CameraCalculations.getAngleFor(groundItems[0].getPosition()));
	                    if (Projection.isInViewport(groundItems[0].getModel().getCentrePoint())) {
	                        return loot();
	                    } else {
	                        Navigator.walkNearby(groundItems[0].getPosition());
	                        return loot();
	                    }
	                }
	            } catch (Exception e){}
		}
		return false;
	}
}
