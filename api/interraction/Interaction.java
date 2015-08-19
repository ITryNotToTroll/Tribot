package scripts.api.interraction;


import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSObject;

import scripts.api.camera.CameraCalculations;
import scripts.api.navigation.Navigator;

public class Interaction {

    public static boolean interractWithObject(String name, String option, Condition condition, long timeout){
        RSObject[] obj = Objects.findNearest(15,name);
        if (obj.length > 0 && obj[0] != null){
            try {
                if (Projection.isInViewport(obj[0].getModel().getCentrePoint())) {
                    if (Clicking.click(option, obj[0])) {
                        return Timing.waitCondition(condition, timeout);
                    }
                } else {
                    Camera.turnToTile(obj[0]);
                    Camera.setCameraAngle(CameraCalculations.getAngleFor(obj[0].getPosition()));
                    if (Projection.isInViewport(obj[0].getModel().getCentrePoint())) {
                        interractWithObject(name, option, condition, timeout);
                    } else {
                        Navigator.walkNearby(obj[0].getPosition());
                        interractWithObject(name, option, condition, timeout);
                    }
                }
            } catch (Exception e){}
        }
        return false;
    }
    
    public static boolean interractWithObject(int name, String option, Condition condition, long timeout){
        RSObject[] obj = Objects.findNearest(15,name);
        if (obj.length > 0 && obj[0] != null){
            try {
            	Camera.setCameraRotation(General.random(Camera.getCameraRotation() - 30, Camera.getCameraRotation() + 30));
                if (Projection.isInViewport(obj[0].getModel().getCentrePoint())) {
                    if (Clicking.click(option, obj[0])) {
                        return Timing.waitCondition(condition, timeout);
                    }
                } else {
                    Camera.turnToTile(obj[0]);
                    Camera.setCameraAngle(CameraCalculations.getAngleFor(obj[0].getPosition()));
                    if (Projection.isInViewport(obj[0].getModel().getCentrePoint())) {
                        interractWithObject(name, option, condition, timeout);
                    } else {
                        Navigator.walkNearby(obj[0].getPosition());
                        interractWithObject(name, option, condition, timeout);
                    }
                }
            } catch (Exception e){}
        }
        return false;
    }

    public static boolean clicking (RSObject rsObject){
        Clicking.click(rsObject);
        return Game.getCrosshairState() == 2;
    }

	public static boolean interractWithNPC(String npcName, String npcString,
			Condition condition, int random) {
		// TODO Auto-generated method stub
		return false;
	}

}
