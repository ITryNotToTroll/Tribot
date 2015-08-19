package scripts.api.camera;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSAnimableEntity;
import org.tribot.api2007.types.RSTile;

public class CameraCalculations {

    public static int getAngleFor(RSAnimableEntity animableEntity){

        if (animableEntity != null){
            int distance = animableEntity.getPosition().distanceTo(Player.getPosition());
            Camera.turnToTile(animableEntity.getPosition());
            return  (100 - ((5*distance) + General.random(-5, 5)));
        }

        return 100;

    }

    public static int getAngleFor(RSTile tile){

        if (tile != null){
            int distance = tile.getPosition().distanceTo(Player.getPosition());
            Camera.turnToTile(tile.getPosition());
            return  (100 - ((5*distance) + General.random(-5, 5)));
        }

        return 100;

    }

}
