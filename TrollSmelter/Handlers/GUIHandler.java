package scripts.TrollSmelter.Handlers;

import scripts.TrollSmelter.Variables.Item;
import scripts.TrollSmelter.Variables.Location;

public class GUIHandler{
	
	public static Location getLocation(String location) {
		switch(location) {
		case "Al Kharid":
			return Location.AL_KHARID;
		case "Edgeville":
			return Location.EDGEVILLE;
		case "Port Phasmatys":
			return Location.PORT_PHASMATYS;
		}
		return Location.EDGEVILLE;
	}
	
	public static Item getItem(String name) {
		switch(name) {
		case "Bronze":
			return Item.BRONZE_BAR;
		case "Iron":
			return Item.IRON_BAR;
		case "Steel":
			return Item.STEEL_BAR;
		case "Mithril":
			return Item.MITHRIL_BAR;
		case "Adamantite":
			return Item.ADAMANTITE_BAR;
		case "Runite":
			return Item.RUNITE_BAR;
		case "Silver":
			return Item.SILVER_BAR;
		case "Gold":
			return Item.GOLD_BAR;
		}
		
		return Item.CANNONBALL;
	}
	
	public static Item getItem(String name, String gem) {
		switch(name) {
		case "Ring":
			switch(gem) {
			case "Gold":
				return Item.GOLD_RING;
			case "Sapphire":
				return Item.SAPPHIRE_RING;
			case "Emerald":
				return Item.EMERALD_RING;
			case "Ruby":
				return Item.RUBY_RING;
			case "Diamond":
				return Item.DIAMOND_RING;
			case "Dragonstone":
				return Item.DRAGONSTONE_RING;
			case "Onyx":
				return Item.ONYX_RING;
			}
		case "Necklace":
			switch(gem) {
			case "Gold":
				return Item.GOLD_NECKLACE;
			case "Sapphire":
				return Item.SAPPHIRE_NECKLACE;
			case "Emerald":
				return Item.EMERALD_NECKLACE;
			case "Ruby":
				return Item.RUBY_NECKLACE;
			case "Diamond":
				return Item.DIAMOND_NECKLACE;
			case "Dragonstone":
				return Item.DRAGONSTONE_NECKLACE;
			case "Onyx":
				return Item.ONYX_NECKLACE;
			}
		case "Amulet":
			switch(gem) {
			case "Gold":
				return Item.GOLD_AMULET;
			case "Sapphire":
				return Item.SAPPHIRE_AMULET;
			case "Emerald":
				return Item.EMERALD_AMULET;
			case "Ruby":
				return Item.RUBY_AMULET;
			case "Diamond":
				return Item.DIAMOND_AMULET;
			case "Dragonstone":
				return Item.DRAGONSTONE_AMULET;
			case "Onyx":
				return Item.ONYX_AMULET;
			}
		case "Bracelet":
			switch(gem) {
			case "Gold":
				return Item.GOLD_BRACELET;
			case "Sapphire":
				return Item.SAPPHIRE_BRACELET;
			case "Emerald":
				return Item.EMERALD_BRACELET;
			case "Ruby":
				return Item.RUBY_BRACELET;
			case "Diamond":
				return Item.DIAMOND_BRACELET;
			case "Dragonstone":
				return Item.DRAGONSTONE_BRACELET;
			case "Onyx":
				return Item.ONYX_BRACELET;
			}
		}
		return null;
	}

}
