package scripts.TrollWines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

@ScriptManifest(authors = { "ITryNotToTroll" }, category = "Cooking", name = "Troll Wines")
public class Main extends Script implements Painting, MessageListening07 {

	private final Color DARK_GREY = new Color(0, 0, 0, 150);
	private final Color BLACK = new Color(0, 0, 0);
	private final Color LIGHT_GREY = new Color(0, 0, 0, 38);
	private final Color RED = new Color(239, 0, 0);
	private final Color WHITE = new Color(255, 255, 255);
	private final Color GREEN = new Color(0, 205, 8, 50);

	private final BasicStroke STROKE = new BasicStroke(1);

	private final Font FONT1 = new Font("Century Gothic", 2, 14);
	private final Font FONT2 = new Font("Century Gothic", 0, 13);
	private final Font SCRIPT_NAME_FONT = new Font("Century Gothic", 0, 18);

	@Override
	public void run() {
		Mouse.setSpeed(General.random(100, 104));

		General.useAntiBanCompliance(true);
		ThreadSettings.get().setClickingAPIUseDynamic(true);

		Items.GRAPES.setPrice(getPrice(Items.GRAPES.getId()));
		Items.JUG.setPrice(getPrice(Items.JUG.getId()));
		Items.WINE.setPrice(getPrice(Items.WINE.getId()));

		Variables.profitPer = Items.WINE.getPrice() - Items.GRAPES.getPrice() - Items.JUG.getPrice();

		Variables.startTime = Timing.currentTimeMillis();

		Variables.startXP = Skills.getXP(SKILLS.COOKING);

		while (Variables.running) {

			Combine.run();

			General.sleep(20, 30);
		}
	}

	public static int getPrice(final int itemId) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + itemId).openStream()))) {
        	Matcher matcher = Pattern.compile(".*\"price\":\"?(\\d+\\,?\\.?\\d*)([k|m]?)\"?},\"today\".*").matcher(reader.readLine());
            if (matcher.matches()) {
                double price = Double.parseDouble(matcher.group(1).replace(",",""));
                String suffix = matcher.group(2);
                return (int) (suffix.isEmpty() ? price : price * (suffix.charAt(0) == 'k' ? 1000 : 1000000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

	@Override
	public void onPaint(Graphics g) {
		long timeRan = System.currentTimeMillis() - Variables.startTime;

		int itemsHr = (int) (Variables.itemsMade / (timeRan / 3600000D));

		int profit = Variables.profitPer * Variables.itemsMade;

		int profitHr = (int) (profit / (timeRan / 3600000D));

		int xpGained = Skills.getXP(SKILLS.COOKING) - Variables.startXP;

		int xpHr = (int) (xpGained / (timeRan / 3600000D));

		g.setFont(FONT1);
		g.setColor(Color.BLUE);
		g.drawString("Profit:", 313, 448);
		g.drawString("Xp Gained:", 313, 433);
		g.drawString("Wines Made:", 313, 418);
		g.drawString("Ran For:", 313, 403);

		g.setFont(FONT1);
		g.setColor(WHITE);
		g.drawString("Troll Wines", 313, 388);

		g.setFont(FONT2);
		g.setColor(WHITE);

		g.drawString(String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(timeRan),
				TimeUnit.MILLISECONDS.toMinutes(timeRan)
						% TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(timeRan)
						% TimeUnit.MINUTES.toSeconds(1)), 375, 403);

		g.drawString(Variables.itemsMade + " (" + itemsHr + "/hr)", 404, 418);

		g.drawString(NumberFormat.getNumberInstance(Locale.US).format(xpGained)
				+ " (" + NumberFormat.getNumberInstance(Locale.US).format(xpHr)
				+ "/hr)", 394, 433);

		g.drawString(
				NumberFormat.getNumberInstance(Locale.US).format(profit)
						+ " ("
						+ NumberFormat.getNumberInstance(Locale.US).format(
								profitHr) + "/hr)", 355, 448);

	}

	@Override
	public void serverMessageReceived(String msg) {
		if (msg.contains("You squeeze the grapes into the jug"))
			Variables.itemsMade++;

	}

	// Unused
	@Override
	public void clanMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {
	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
	}

	@Override
	public void tradeRequestReceived(String arg0) {
	}

}
