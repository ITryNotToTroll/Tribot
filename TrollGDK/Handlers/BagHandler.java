package scripts.TrollGDK.Handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItem;

public class BagHandler {

	private static List<Integer> bagItems = new ArrayList<Integer>();

	private static int[] lootingItems = new int[] {0, 1, 2};

	public static boolean isFull = false;

	private static int masterBagInterface = 81,
			closeBagInterface = 2,
			bagItemsInterface = 7,
			bagIsEmptyInterface = 28;

	public static boolean isBagFull() {
		return bagItems.size() >= 28;
	}

	public static boolean isBagEmpty() {
		return bagItems.size() == 0;
	}

	//	public static void withdrawAll() {
	//
	//		RSItem[] bag = Inventory.find("Looting bag");
	//
	//		if(isBagOpen()) {
	//
	//			if(bag.length > 0 && bag[0] != null) {
	//
	//				bag[0].click("Withdraw Looting bag");
	//
	//			}
	//
	//		}
	//		
	//		if(Timing.waitCondition(new Condition() {
	//			@Override
	//			public boolean active() {
	//				General.sleep(20);
	//				return isBagOpen();
	//
	//			}
	//		}, General.random(1000,  2000))) {
	//			
	//			
	//			
	//		}
	//		
	//		
	//	}

	public static boolean isBagOpen() {
		RSInterface bag = Interfaces.get(masterBagInterface, closeBagInterface);
		return bag != null && !bag.isHidden();
	}

	public static void addToBag() {

		RSItem[] bag = Inventory.find("Looting bag");

		if(!isBagOpen()) {

			if(bag.length > 0 && bag[0] != null) {

				bag[0].click("Deposit Looting bag");

			}

		}


		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20);
				return isBagOpen();

			}
		}, General.random(1000,  2000))) {



			while(bagItems.size() < 28 && getItems().length > 0) {
				int[] items = getItems();

				for(int i : items) {
					for(int j : lootingItems) {

						if(i == j) {

							storeItem(i);

							if(Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									General.sleep(20, 30);
									return items.length != getItems().length;

								}
							}, General.random(1000, 2000))) {

								int[] checkItems = getItems();

								for(int k = 0;k< items.length - checkItems.length;k++) {

									bagItems.add(i);

								}

							}

							break;

						}

					}

					if(isBagFull())
						break;
				}

			}

		}



	}

	public static void storeItem(int itemID) {

		RSInterfaceChild bag = Interfaces.get(81, 7);
		if (bag != null) {
			RSInterfaceComponent[] depositItems = bag.getChildren();
			for (int i = 0; i < depositItems.length; i++) {
				if(depositItems[i] != null) {
					if (depositItems[i].getComponentItem() == itemID) {
						depositItems[i].click("Store-All");
						return;
					}
				}
			}
		}

	}

	public static void bankItem(int itemID) {

		RSItem[] bag = Inventory.find("Looting bag");

		if(!isBagOpen()) {

			if(bag.length > 0 && bag[0] != null) {

				bag[0].click("Withdraw Looting bag");

			}

		}
		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20);
				return isBagOpen();

			}
		}, General.random(1000,  2000))) {
			RSInterfaceChild bagInterface = Interfaces.get(81, 7);
			if (bag != null) {
				RSInterfaceComponent[] depositItems = bagInterface.getChildren();
				for (int i = 0; i < depositItems.length; i++) {
					if(depositItems[i] != null) {
						if (depositItems[i].getComponentItem() == itemID) {
							depositItems[i].click("Deposit-All");
							return;
						}
					}
				}
			}
		}
	}

	public static void bankAll() {

		RSItem[] bag = Inventory.find("Looting bag");

		if(!isBagOpen()) {

			if(bag.length > 0 && bag[0] != null) {

				bag[0].click("Withdraw Looting bag");

			}

		}

		if(Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(20);
				return isBagOpen();

			}
		}, General.random(1000,  2000))) {

			RSInterfaceChild bagInterface = Interfaces.get(81, 7);
			if (bag != null) {
				RSInterfaceComponent[] depositItems = bagInterface.getChildren();

				while(depositItems.length > 0) {
					depositItems = bagInterface.getChildren();
					for (int i = 0; i < depositItems.length; i++) {
						if(depositItems[i] != null) {
							depositItems[i].click("Deposit-All");
						}
					}
				}
			}
		}
		
		while(bagItems.size() > 0)
			bagItems.remove(0);
	}

	public static int[] getItems() {

		ArrayList<Integer> items = new ArrayList<Integer>();

		RSInterface bag = Interfaces.get(masterBagInterface, 7);

		if(bag != null && !bag.isHidden()) {

			RSInterface[] bagChildren = bag.getChildren();

			for(RSInterface i : bagChildren) {

				if(i != null) {

					int id = i.getComponentItem();

					if(id != 0) 
						items.add(id);

				}

			}

		}

		int[] returnInt = new int[items.size()];
		Iterator<Integer> iterator = items.iterator();
		for (int i = 0; i < returnInt.length; i++)
		{
			returnInt[i] = iterator.next().intValue();
		}

		return returnInt;

	}

	public static void setItems() {
		RSInterface bag = Interfaces.get(masterBagInterface, 7);

		if(bag != null && !bag.isHidden()) {

			RSInterface bagChild = bag.getChild(28);

			if(bagChild != null) {

				String s = bagChild.getText();

				if(s != null) {

					if(s.contains("The bag is empty")) {

						while(bagItems.size() > 0)
							bagItems.remove(0);


						isFull = false;
						return;

					}
				}
			}



		}
	}

}
