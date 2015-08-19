package scripts.TrollGDK.Handlers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.TrollGDK.Variables;

public class EquipmentHandler {

	public static boolean isWeaponEquipped() {
		return Equipment.isEquipped(Variables.mainWeaponID);
	}

	public static boolean isItemsToEquip() {
		
		for(int j = 0;j<Variables.equipment.size();j++) {
			if(Inventory.getCount(Variables.equipment.get(j)) > 0  && !Equipment.isEquipped(Variables.equipment.get(j))) {
				return true;
			}
		}
		return false;

	}
	
	public static void equipItems() {
		for(int j = 0;j<Variables.equipment.size();j++) {
			if(Inventory.getCount(Variables.equipment.get(j)) > 0  && !Equipment.isEquipped(Variables.equipment.get(j))) {
				RSItem[] item = Inventory.find(Variables.equipment.get(j));
				if(item.length > 0 && item[0] != null) {
					final int itemID = Variables.equipment.get(j);
					item[0].click();
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Equipment.isEquipped(itemID);
						}
					}, General.random(1000, 2000));
				}
			}
		}
	}
	
}
