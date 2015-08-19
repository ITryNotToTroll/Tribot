package scripts.api.navigation;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSAnimableEntity;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

import scripts.api.graphics.daxPaint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Navigator {

    private static DPathNavigator dPathNavigator = new DPathNavigator();
    private static ArrayList<RSTile> path = new ArrayList<RSTile>();
    private static final Color GREEN = new Color(0, 205, 19,70);

    public static boolean walkNearby(final RSTile location){
        path.addAll(Arrays.asList(dPathNavigator.findPath(location)));
        dPathNavigator.traverse(location);
        path.clear();

        return Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(350);
                return Player.getPosition().distanceTo(location) <= 3;
            }
        },5000);

    }

    public static boolean walkNearby(final RSTile location, final RSAnimableEntity target){
        DPathNavigator dPathCustom = new DPathNavigator();

        dPathCustom.setStoppingCondition(new Condition() {
            @Override
            public boolean active() {

                return (target != null && target.getModel() != null &&
                        Projection.isInViewport(target.getModel().getCentrePoint())) ||
                        Player.getPosition().distanceTo(location) < 3;
            }
        });

        path.addAll(Arrays.asList(dPathCustom.findPath(location)));
        dPathCustom.traverse(location);
        path.clear();
        return target.getPosition().isOnScreen();

    }

    public static boolean walkFar(final RSTile location){

        DPathNavigator dPathFar = new DPathNavigator();

        final RSArea mapCache = new RSArea(new RSTile(Game.getBaseX(),Game.getBaseY()),
                new RSTile(Game.getBaseX() + 104, Game.getBaseY() + 104));

        dPathFar.setStoppingCondition(new Condition() {
            @Override
            public boolean active() {
                return !mapCache.contains(new RSTile(Game.getBaseX(),Game.getBaseY(),Game.getPlane()));
            }
        });

        int j = 9999;
        RSTile tile = null;
        for (RSTile x : mapCache.getAllTiles()){
            int k = x.distanceTo(location);
            if (k < j && PathFinding.canReach(x,false)){
                j = k;
                tile = x;
            }
        }

        for (RSTile x : dPathFar.findPath(tile)){
            path.add(x);
        }

        dPathFar.traverse(tile);

        path.clear();

        if (location.distanceTo(Player.getPosition()) > 4){
            walkFar(location);
        }

        return true;

    }

    public static void drawPath(Graphics g){
        Graphics2D g1 = (Graphics2D)g;
        for (RSTile tile: path){
            Point point = Projection.tileToMinimap(tile);
            daxPaint.drawCircle(g1, point.x, point.y, 4, GREEN, GREEN);
        }
    }

    public enum Bank {
        VARROCK_WEST (new RSTile(3183,3439,0), "Varrock West"),
        VARROCK_EAST (new RSTile(3253,3421,0), "Varrock East"),
        DRAYNOR (new RSTile(3092,3242,0), "Draynor"),
        FALADOR_WEST (new RSTile(2945,3370,0), "Falador West"),
        FALADOR_EAST (new RSTile(3013,3355,0), "Falador East"),
        CATHERBY (new RSTile(2808,3440), "Catherby"),
        SEERS (new RSTile(2724,3491,0), "Seer's"),
        ARDOUGNE_NORTH (new RSTile(2614,3333,0), "Ardougne North"),
        ARDOUGNE_SOUTH (new RSTile(2653,3284,0), "Ardougne South"),
        YANILLE (new RSTile(2611,3093,0), "Yanille"),
        AL_KHARID (new RSTile(3267,3166,0), "Al Kharid"),
        NULL (new RSTile(0,0,0), "Null");

        RSTile tile;
        String name;

        Bank (RSTile tile, String name){
            this.tile = tile;
            this.name = name;
        }
        public RSTile getTile(){
            return this.tile;
        }
        public String getName(){
            return this.name;
        }

    }

    public static boolean walkToBank(){
        Bank closestBank = getClosestBank();

        if (closestBank != Bank.NULL){
            if (walkFar(closestBank.getTile())){
                if (Banking.isInBank()){
                    return true;
                } else {
                    return WebWalking.walkToBank();
                }
            }
        }
        return Banking.isInBank();
    }

    public static Bank getClosestBank(){
        Bank closestBank = Bank.NULL;
        for (Bank banks : Bank.values()){
            banks.getTile();
            if (banks.getTile().distanceTo(Player.getPosition()) <
                    closestBank.getTile().distanceTo(Player.getPosition())){
                closestBank = banks;
            }
        }
        return closestBank;
    }

    
    public static int getIndexClosestTileInPath(RSTile[] path) {
    	int closest = 99999;
    	int index = 0;
    	for(int i = 0;i<path.length;i++) {
    		if(path[i].distanceTo(Player.getPosition()) < closest) {
    			index = i;
    			closest = path[i].distanceTo(Player.getPosition());
    		}
    	}
    	return index;
    }
    
}
