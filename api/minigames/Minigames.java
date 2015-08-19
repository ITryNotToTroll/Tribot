package scripts.api.minigames;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSInterfaceMaster;
import org.tribot.api2007.types.RSTile;

import java.awt.*;

public class Minigames {

    //IDs
    public static int mainInterfaceID = 76,
            clanInterfaceID = 589;

    //ENUMS
    public static enum MINIGAMES {
        BARBARIAN_ASSAULT ("barbarian"),
        BLAST_FURNACE ("blast"),
        CASTLE_WARS ("castle"),
        FISHING_TRAWLER ("fishing"),
        BURTHORPE_GAMES_ROOM ("burthorpe"),
        NIGHTMARE_ZONE ("nightmare"),
        PEST_CONTROL ("pest"),
        RAT_PITS ("pits"),
        SHADES_OF_MORTTON ("shades"),
        TROUBLE_BREWING ("trouble"),
        TZHAAR_FIGHT_PIT ("tzhaar"),
        SHIELD_OF_ARRAV ("shield"),
        CLAN_WARS ("clan"),
        GOD_WARS ("god war"),
        DAGANNOTH_KINGS ("dagannoth"),
        PLAYER_OWNED_HOUSES ("player");

        private String name;

        MINIGAMES(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }

    }

    //METHODS
    public static boolean selectMinigame(MINIGAMES name){ //Selects Desired Minigame. Returns false if failed to select.
        if (!isMinigameTabOpen()){                        //Can be used to check if desired Minigame is already selected.
            openMinigameTab();
        }
        return selectMinigame(name.getName());
    }

    public static boolean isMinigameTabOpen(){ //Checks if Minigame Tab is open. Returns false if not.
        RSInterfaceMaster main = Interfaces.get(mainInterfaceID);
        RSInterfaceChild test;
        return main != null && (test = main.getChild(0)) != null && !test.isHidden();
    }

    public static boolean inMinigameChat(){ //CHECKS IF YOU ARE IN A MINIGAME CHAT. Returns false if not.
        RSInterfaceMaster clan = Interfaces.get(clanInterfaceID);
        if (clan != null){
            for (RSInterfaceChild x : clan.getChildren()){
                if (x != null && x.getText().contains("Osrs")){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean openMinigameTab(){ //Opens the Minigame tab inside the Quests tab. Returns false if failed to open.
        if (GameTab.getOpen() != GameTab.TABS.QUESTS){
            Mouse.click(General.random(600,620),General.random(170,195),1);
        }

        RSInterfaceMaster quest = Interfaces.get(274);
        RSInterfaceMaster diary = Interfaces.get(259);

        if (quest != null){
            RSInterfaceChild button = quest.getChild(12);
            Clicking.click(button);
            General.sleep(400,900);
            return isMinigameTabOpen();
        }
        else if (diary != null){
            RSInterfaceChild button = diary.getChild(11);
            Clicking.click(button);
            General.sleep(400, 900);
            return isMinigameTabOpen();
        }

        return false;

    }

    public static boolean joinChat(){ //Joins the chat of selected minigame. Returns false if failed to join.

        if (!isMinigameTabOpen()){
            openMinigameTab();
        }

        RSInterfaceMaster mini = Interfaces.get(mainInterfaceID);

        if (mini!= null){
            RSInterfaceChild button = mini.getChild(26);
            String text;
            if (button != null){
                if ((text = button.getText()) != null && text.contains("Leave")){
                    return true;
                }
                Clicking.click(button);
                return (Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(350);
                        return inMinigameChat();
                    }
                },3000));
            }
        }
        return false;
    }

    public static boolean teleport(){ //Teleports with selected minigame. Returns false if Teleport failed.

        if (!isMinigameTabOpen()){
            openMinigameTab();
        }

        RSInterfaceMaster mini = Interfaces.get(mainInterfaceID);

        if (mini!= null){

            RSInterfaceChild button = mini.getChild(29);

            if (button != null){

                final RSTile TILE = Player.getPosition();
                Clicking.click(button);
                if (Timing.waitCondition( new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(350);
                        return Player.getAnimation() == 4847;
                    }
                },4000))
                    return Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(350);
                            return Player.getPosition().distanceTo(TILE) > 10;
                        }
                    },20000);
            }
        }
        return false;
    }

    private static boolean isSelected(String name){

        final RSInterfaceMaster mini = Interfaces.get(mainInterfaceID);
        RSInterfaceChild child;
        String minigame;

        return(mini != null && (child = mini.getChild(9)) != null
                && (minigame = child.getText()) != null && minigame.toLowerCase().contains(name.toLowerCase()));
    }

    private static boolean selectMinigame(String name){

        final RSInterfaceMaster MINI = Interfaces.get(mainInterfaceID);
        RSInterfaceChild child;

        if (isSelected(name)){
            return true;
        } else {
            if (MINI != null && (child = MINI.getChild(9)) != null){

                Clicking.click(child);

                if (Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(350);
                        RSInterfaceChild minigamesBox = MINI.getChild(19);
                        return minigamesBox != null && minigamesBox.getChildren() != null;
                    }
                },2000)) {

                    RSInterfaceChild minigamesBox = MINI.getChild(19);
                    if (minigamesBox != null) {

                        RSInterfaceComponent[] comp = minigamesBox.getChildren();
                        RSInterfaceComponent ourMinigame = null;

                        for (RSInterfaceComponent x : comp) {

                            String s;

                            if (x != null && (s = x.getText()) != null && s.toLowerCase().contains(name.toLowerCase())) {
                                ourMinigame = x;
                            }

                        }

                        Rectangle rec = minigamesBox.getAbsoluteBounds();
                        Rectangle miniRec = ourMinigame != null ? ourMinigame.getAbsoluteBounds() : null;

                        if (miniRec != null) {

                            if (!rec.contains(miniRec)) {

                                Rectangle scroll = Interfaces.get(mainInterfaceID).getChild(14).getChild(0).getAbsoluteBounds();
                                Point randomized = new Point((int) scroll.getX() + General.random(1, scroll.width - 1),
                                        (int) scroll.getY() + General.random(1, scroll.height - 1));
                                Point target = new Point(scroll.x + General.random(-5, 5), miniRec.y + General.random(-5, 5));
                                Mouse.drag(randomized, target, 1);

                            }

                            Clicking.click(ourMinigame);

                            return isSelected(name);

                        }
                    }

                }
            }
        }

        return false;

    }

}
