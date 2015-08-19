package scripts.TrollGDK.Handlers;

import org.tribot.api2007.Login;

import scripts.api.worldHopper.World;

public class WorldHopHandler {

	public static void hop() {
		World.hopRandom(true, true);
	}
}
