package scripts.api.zybez.alphaDog;

import org.tribot.api2007.types.RSItemDefinition;

import scripts.api.json.JsonArray;
import scripts.api.json.JsonObject;
import scripts.api.json.JsonValue;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public final class ExchangeParser {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
    private static final String ZYBEZ_URL = "http://forums.zybez.net/runescape-2007-prices/api/item/";
    private static final HashMap<String, ExchangeItem> CACHE = new HashMap<String, ExchangeItem>();

    //prevent instantiation
    private ExchangeParser() {
    }

    public static ExchangeItem lookup(String itemName) {
        return lookup(itemName, false);
    }

    public static ExchangeItem lookup(int itemId) {
        return lookup(itemId, false);
    }

    public static ExchangeItem lookup(int itemId, boolean reload) {
        RSItemDefinition itemDef = RSItemDefinition.get(itemId);
        return itemDef == null ? ExchangeItem.NILL : lookup(itemDef.getName(), reload);
    }

    public static ExchangeItem lookup(String itemName, boolean reload) {
        ExchangeItem cachedItem = CACHE.get(itemName.toLowerCase());
        if (cachedItem != null) {
            if (reload) CACHE.remove(itemName.toLowerCase());
            else return cachedItem;
        }
        return parseItem(getJSON(ZYBEZ_URL + itemName.replaceAll(" ", "+").replaceAll("'", "_")));
    }

    private static ExchangeItem parseItem(String json) {
        try {
            //parse json object
            JsonObject object = JsonObject.readFrom(json);

            //parse all offers
            ArrayList<Offer> offers = new ArrayList<Offer>();
            JsonArray offerObjects = object.get("offers").asArray();
            for (JsonValue offerArray : offerObjects) {
                JsonObject offerObject = offerArray.asObject();
                offers.add(new Offer(
                        offerObject.get("selling").asInt() == 0 ? Offer.Type.SELLING : Offer.Type.BUYING,
                        offerObject.get("quantity").asInt(),
                        offerObject.get("price").asInt(),
                        offerObject.get("rs_name").asString(),
                        offerObject.get("contact").asString(),
                        offerObject.get("notes").asString(), new Date(offerObject.get("date").asInt() * 1000l)));
            }

            //parse all other attributes and create a new ExchangeItem object
            ExchangeItem exchangeItem = new ExchangeItem(object.get("id").asInt(), object.get("name").asString(),
                    object.get("recent_high").asDouble(), object.get("recent_low").asDouble(), object.get("average").asDouble(),
                    object.get("high_alch").asInt(), offers.toArray(new Offer[offers.size()]), loadImage(object.get("image").asString()));
            CACHE.put(exchangeItem.getName().toLowerCase(), exchangeItem);
            return exchangeItem;
        } catch (Exception e) {
            System.err.println("Failed to parse item");
            return ExchangeItem.NILL;
        }
    }

    private static BufferedImage loadImage(String url) {
        try {
            url = url.replaceAll(" ", "%20");
            URLConnection cn = new URL(url).openConnection();
            cn.setRequestProperty("User-Agent", USER_AGENT);
            BufferedInputStream in = new BufferedInputStream(cn.getInputStream());
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getJSON(String url) {
        try {
            URLConnection cn = new URL(url).openConnection();
            cn.setRequestProperty("User-Agent", USER_AGENT);
            InputStreamReader in = new InputStreamReader(cn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
