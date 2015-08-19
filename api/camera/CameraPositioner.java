package scripts.api.camera;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.types.RSAnimableEntity;

public class CameraPositioner extends Thread {

    RSAnimableEntity rsAnimableEntity = null;

    public CameraPositioner(RSAnimableEntity rsAnimableEntity){
        this.rsAnimableEntity = rsAnimableEntity;
    }

    public void run(){

        try {
            sleep(General.random(400,600));
        } catch (InterruptedException e){}

        if (Camera.getRotationMethod() != Camera.ROTATION_METHOD.ONLY_KEYS){
            Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_KEYS);
        }
        if (rsAnimableEntity != null) {
            Camera.turnToTile(rsAnimableEntity);
            Camera.setCameraAngle(CameraCalculations.getAngleFor(rsAnimableEntity));
        }
    }

}
