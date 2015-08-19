package scripts.api.worldHopper;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.WorldHopper;

import java.util.ArrayList;

public class World {

    public World(){
        botWorlds.add(85);
        botWorlds.add(86);
        blackList.add(25);
        blackList.add(37);
    }

    public static ArrayList<Integer> botWorlds = new ArrayList<Integer>();
    public static ArrayList<Integer> blackList = new ArrayList<Integer>();

    public static int getWorld(){
        return WorldHopper.getWorld();
    }

    public boolean inBotWorld(){
        return botWorlds.contains(getWorld());
    }

    public static boolean hopRandom(Boolean inGame, Boolean members, Boolean useBlackList){
        blackList.add(getWorld());
        int newWorld = WorldHopper.getRandomWorld(members);
        if (useBlackList && blackList.contains(newWorld)){
            hopRandom(inGame, members, true);
        } else {
        	if(inGame) 
        		InGameWorld.switchWorld(newWorld);
        	else
        		hopToWorld(newWorld);
        	
            return true;
        }

    	return false;
    	
    }

    public static void hopToWorld(int world){
        WorldHopper.changeWorld(world);
        Timing.waitCondition(new Condition() {
			@Override 
			public boolean active() {
				General.sleep(20, 30);
				return Login.getLoginState() == STATE.INGAME;
			}
		}, General.random(4000, 5000));
    }

}
