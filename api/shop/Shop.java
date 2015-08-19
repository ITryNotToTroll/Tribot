package scripts.api.shop;

import java.awt.Point;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

public class Shop {

	public static int getSlot(int itemId) {
		if (isShopOpen()) {
			RSItem[] items = getItems();
			for (int i = 0; i < getStockLength(); i++) {
				if (items[i] != null && itemId == items[i].getID())
					return i;
			}
		}
		return -1;
	}

	public static boolean contains(int id) {
		if (isShopOpen()) {
			for (RSItem r : Interfaces.get(300, 75).getItems()) {
				if (r.getID() == id){
					return r.getStack() > 0;
				}
			}
		}
		return false;
	}
	
	public static int getCount(int id) {
		if (isShopOpen()) {
			for (RSItem r : Interfaces.get(300, 75).getItems()) {
				if (r.getID() == id){
					General.println(r.getStack());
					return r.getStack();
				}
			}
		}
		return 0;
	}
	
	static public void buy(int id) {
		int index = -1;
		if (Shop.isShopOpen()) {
			for (int i = 0; i < Shop.getItems().length; i++) {
				if (Shop.getItems()[i].getID() == id) {
					index = i;
				}
			}
			if (index == -1)
				return;
			int itemX = (int) Math.ceil((index) % 8);
			int itemY = (int) ((Math.floor(index) / 8) % 5);
			int y = (itemY * 45) + 82;
			int x = (itemX * 46) + 97;

			// from here it's terrible, but the main part is up above
			Mouse.move(new Point(x, y));
			General.sleep(200);
			Mouse.click(3);
			if (ChooseOption.isOpen() && ChooseOption.isOptionValid("Buy 10")) {
				ChooseOption.select("Buy 10");
			}
		}
	}

	public static boolean isShopOpen() {
		return Interfaces.get(300, 75) != null;
	}

	public String getShopName() {
		if (Interfaces.get(300, 76) != null)
			return Interfaces.get(300, 76).getText();

		return null;
	}

	public static boolean close() {
		if (Interfaces.get(300, 91) != null) {
			if (Interfaces.get(300, 91).click("Close")) {
				General.sleep(600);
				return true;
			}
		}
		return false;
	}

	static int getStockLength() {
		if (isShopOpen())
			return Interfaces.get(300, 75).getItems().length;

		return 0;
	}

	public static boolean sell(int id, int count) {
		if (isShopOpen()) {
			RSItem[] item = Inventory.find(id);
			if (item.length > 0) {
				if (count == 0 || count > 10) {
					if (item[0].click("Sell 10")) {
					General.sleep(200);
						return true;
					}
				} else {
					if (item[0].click("Sell " + count)) {
						General.sleep(200);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static RSItem[] getItems() {
		if (Interfaces.get(300, 75) != null)
			return Interfaces.get(300, 75).getItems();

		return null;
	}

	
}