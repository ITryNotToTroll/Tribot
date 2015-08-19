package scripts.api.npc;

import org.tribot.api.DynamicClicking;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.util.DPathNavigator;

public class NPC {

    private static DPathNavigator dpath = new DPathNavigator();

    public static RSNPC getClosest (String name){

        RSNPC[] npc = NPCs.find(name);

        int distance = 9999;
        RSNPC closestNPC = null;

        for (RSNPC x : npc){
            if (x != null){
                int pathDistance = dpath.findPath(x).length;
                if (distance > pathDistance){
                    distance = pathDistance;
                    closestNPC = x;
                }
            }
        }

        return closestNPC;

    }

    public static RSNPC getClosestNotInCombat (String name){

        RSNPC[] npc = NPCs.find(name);

        int distance = 9999;
        RSNPC closestNPC = null;

        for (RSNPC x : npc){
            if (x != null && !x.isInCombat()){
                int pathDistance = dpath.findPath(x).length;
                if (distance > pathDistance){
                    distance = pathDistance;
                    closestNPC = x;
                }
            }
        }

        return closestNPC;

    }

    public static boolean click(RSNPC npc, String option){
        if (npc != null){
            if (npc.isOnScreen()){
                DynamicClicking.clickRSNPC(npc,option);
                return (Game.getCrosshairState() == 2);
            } else {
                Camera.turnToTile(npc);
                if (!npc.isOnScreen()){
                    int x = 100 - (npc.getPosition().distanceTo(Player.getPosition()) * 5);
                    Camera.setCameraAngle(x);
                    if (npc.isOnScreen()) {
                        DynamicClicking.clickRSNPC(npc, option);
                        return (Game.getCrosshairState() == 2);
                    }
                }
            }
        }
        return false;
    }


}
