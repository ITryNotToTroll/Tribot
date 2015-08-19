package scripts.api.player;

import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSAnimableEntity;

public class Orientation {

    public enum Direction {
        NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST
    }

    public static Direction getPlayerOrientation() {
        final int orientation = (int) Math.round(Player.getRSPlayer().getOrientation() / 256.0);

        switch (orientation) {
            case 0:
                return Direction.SOUTH;
            case 1:
                return Direction.SOUTHWEST;
            case 2:
                return Direction.WEST;
            case 3:
                return Direction.NORTHWEST;
            case 4:
                return Direction.NORTH;
            case 5:
                return Direction.NORTHEAST;
            case 6:
                return Direction.EAST;
            case 7:
                return Direction.SOUTHEAST;
        }

        return null;

    }

    public static Direction getOrientation(RSAnimableEntity rsAnimableEntity) {
        final int orientation = (int) Math.round(rsAnimableEntity.getOrientation() / 256.0);

        switch (orientation) {
            case 0:
                return Direction.SOUTH;
            case 1:
                return Direction.SOUTHWEST;
            case 2:
                return Direction.WEST;
            case 3:
                return Direction.NORTHWEST;
            case 4:
                return Direction.NORTH;
            case 5:
                return Direction.NORTHEAST;
            case 6:
                return Direction.EAST;
            case 7:
                return Direction.SOUTHEAST;
        }

        return null;

    }

    public static int getRotation(){
        switch (getPlayerOrientation()){
            case NORTH:
                return General.random(22, -22);
            case NORTHWEST:
                return General.random(23,68);
            case WEST:
                return General.random(69,114);
            case SOUTHWEST:
                return General.random(115,160);
            case SOUTH:
                return General.random(161,206);
            case SOUTHEAST:
                return General.random(207,252);
            case EAST:
                return General.random(253,297);
            case NORTHEAST:
                return General.random(298,338);
        }
        return General.random(0,360);
    }

}
