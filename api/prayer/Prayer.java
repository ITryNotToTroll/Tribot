package scripts.api.prayer;


import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSInterface;

import java.util.Arrays;
import java.util.List;

public class Prayer {

    public static double getPercent(){
        return ((double)Skills.getCurrentLevel(Skills.SKILLS.PRAYER)/(double)Skills.getActualLevel(Skills.SKILLS.PRAYER));
    }

    public static boolean activateQuickPrayers(){
        if (!isQuickPrayersOn()) {
            RSInterface rsInterface = Interfaces.get(548);
            RSInterface prayer;
            if (rsInterface != null && (prayer = rsInterface.getChild(86)) != null) {
                Clicking.click(prayer);
            }
        }
        return false;
    }

    public static boolean isQuickPrayersOn(){
        RSInterface rsInterface = Interfaces.get(548);
        RSInterface prayer;
        if (rsInterface != null && (prayer = rsInterface.getChild(86)) != null){
            List<String> list = Arrays.asList(prayer.getActions());
            return !list.contains("Activate");
        }
        return false;
    }

}
