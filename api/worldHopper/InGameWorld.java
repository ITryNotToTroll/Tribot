package scripts.api.worldHopper;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Login;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.Screen;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;

public class InGameWorld {

	private static final HashMap<Integer, Integer> CACHE = new HashMap<>();

	public static boolean switchWorld(int world){

		if (Interfaces.isInterfaceValid(378)){
			RSInterfaceChild child = Interfaces.get(378, 17);
			if (child != null) {
				if (Clicking.click(child)) {
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(100, 300);
							return !Interfaces.isInterfaceValid(378);
						}
					}, General.random(2000, 3000));
				}
			}
		}

		if (WorldHopper.getWorld() == world ){

			if (isInWorldSelect() && Login.getLoginState() == Login.STATE.LOGINSCREEN){
				clickRectangle(new Rectangle(710, 7, 45, 9), 16, 3);
			}

			return true;
		}

		if (Login.getLoginState() == Login.STATE.LOGINSCREEN){
			General.println("Login Screen Hopping to world: " + world);
			WorldHopper.changeWorld(world);
			return WorldHopper.getWorld() == world;
		}

		General.println("Ingame Hopping to world: " + world);
		if (!GameTab.open(GameTab.TABS.LOGOUT) || !openWorldSwitchInterface() || !moveMouseInsideWorldSwitchInterface()){
			return false;
		}
		RSInterface rsInterface = getWorldInterfaceChild(world);
		return rsInterface != null && scrollToWorldInterface(rsInterface) && worldSwitch(rsInterface, world) && waitUntilWorld(world);
	}

	private static boolean openWorldSwitchInterface(){
		if (Interfaces.isInterfaceValid(182)){
			RSInterfaceChild child = Interfaces.get(182, 5);
			if (child == null || !child.click() || !Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(50, 100);
					return Interfaces.isInterfaceValid(69);
				}
			}, General.random(1200, 2200))){
				return false;
			}
		}
		return Interfaces.isInterfaceValid(69);
	}

	private static boolean moveMouseInsideWorldSwitchInterface(){
		RSInterfaceChild worldSelectBox = Interfaces.get(69, 4);
		Rectangle bounds = null;
		if (worldSelectBox != null){
			bounds = worldSelectBox.getAbsoluteBounds();
			if (bounds != null && !bounds.contains(Mouse.getPos())) {
				Mouse.moveBox(bounds);
			}
		}
		return bounds != null && bounds.contains(Mouse.getPos());
	}

	private static RSInterface getWorldInterfaceChild(int world){
		if (CACHE.containsKey(world)){
			RSInterfaceChild child = Interfaces.get(69, 7);
			RSInterfaceComponent[] components;
			if (child != null && (components = child.getChildren()) != null){
				int index = CACHE.get(world);
				if (components.length > index){
					return components[index];
				}
			}
		}
		RSInterfaceChild worldInterface = Interfaces.get(69, 7);
		if (worldInterface != null){
			RSInterfaceComponent[] components = worldInterface.getChildren();
			if (components != null){
				for (int i = 2; i <= 398; i+=6) {
					if (components.length > i) {
						String text = components[i].getText();
						try {
							if (text != null && Integer.parseInt(text) == world) {
								CACHE.put(world, i - 2);
								return components[i];
							}
						} catch (NumberFormatException e){

						}
					}
				}
			}
		}
		return null;
	}

	private static boolean scrollToWorldInterface(RSInterface desiredWorld){
		final long timeout = System.currentTimeMillis() + 7000;
		Rectangle worldClickBox;
		while ((worldClickBox = desiredWorld.getAbsoluteBounds()) != null && (worldClickBox.y < 240 || worldClickBox.y > 417)){
			Mouse.scroll(worldClickBox.y < 240);
			General.sleep(50, 100);
			if (System.currentTimeMillis() > timeout){
				return false;
			}
		}
		General.sleep(100, 300);
		return worldClickBox != null && worldClickBox.y >= 240 && worldClickBox.y <= 417;
	}

	private static boolean worldSwitch(RSInterface desiredWorld, int world){
		return Clicking.click(desiredWorld) && Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return NPCChat.getOptions() != null || WorldHopper.getWorld() == world;
			}
		}, General.random(2000, 3000)) && (WorldHopper.getWorld() == world || (NPCChat.getOptions() != null && NPCChat.selectOption("Yes.", true)));
	}

	private static boolean waitUntilWorld(int world){
		return Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				if (WorldHopper.getWorld() == world && (Login.getLoginState() == Login.STATE.INGAME || Login.getLoginState() == Login.STATE.LOGINSCREEN)){
					General.println("We hopped to world: " + world);
					return true;
				}
				return false;
			}
		}, General.random(4000, 6000));
	}

	private static boolean isInWorldSelect(){
		return Screen.getColorAt(713, 10).equals(Color.BLACK);
	}

	private static void openWorldSelect(){
		Rectangle rectangle = new Rectangle(10, 467, 88, 26);
		clickRectangle(rectangle, 13, 3);
	}

	public static void clickRectangle(Rectangle rectangle, int sdx, int sdy){
		Mouse.hop(new Point(
				General.randomSD((rectangle.x + (rectangle.x + rectangle.width))/2, sdx),
				General.randomSD((rectangle.y + (rectangle.y + rectangle.height))/2, sdy)));
		Mouse.click(1);
	}

}
