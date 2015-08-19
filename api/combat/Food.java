package scripts.api.combat;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

public class Food {

    public static final int[] FOOD_LIST = {1969,6962, 6965, 1973, 1881, 1883, 1971, 4608, 2142, 4293, 315, 2140, 319, 2142, 1891, 1893, 1895, 1897, 1899, 1901,
            347, 333, 329, 325, 351, 361, 365, 379, 373, 7944, 385, 7060, 391, 397, 6883};


    public static boolean haveFood(){
        return Inventory.find(FOOD_LIST).length > 0;
    }

    public static boolean eatFood(){
        RSItem[] food = Inventory.find(FOOD_LIST);
        final int HEALTH = Combat.getHP();
        if (food.length > 0 && food[0] != null){
            if (Clicking.click("Eat", food[0])){
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Combat.getHP() > HEALTH;
                    }
                },2000);
            }
        }
        return false;
    }

}
