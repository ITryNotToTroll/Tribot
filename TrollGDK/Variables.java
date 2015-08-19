package scripts.TrollGDK;

import java.util.ArrayList;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Variables {

	public static boolean running;
	
	//GUI
	public static boolean worldHopEnabled;
	public static Preset preset;
	
	//Paint
	public static String status;
	
	//Tiles & Areas
	public static RSArea dragonArea;
	public static RSArea gloryTeleportArea;
	public static RSTile ditchTile;
	public static RSArea castleWarsArea;
	
	//Equipment
	public static int mainWeaponID;
	public static int lootingBagID;
	
	//Spec
	public static boolean useSpec;
	public static int minSpecPercentage;
	public static int specWeaponID;
	
	//Eating
	public static int foodID;
	public static int eatAtPercentage;
	public static int foodAmount;
	
	//Looting
	public static ArrayList<String> lootItems = new ArrayList<String>();
	public static ArrayList<Integer> pots = new ArrayList<Integer>();
	public static ArrayList<Integer> equipment = new ArrayList<Integer>();
	

	public enum Preset {
		GREEN_DRAGONS,
		TAVERLY_DRUIDS,
	}
	
}
