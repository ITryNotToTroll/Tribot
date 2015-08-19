package scripts.api.abc;

import java.awt.Polygon;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Game;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Options;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSModel;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;

/**
 * Utility class that allows for simple implementation of Anti-ban Compliance.
 *
 * Create a new ABC object in your script and reference it wherever you need to place anti-ban compliance methods.
 *
 * When any a.b.c. method is called, the seed is checked. If the next action to be performed should be performed, it will be. Otherwise the method will return.
 *
 * @author Starfox
 */
public class ABC {

    private final ABCUtil abc;

    private int resources_won;
    private int resources_lost;

    /**
     * Creates a new ABC. By default, sets the use of general a.b.c. to be true.
     */
    public ABC() {
        General.useAntiBanCompliance(true);
        this.abc = new ABCUtil();
        this.resources_won = 0;
        this.resources_lost = 0;
    }

    /**
     * Gets the ABCUtil object.
     *
     * @return The ABCUtil object.
     */
    public final ABCUtil getABCUtil() {
        return this.abc;
    }

    /**
     * Gets the amount of resources won.
     *
     * @return The amount.
     */
    public final int getResourcesWon() {
        return this.resources_won;
    }

    /**
     * Gets the amount of resources lost.
     *
     * @return The amount.
     */
    public final int getResourcesLost() {
        return this.resources_lost;
    }

    /**
     * Sets the amount of resources won to the specified amount.
     *
     * @param amount The amount to set.
     */
    public final void setResourcesWon(int amount) {
        this.resources_won = amount;
    }

    /**
     * Sets the amount of resources lost to the specified amount.
     *
     * @param amount The amount to set.
     */
    public final void setResourcesLost(int amount) {
        this.resources_lost = amount;
    }

    /**
     * Checks to see whether or not the specified bool tracker is ready.
     *
     * @param tracker The tracker to check.
     * @return True if it is ready, false otherwise.
     */
    public final boolean isBoolTrackerReady(ABCUtil.BOOL_TRACKER tracker) {
        return tracker.next();
    }

    /**
     * Checks to see whether or not the specified switch tracker is ready.
     *
     * @param tracker The tracker to check.
     * @param playerCount The player count.
     * @return True if it is ready, false otherwise.
     */
    public final boolean isSwitchTrackerReady(ABCUtil.SWITCH_TRACKER tracker, int playerCount) {
        return tracker.next(playerCount);
    }

    /**
     * Gets the time (in milliseconds) until the next action will be performed for the specified delay tracker.
     *
     * @param tracker The tracker.
     * @return The time (in milliseconds) until the next action will be performed.
     */
    public final long getTimeUntilNext(ABCUtil.DELAY_TRACKER tracker) {
        return tracker.next() - System.currentTimeMillis();
    }

    /**
     * Gets the time (in milliseconds) until the next action will be performed for the specified int tracker.
     *
     * @param tracker The tracker.
     * @return The time (in milliseconds) until the next action will be performed.
     */
    public final long getTimeUntilNext(ABCUtil.INT_TRACKER tracker) {
        return tracker.next() - System.currentTimeMillis();
    }

    /**
     * Gets the time (in milliseconds) until the next action will be performed for the specified time tracker.
     *
     * @param tracker The tracker.
     * @return The time (in milliseconds) until the next action will be performed.
     */
    public final long getTimeUntilNext(ABCUtil.TIME_TRACKER tracker) {
        return tracker.next() - System.currentTimeMillis();
    }

    /**
     * Gets the next run at based off the int tracker.
     *
     * @return The next run energy.
     */
    public final int getNextRun() {
        return abc.INT_TRACKER.NEXT_RUN_AT.next();
    }

    /**
     * Gets the next eat percent based off the int tracker.
     *
     * @return The next eat percent.
     */
    public final int getNextHpPercent() {
        return abc.INT_TRACKER.NEXT_EAT_AT.next();
    }

    /**
     * Randomly moves the camera. Happens only if the time tracker for camera movement is ready.
     */
    public final void moveCamera() {
        abc.performRotateCamera();
    }

    /**
     * Checks the xp of the specified skill. Happens only if the time tracker for checking xp is ready.
     *
     * @param skill The skill to check.
     */
    public void checkXp(SKILLS skill) {
        abc.performXPCheck(skill);
    }

    /**
     * Activates run. Happens only when run is off & your run energy is >=
     */
    public final void activateRun() {
        if (Game.getRunEnergy() >= getNextRun() && !Game.isRunOn()) {
            if (Options.setRunOn(true)) {
                abc.INT_TRACKER.NEXT_RUN_AT.reset();
            }
        }
    }

    /**
     * Picks up the mouse. Happens only if the time tracker for picking up the mouse is ready.
     */
    public final void pickUpMouse() {
        abc.performPickupMouse();
    }

    /**
     * Leaves the game. Happens only if the time tracker for leaving the game is ready.
     */
    public void leaveGame() {
        abc.performLeaveGame();
    }

    /**
     * Examines a random object. Happens only if the time tracker for examining a random object is ready.
     */
    public final void examineObject() {
        abc.performExamineObject();
    }

    /**
     * Right clicks a random spot. Happens only if the time tracker for right clicking a random spot is ready.
     */
    public final void rightClick() {
        abc.performRandomRightClick();
    }

    /**
     * Randomly moves the mouse. Happens only if the time tracker for randomly moving the mouse is ready.
     */
    public final void mouseMovement() {
        abc.performRandomMouseMovement();
    }

    /**
     * Performs a combat tab check. Happens only if the time tracker for combat tab checking is ready.
     */
    public final void combatCheck() {
        abc.performCombatCheck();
    }

    /**
     * Performs an equipment tab check. Happens only if the time tracker for equipment tab checking is ready.
     */
    public final void equipmentCheck() {
        abc.performEquipmentCheck();
    }

    /**
     * Performs a friends tab check. Happens only if the time tracker for friends tab checking is ready.
     */
    public final void friendsCheck() {
        abc.performFriendsCheck();
    }

    /**
     * Performs a music tab check. Happens only if the time tracker for friends tab checking is ready.
     */
    public final void musicCheck() {
        abc.performMusicCheck();
    }

    /**
     * Performs a quest tab check. Happens only if the time tracking for friends tab checking is ready.
     */
    public final void questCheck() {
        abc.performQuestsCheck();
    }

    /**
     * Sleeps for the switch object delay time.
     */
    public final void waitSwitchObjectDelay() {
        General.sleep(abc.DELAY_TRACKER.SWITCH_OBJECT.next());
        abc.DELAY_TRACKER.SWITCH_OBJECT.reset();
    }

    /**
     * Sleeps for the switch object combat delay time.
     */
    public final void waitSwitchObjectCombatDelay() {
        General.sleep(abc.DELAY_TRACKER.SWITCH_OBJECT_COMBAT.next());
        abc.DELAY_TRACKER.SWITCH_OBJECT_COMBAT.reset();
    }

    /**
     * Sleeps for the new object delay time.
     */
    public final void waitNewObjectDelay() {
        General.sleep(abc.DELAY_TRACKER.NEW_OBJECT.next());
        abc.DELAY_TRACKER.NEW_OBJECT.reset();
    }

    /**
     * Sleeps for the new object combat delay time.
     */
    public final void waitNewObjectCombatDelay() {
        General.sleep(abc.DELAY_TRACKER.NEW_OBJECT_COMBAT.next());
        abc.DELAY_TRACKER.NEW_OBJECT_COMBAT.reset();
    }

    /**
     * Sleeps for the interaction delay time.
     */
    public final void waitInteractionDelay() {
        General.sleep(abc.DELAY_TRACKER.ITEM_INTERACTION.next());
    }

    /**
     * Executes the NEW_OBJECT/SWITCH_OBJECT delay, to be executed right before clicking objects/NPCs/players/tiles/ground items/minimap tiles/items. Not to be
     * used between item clicks; only if we have to wait for an item to popup in the inventory before clicking on it, and if we do not know the exact time at
     * which the item will appear in the inventory. In other words, this is the reaction delay when responding to a changing environment where we do not know
     * the exact time at which the environment will change to the way we want.
     *
     * @param last_busy_time The timestamp at which the player was last working/mining/woodcutting/fighting/fishing/crafting/etc. The timestamp beings when we
     * have to move on to the next resource.
     *
     * @param combat True if the player is in combat, or the script is one which the player is constantly performing actions, and requires the player to have
     * very fast actions (such as sorceress's garden).
     */
    public void waitNewOrSwitchDelay(final long last_busy_time, final boolean combat) {
        abc.waitNewOrSwitchDelay(last_busy_time, combat);
    }

    /**
     * Hovers the next available object if applicable. YOU MUST RESET THE TRACKER YOURSELF AFTER THE CURRENT OBJECT IS GONE/DEPLETED.
     *
     * @param currentlyInteracting The object you are currently interacting with.
     * @param objectName The name of the object you wish to hover.
     */
    public final void hoverNextObject(final RSObject currentlyInteracting, final String objectName) {
        if (currentlyInteracting == null || objectName == null) {
            return;
        }
        final RSObject[] objects = Objects.findNearest(10, new Filter<RSObject>() {
            @Override
            public boolean accept(RSObject o) {
                if (o == null) {
                    return false;
                }
                final RSObjectDefinition def = o.getDefinition();
                if (def != null) {
                    final String name = def.getName();
                    if (name != null) {
                        return name.equalsIgnoreCase(objectName) && !o.getPosition().equals(currentlyInteracting.getPosition()) && o.isOnScreen();
                    }
                }
                return false;
            }
        });
        if (objects.length <= 0) {
            return;
        }
        if (abc.BOOL_TRACKER.HOVER_NEXT.next()) {
            final RSObject next = objects[0];
            if (next != null) {
                if (!ObjectUtil.isHovering(next) && next.hover()) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return ObjectUtil.isHovering(next);
                        }
                    }, 100);
                }
            }
        }
    }

    /**
     * Gets an object near you with the specified id. This object will either be the nearest, or second nearest depending on the tracker.
     *
     * @param id The id of the object you are looking to find.
     * @param distance The distance range.
     * @return An object with the specified id near you. Null if no objects were found.
     */
    public final RSObject getUseClosestObject(final int id, final int distance) {
        final RSObject[] objects = Objects.findNearest(distance, id);
        if (objects.length < 1) {
            return null;
        }
        RSObject object_to_click = objects[0];
        if (objects.length > 1 && abc.BOOL_TRACKER.USE_CLOSEST.next()) {
            if (objects[1].getPosition().distanceToDouble(objects[0]) < 3.0) {
                object_to_click = objects[1];
            }
        }
        abc.BOOL_TRACKER.USE_CLOSEST.reset();
        return object_to_click;
    }

    /**
     * Gets an object near you with the specified name. This object will either be the nearest, or second nearest depending on the tracker.
     *
     * @param name The name of the object you are looking to find.
     * @param distance The distance range.
     * @return An object with the specified name near you. Null if no objects were found.
     */
    public final RSObject getUseClosestObject(final String name, final int distance) {
        final RSObject[] objects = Objects.findNearest(distance, name);
        if (objects.length < 1) {
            return null;
        }
        RSObject object_to_click = objects[0];
        if (objects.length > 1 && abc.BOOL_TRACKER.USE_CLOSEST.next()) {
            if (objects[1].getPosition().distanceToDouble(objects[0]) < 3.0) {
                object_to_click = objects[1];
            }
        }
        abc.BOOL_TRACKER.USE_CLOSEST.reset();
        return object_to_click;
    }

    /**
     * Gets an object near you with the specified id. This object will either be the nearest, or second nearest depending on the tracker. A distance range of
     * 100 will be used as the default.
     *
     * @param id The id of the object you are looking to find.
     * @return An object with the specified id near you. Null if no objects were found.
     */
    public final RSObject getUseClosestObject(final int id) {
        return getUseClosestObject(id, 100);
    }

    /**
     * Gets an object near you with the specified name. This object will either be the nearest, or second nearest depending on the tracker. A distance range of
     * 100 will be used as the default.
     *
     * @param name The name of the object you are looking to find.
     * @return An object with the specified name near you. Null if no objects were found.
     */
    public final RSObject getUseClosestObject(final String name) {
        return getUseClosestObject(name, 100);
    }

    /**
     * Goes to the next anticipated resource. You must calculate which resource is the next anticipated resource yourself. Note that you may use any
     * Positionable object (e.g. rsnpc, rsplayer, rstile, rsobject etc.) as your next anticipated resource.
     *
     * @param anticipated The next anticipated resource.
     * @param random_offset The distance away from the object that can be randomized.
     * @return true if the player moved to the resource; false otherwise.
     */
    public final boolean goToAnticipated(Positionable anticipated, int random_offset) {
        if (anticipated != null && getABCUtil().BOOL_TRACKER.GO_TO_ANTICIPATED.next()) {
            if (Walking.blindWalkTo(new RSArea(anticipated, random_offset <= 0 ? 1 : random_offset).getRandomTile())) {
                abc.BOOL_TRACKER.GO_TO_ANTICIPATED.reset();
            }
        }
        return false;
    }

    /**
     * Checks to see if the player should switch resources. YOU MUST RESET THE TRACKER YOURSELF AFTER SWITCHING RESOURCES SUCCESSFULLY.
     *
     * @param player_count The amount of players gathering resources.
     * @param last_time_moved The last time you moved to a new resource.
     * @return true if your player should switch; false otherwise.
     */
    public final boolean shouldSwitchResources(int player_count, long last_time_moved) {
        double win_percent = ((double) (resources_won + resources_lost) / (double) resources_won);
        return win_percent < 50.0 && abc.SWITCH_TRACKER.TOO_MANY_PLAYERS.next(player_count);
    }

    /**
     * Checks all of the actions that should be done when idle. If any are ready, it will perform them.
     *
     * @param skill The skill to check the xp of. Null if no xp is being gained.
     * @return true if any actions were performed; false otherwise.
     */
    public final boolean timedActions(SKILLS skill) {
        return abc.performTimedActions(skill);
    }

    /**
     * @author Starfox
     */
    private static class ObjectUtil {

        private static Polygon getObjectArea(final RSObject object) {
            if (object == null) {
                return null;
            }
            final RSModel model = object.getModel();
            if (model != null) {
                return model.getEnclosedArea();
            }
            return null;
        }

        public static boolean isHovering(final RSObject object) {
            final Polygon area = getObjectArea(object);
            return area != null && area.contains(Mouse.getPos());
        }
    }
} 