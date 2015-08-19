package scripts.TrollGDK;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

import scripts.TrollGDK.Handlers.*;
import scripts.api.navigation.Navigator;

public class GDK {
	
	enum State {
		WORLDHOP,
		LOOT,
		BANK,
		ATTACK,
		ESCAPE,
		POT,
		PRAY,
		FILL_BAG,
		EQUIP,
		EAT,
		SPEC,
		IDLE,
		TO_DRAGONS,
	}
	
	public static State getState() {
		if(EquipmentHandler.isItemsToEquip())
			return State.EQUIP;
		
		if(BankHandler.needToBank())
			return State.BANK;
		
		if(EscapeHandler.needToEscape())
			return State.ESCAPE;
		
		if(Variables.dragonArea.contains(Player.getPosition())) {
			if(FoodHandler.needToEat())
				return State.EAT;

			if(Inventory.isFull()) {
				if(!BagHandler.isBagFull())
					return State.FILL_BAG;
				
				return FoodHandler.hasFood() ? State.EAT:State.BANK;
			}
			
			if(PrayerHandler.needToPray()) 
				return State.PRAY;
			
			if(PotHandler.needToPot())
				return State.POT;
			
			if(!EquipmentHandler.isWeaponEquipped())
				return State.EQUIP;
			
			if(SpecHandler.canSpec())
				return State.SPEC;
			
			if(LootHandler.isLootAvaliable())
				return State.LOOT;
			
			return Player.getRSPlayer().isInCombat() ? State.IDLE:State.ATTACK;
		}
		return State.TO_DRAGONS;
	}
	
	public static void run() {
		switch(getState()) {
		case WORLDHOP:
			
			WorldHopHandler.hop();
			
			break;
			
		case LOOT:
			
			LootHandler.loot();
			
			break;
			
		case BANK:
			
			if(BankHandler.walkToBank())
				BankHandler.bank();
			
			break;
			
		case ATTACK:
			
			AttackHandler.attackDragon();
			
			break;
			
		case ESCAPE:
			
			EscapeHandler.escape(Variables.preset);
			
			break;
			
		case POT:
			
			break;
			
		case PRAY:
			
			break;
			
		case FILL_BAG:
			
			break;
			
		case EQUIP:
			
			EquipmentHandler.equipItems();
			
			break;
			
		case EAT:
			
			FoodHandler.eat();
			
			break;
			
		case SPEC:
			
			if(SpecHandler.switchWeapon())
				SpecHandler.useSpec();
			
			break;
			
		case IDLE:
			
			break;
			
		case TO_DRAGONS:
			
			AttackHandler.walkToDragons(Variables.preset);
			
			break;
			
		}
	}
	
}
