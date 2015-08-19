package scripts.api.combat;

import org.tribot.api2007.Combat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSPlayer;

import java.util.ArrayList;

public class CombatHandler {

    public static boolean isUnderAttack(){
        ArrayList<Object> aggressiveEntity = new ArrayList<Object>();
        RSNPC[] rsnpcs = NPCs.getAll();
        for (RSNPC npc : rsnpcs){
            if (npc != null && npc.isInteractingWithMe() && npc.getHealth() > 0){
                aggressiveEntity.add(npc);
            }
        }
        RSPlayer[] rsplayer = Players.getAll();
        for (RSPlayer player : rsplayer){
            if (player != null && player.isInteractingWithMe() && Combat.isUnderAttack()){
                aggressiveEntity.add(player);
            }
        }
        return aggressiveEntity.size() > 0;
    }

    public static RSNPC getInterractingNPC(){
        RSNPC[] rsnpcs = NPCs.getAll();
        for (RSNPC npc : rsnpcs){
            if (npc != null && npc.isInteractingWithMe() && npc.getHealth() > 0){
                return npc;
            }
        }
        return null;
    }

}
