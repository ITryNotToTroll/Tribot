package scripts.TrollSmelter;

import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.api.abc.ABC;

public class Variables {

	public static boolean running = true;
	
	public static ABCUtil abc = new ABCUtil();

	public static final int AMOUNT_PARENT= 162,
			AMOUNT_CHILD = 32;

	public static final String FURNACE_NAME = "Furnace";
	
	public static int itemsMade = 0, profitPerItem = 0, startXP = 0;
	
	public static Item item;
	
	public static Location location;
	
	public static long startTime = 0;
	
	public enum ItemType {
		BAR,
		CANNONBALL,
		JEWELLERY
	}
	
	public enum Location {
		PORT_PHASMATYS(new RSTile(3687, 3479, 0), new RSTile(3685, 3479, 0), 
				new RSArea(
						new RSTile(3688, 3476, 0),
						new RSTile(3682, 3482, 0)
				), 
				new RSArea(
						new RSTile(3692, 3465, 0),
						new RSTile(3685, 3472, 0)
				)
		),
		AL_KHARID(new RSTile(3274, 3186, 0), new RSTile(3269, 3169, 0), 
				new RSArea(
						new RSTile(3271, 3189, 0),
						new RSTile(3280, 3183, 0)
				), 
				new RSArea(
						new RSTile(3168, 3174, 0),
						new RSTile(3273, 3160, 0)
				)
		),
		EDGEVILLE(new RSTile(3109, 3499, 0), new RSTile(3096, 3494, 0), 
				new RSArea(
						new RSTile(3111, 3502, 0),
						new RSTile(3104, 3495, 0)
				), 
				new RSArea(
						new RSTile(3099, 3500, 0),
						new RSTile(3090, 3487, 0)
				)
		),
		FALADOR(new RSTile(2975, 3369, 0), new RSTile(2946, 3368, 0), 
				new RSArea(
						new RSTile(2976, 3374, 0),
						new RSTile(2969, 3367, 0)
				), 
				new RSArea(
						new RSTile(2950, 3367, 0),
						new RSTile(2942, 3374, 0)
				)
		);
		
		
		public RSTile getBankTile() {
			return bankTile;
		}

		public RSTile getFurnaceTile() {
			return furnaceTile;
		}

		public RSArea getFurnaceArea() {
			return furnaceArea;
		}

		public RSArea getBankArea() {
			return bankArea;
		}

		RSTile bankTile, furnaceTile;
		
		RSArea furnaceArea, bankArea;
		
		Location(RSTile furnaceTile, RSTile bankTile, RSArea furnaceArea, RSArea bankArea) {
			this.furnaceTile = furnaceTile;
			this.bankTile = bankTile;
			this.furnaceArea = furnaceArea;
			this.bankArea = bankArea;
		}
	}
	
	public enum Item {
		BRONZE_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.TIN_ORE.getID(), ItemID.COPPER_ORE.getID()}, new int[] {14, 14}, 1, 311, 4, 0),
		IRON_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.IRON_ORE.getID()} , new int[] {28}, 1, 311, 6, 0),
		STEEL_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.IRON_ORE.getID(), ItemID.COAL.getID()}, new int[] {9, 18}, 2, 311, 8, 10),
		MITHRIL_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.MITHRIL_ORE.getID(), ItemID.COAL.getID()}, new int[] {5, 20}, 4, 311, 10, 5),
		ADAMANTITE_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.ADAMANITITE_ORE.getID(), ItemID.COAL.getID()}, new int[] {4, 24}, 6, 311, 11, 5),
		RUNITE_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.RUNITE_ORE.getID(), ItemID.COAL.getID()}, new int[] {3, 24}, 8, 311, 12, 5),
		SILVER_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.SILVER_ORE.getID()}, new int[] {28}, 1, 311, 7, 0),
		GOLD_BAR(ItemType.BAR, new int[] {}, new int[] {ItemID.GOLD_ORE.getID()}, new int[] {28}, 1, 311, 9, 0),
		CANNONBALL(ItemType.CANNONBALL, new int[] {ItemID.AMMO_MOULD.getID()}, new int[] {ItemID.STEEL_BAR.getID()}, new int[] {27}, 1, 309, 7, 0),
		GOLD_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID()}, new int[] {27}, 1, 446, 7, 0),
		SAPPHIRE_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.SAPPHIRE.getID()}, new int[] {13, 13}, 1, 446, 8, 0),
		EMERALD_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.EMERALD.getID()}, new int[] {13, 13}, 1, 446, 9, 0),
		RUBY_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.RUBY.getID()}, new int[] {13, 13}, 1, 446, 10, 0),
		DIAMOND_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DIAMOND.getID()}, new int[] {13, 13}, 1, 446, 11, 0),
		DRAGONSTONE_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DRAGONSTONE.getID()}, new int[] {13, 13}, 1, 446, 12, 0),
		ONYX_RING(ItemType.JEWELLERY, new int[] {ItemID.RING_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.ONYX.getID()}, new int[] {13, 13}, 1, 446, 13, 0),
		GOLD_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID()}, new int[] {27}, 1, 446, 20, 0),
		SAPPHIRE_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.SAPPHIRE.getID()}, new int[] {13, 13}, 1, 446, 21, 0),
		EMERALD_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.EMERALD.getID()}, new int[] {13, 13}, 1, 446, 22, 0),
		RUBY_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.RUBY.getID()}, new int[] {13, 13}, 1, 446, 23, 0),
		DIAMOND_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DIAMOND.getID()}, new int[] {13, 13}, 1, 446, 24, 0),
		DRAGONSTONE_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DRAGONSTONE.getID()}, new int[] {13, 13}, 1, 446, 25, 0),
		ONYX_NECKLACE(ItemType.JEWELLERY, new int[] {ItemID.NECKLACE_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.ONYX.getID()}, new int[] {13, 13}, 1, 446, 26, 0),
		GOLD_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID()}, new int[] {27}, 1, 446, 44, 0),
		SAPPHIRE_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.SAPPHIRE.getID()}, new int[] {13, 13}, 1, 446, 45, 0),
		EMERALD_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.EMERALD.getID()}, new int[] {13, 13}, 1, 446, 46, 0),
		RUBY_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.RUBY.getID()}, new int[] {13, 13}, 1, 446, 47, 0),
		DIAMOND_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DIAMOND.getID()}, new int[] {13, 13}, 1, 446, 48, 0),
		DRAGONSTONE_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DRAGONSTONE.getID()}, new int[] {13, 13}, 1, 446, 49, 0),
		ONYX_BRACELET(ItemType.JEWELLERY, new int[] {ItemID.BRACELET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.ONYX.getID()}, new int[] {13, 13}, 1, 446, 50, 0),
		GOLD_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID()}, new int[] {27}, 1, 446, 32, 0),
		SAPPHIRE_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.SAPPHIRE.getID()}, new int[] {13, 13}, 1, 446, 33, 0),
		EMERALD_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.EMERALD.getID()}, new int[] {13, 13}, 1, 446, 34, 0),
		RUBY_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.RUBY.getID()}, new int[] {13, 13}, 1, 446, 35, 0),
		DIAMOND_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DIAMOND.getID()}, new int[] {13, 13}, 1, 446, 36, 0),
		DRAGONSTONE_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.DRAGONSTONE.getID()}, new int[] {13, 13}, 1, 446, 37, 0),
		ONYX_AMULET(ItemType.JEWELLERY, new int[] {ItemID.AMULET_MOULD.getID()}, new int[] {ItemID.GOLD_BAR.getID(), ItemID.ONYX.getID()}, new int[] {13, 13}, 1, 446, 38, 0);
		
		private ItemType itemType;
		private int[] reqItems, bankItems, bankItemsAmount;
		private int parentInterface, childInterface, makeInt, minMaterials;
		
		public ItemType getItemType() {
			return itemType;
		}

		public int[] getReqItems() {
			return reqItems;
		}

		public int[] getBankItems() {
			return bankItems;
		}

		public int[] getBankItemsAmount() {
			return bankItemsAmount;
		}
		
		public int getMinMaterials() {
			return minMaterials;
		}
		
		public int getParentInterface() {
			return parentInterface;
		}
		
		public int getChildInterface() {
			return childInterface;
		}

		public int getMakeInt() {
			return makeInt;
		}
		
		Item (ItemType itemType, int[] reqItems, int[] bankItems, int[] bankItemsAmount, int minMaterials, int parentInterface, int childInterface, int makeInt) {
			this.itemType = itemType;
			this.reqItems = reqItems;
			this.bankItems = bankItems;
			this.bankItemsAmount = bankItemsAmount;
			this.minMaterials = minMaterials;
			this.parentInterface = parentInterface;
			this.childInterface = childInterface;
			this.makeInt = makeInt;
		}
	}
	
	
	public enum ItemID {
		TIN_ORE(438, 0),
		COPPER_ORE(436, 0),
		IRON_ORE(440, 0),
		COAL(453, 0),
		MITHRIL_ORE(447, 0),
		ADAMANITITE_ORE(449, 0),
		RUNITE_ORE(451, 0),
		SILVER_ORE(442, 0),
		GOLD_ORE(444, 0),
		SILVER_BAR(2355, 0),
		GOLD_BAR(2357, 0),
		STEEL_BAR(2353, 0),
		CANNONBALL(2, 0),
		AMMO_MOULD(4, 0),
		RING_MOULD(1592, 0),
		NECKLACE_MOULD(1597, 0),
		BRACELET_MOULD(11065, 0),
		AMULET_MOULD(1595, 0),
		TIARA_MOULD(5523, 0),
		SAPPHIRE(1607, 0),
		EMERALD(1605, 0),
		RUBY(1603, 0),
		DIAMOND(1601, 0),
		DRAGONSTONE(1615, 0),
		ONYX(6573, 0);
		
		private int itemID, itemPrice;
		
		ItemID(int itemID, int itemPrice) {
			this.itemID = itemID;
			this.itemPrice = itemPrice;
		}
		
		public int getID() {
			return this.itemID;
		}
		
		public int getPrice() {
			return this.itemPrice;
		}
		
		public void setPrice(int itemPrice) {
			this.itemPrice = itemPrice;
		}
		
	}
}
