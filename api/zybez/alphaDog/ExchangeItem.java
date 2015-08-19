package scripts.api.zybez.alphaDog;

import java.awt.image.BufferedImage;

public class ExchangeItem {
    public static final ExchangeItem NILL = new ExchangeItem(0, "Error", 0, 0, 0, 0, new Offer[0], null);

    private final int zybezId, highAlch;
    private final double recentHigh, recentLow, average;
    private final String name;
    private final Offer[] offers;
    private final BufferedImage image;

    public ExchangeItem(int zybezId, String name, double recentHigh, double recentLow, double average, int highAlch, Offer[] offers, BufferedImage image) {
        this.zybezId = zybezId;
        this.name = name;
        this.recentHigh = recentHigh;
        this.recentLow = recentLow;
        this.average = average;
        this.highAlch = highAlch;
        this.offers = offers;
        this.image = image;
    }

    //getters
    public int getZybezId() { return zybezId; }
    public int getHighAlch() { return highAlch; }
    public double getRecentHigh() { return recentHigh; }
    public double getRecentLow() { return recentLow;  }
    public double getAverage() { return average; }
    public String getName() { return name; }
    public Offer[] getOffers() { return offers; }
    public BufferedImage getImage() { return image; }
}
