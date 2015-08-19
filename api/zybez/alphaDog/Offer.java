package scripts.api.zybez.alphaDog;

import java.util.Date;

public final class Offer {
    private final Type type;
    private final int quantity, price;
    private final String traderName, contactMethod, notes;
    private final Date date;

    public Offer(Type type, int quantity, int price, String traderName, String contactMethod, String notes, Date date) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.traderName = traderName;
        this.contactMethod = contactMethod;
        this.notes = notes;
        this.date = date;
    }

    //getters
    public Type getType() { return type; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price; }
    public String getTraderName() { return traderName; }
    public String getContactMethod() { return contactMethod; }
    public String getNotes() { return notes; }
    public Date getDate() { return new Date(date.getTime()); }

    public enum Type{ SELLING, BUYING }
}