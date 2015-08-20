package scripts.TrollMakeLeather;

import org.tribot.api.util.ABCUtil;

public class Variables {

	public static long startTime;
	
	public static int itemsMade = 0, hidePrice = 0, leatherPrice = 0, costs = 0, startXP;
	
	public static ABCUtil abc = new ABCUtil();
	
	public static boolean running = true;
	
	public static Leather leather;
	
	public enum Leather {
		GREEN_DRAGONHIDE(1753, 1745),
		BLUE_DRAGONHIDE(1751, 2505),
		RED_DRAGONHIDE(1749, 2507),
		BLACK_DRAGONHIDE(1747, 2509);
		
		private int id, tannedID;
		
		Leather(int id, int tannedID) {
			this.id = id;
			this.tannedID = tannedID;
		}
		
		public int getID() {
			return id;
		}
		
		public int getTannedID() {
			return tannedID;
		}
		
		public String toString() {
			String string = name().toLowerCase().replaceAll("_", " ");
			return string.replaceFirst(string.substring(0, 1), string.substring(0, 1).toUpperCase());
		}

	}
	
	
}
