package scripts.api.combat;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;

import scripts.api.camera.CameraPositioner;
import scripts.api.navigation.Navigator;
import scripts.api.npc.NPC;

public class Attacking {

    public static boolean attack(String name){
        RSNPC npc = NPC.getClosestNotInCombat(name);
        if (npc != null) {
            if (npc.isOnScreen()) {
                if (Clicking.click("Attack", npc))
                    return Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100);
                            return Player.getRSPlayer().getInteractingCharacter() != null;
                        }
                    }, General.random(1400,1700));
            } else {
                CameraPositioner cameraPositioner = new CameraPositioner(npc);
                cameraPositioner.start();
                Navigator.walkNearby(npc.getPosition(),npc);
            }
        }
        return false;
    }

    public static boolean attack(RSNPC npc){
        if (npc != null){
            if (npc.isOnScreen()) {
                if (Clicking.click("Attack", npc))
                    return Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100);
                            return Player.getRSPlayer().getInteractingCharacter() != null;
                        }
                    }, 1500);
            } else {
                CameraPositioner cameraPositioner = new CameraPositioner(npc);
                cameraPositioner.start();
                Navigator.walkNearby(npc.getPosition(),npc);
            }
        }
        return false;
    }

}
