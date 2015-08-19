package scripts.TrollGDK.Handlers;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSPlayer;

import scripts.TrollGDK.Variables;
import scripts.TrollGDK.Variables.Preset;
import scripts.api.camera.CameraCalculations;
import scripts.api.interraction.Interaction;
import scripts.api.navigation.Navigator;

public class AttackHandler {

	public static boolean walkToDragons(Preset p) {

		switch(p) {
		case GREEN_DRAGONS:

			if(Navigator.walkFar(Variables.ditchTile)) {
				if(Interaction.interractWithObject("Wilderness ditch", "Cross", new Condition() {
					@Override
					public boolean active() {
						General.sleep(20, 30);
						return Interfaces.get(381, 1) == null && !Player.isMoving() && Player.getAnimation() == -1;
					}
				}, General.random(6000, 7000))) {
					Navigator.walkFar(Variables.dragonArea.getRandomTile());
					return Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Variables.dragonArea.contains(Player.getPosition());
						}
					}, General.random(3000, 4000));
				}
			}

			break;
			
		case TAVERLY_DRUIDS:
			
			break;

		}
		return false;
	}

	public static boolean attackDragon() {
		RSNPC[] dragon = NPCs.findNearest(new Filter<RSNPC>(){
			@Override
			public boolean accept(RSNPC npc) {
				return !npc.isInCombat() && npc.getCombatCycle() == -1000 && npc.getInteractingCharacter() == null;
			}
		});
		if(dragon.length > 0 && dragon[0] != null) { 
			try {
				if (Projection.isInViewport(dragon[0].getModel().getCentrePoint())) {
					if(canAttackPlayer()) {
						Clicking.hover(dragon[0]);
						Mouse.click(3);
						if(Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(20, 30);
								return ChooseOption.isOpen();
							}
						}, General.random(1000, 1500))) {
							ChooseOption.select("Attack Green dragon");
						}
					} else {
						Clicking.click("Attack Green dragon", dragon[0]);
					}
					return Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							General.sleep(20, 30);
							return Player.getRSPlayer().isInCombat();
						}
					}, General.random(4000, 5000));

				} else {
					Camera.turnToTile(dragon[0]);
					Camera.setCameraAngle(CameraCalculations.getAngleFor(dragon[0].getPosition()));
					if (Projection.isInViewport(dragon[0].getModel().getCentrePoint())) {
						return attackDragon();
					} else {
						Navigator.walkNearby(dragon[0].getPosition());
						return attackDragon();
					}
				}
			} catch (Exception e){}
		}
		return false;
	}

	public static boolean canAttackPlayer() {
		RSPlayer[] players = Players.getAll();
		for(RSPlayer player:players) {
			if(player != Player.getRSPlayer()) {
				int wildernessLevel = Combat.getWildernessLevel();
				if(player.getCombatLevel() + wildernessLevel >= Player.getRSPlayer().getCombatLevel()
						|| player.getCombatLevel() - wildernessLevel <= Player.getRSPlayer().getCombatLevel()) {
					return true;
				}
			}
		}
		return false;
	}

}
