package scripts.api.banking;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;

/**
 * @author Worthy
 */
public class BankHelper {

    private static final int BANKING_INTERFACE = 12;
    private static final int SELECTED_TEXTURE = 813;
    
    public enum Widgets {
        SWAP(16, "Swap"), INSERT(18, "Insert"), ITEM(21, "Item"), NOTE(24, "Note");
        
        String name;
        int index;
        private Widgets(final int index, final String name) {
            this.index = index;
            this.name = name;
        }
    }
    
    /**
     * @param widj The widget to check (swap/insert/item/note)
     * @return true if the widget is selected (in red)
     */
    public static boolean isSelected(final Widgets widg) {
        if (!Banking.isBankScreenOpen() || Interfaces.get(BANKING_INTERFACE) == null) return false;
        final RSInterfaceChild itemWidget = Interfaces.get(BANKING_INTERFACE, widg.index);
        if (itemWidget != null) {
            return itemWidget.getTextureID() == SELECTED_TEXTURE;
        }
        return false;
    }
    
    /**
     * @param widg The widget to enable
     * @return true if the widget was successfully selected
     */
    public static boolean select(final Widgets widg) {
        if (!Banking.isBankScreenOpen() || Interfaces.get(BANKING_INTERFACE) == null) return false;
        if (isSelected(widg)) return false;
        final RSInterfaceChild itemWidget = Interfaces.get(BANKING_INTERFACE, widg.index);
        if (itemWidget != null) {
            if (itemWidget.click(widg.name)) {
                return (Timing.waitCondition(new Condition() {                
                    @Override
                    public boolean active() {
                        return isSelected(widg);
                    }
                }, 4000));
            }
        }
        return false;
    }
}
