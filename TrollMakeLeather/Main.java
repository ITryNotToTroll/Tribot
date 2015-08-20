package scripts.TrollMakeLeather;

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
import org.tribot.api2007.Login;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.TrollMakeLeather.Variables;


@ScriptManifest(authors = { "ITryNotToTroll" }, category = "Magic", name = "Troll Tan Leather")
public class Main extends Script implements Painting{

	private final Color DARK_GREY = new Color(0, 0, 0, 150);
	private final Color BLACK = new Color(0, 0, 0);
	private final Color LIGHT_GREY = new Color(0, 0, 0, 38);
	private final Color RED = new Color(239, 0, 0);
	private final Color WHITE = new Color(255, 255, 255);
	private final Color GREEN = new Color(0, 205, 8, 50);

	private final BasicStroke STROKE = new BasicStroke(1);

	private final Font FONT1 = new Font("Century Gothic", 2, 14);
	private final Font FONT2 = new Font("Century Gothic", 0, 13);
	private final Font SCRIPT_NAME_FONT = new Font("Century Gothic", 0,
			18);	
	
	@Override
	public void run() {
		
		GUI gui = new GUI();
		
		gui.setVisible(true);
		
		while(gui.isVisible())
			sleep(20);
		
		General.println("You have selected " + Variables.leather.toString());
		
		while(Login.getLoginState() != Login.STATE.INGAME)
			sleep(20);
		
		Variables.startTime = System.currentTimeMillis();
		Variables.startXP = Skills.getXP(SKILLS.MAGIC);
		Variables.hidePrice = getPrice(Variables.leather.getID());
		Variables.leatherPrice = getPrice(Variables.leather.getTannedID());
		Variables.costs = (int) (getPrice(Constants.RUNES[0]) * 2 + getPrice(Constants.RUNES[1]) / 5);
		
		while(Variables.running) {
			
			MakeLeather.run();
			sleep(20, 30);
			
		}
		
	}

	
	@Override
	public void onPaint(Graphics g) {
		long timeRan = System.currentTimeMillis() - Variables.startTime;

		int itemsHr = (int) (Variables.itemsMade / (timeRan / 3600000D));

		int profitPer = Variables.leatherPrice - Variables.hidePrice - Variables.costs;
		
		int profit = profitPer * Variables.itemsMade;

		int profitHr = (int) (profit / (timeRan / 3600000D));

		int xpGained = Skills.getXP(SKILLS.MAGIC) - Variables.startXP;

		int xpHr = (int) (xpGained / (timeRan / 3600000D));

		g.setFont(FONT1);
		g.setColor(Color.BLUE);
		g.drawString("Profit:", 313, 448);
		g.drawString("Xp Gained:", 313, 433);
		g.drawString("Leather Made:", 313, 418);
		g.drawString("Ran For:", 313, 403);

		g.setFont(FONT1);
		g.setColor(WHITE);
		g.drawString("Troll Tanner", 313, 388);

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
	

	public static int getPrice(final int itemId) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + itemId).openStream()))) {
        	Matcher matcher = Pattern.compile(".*\"price\":\"?(\\d+\\.?\\d*)([k|m]?)\"?},\"today\".*").matcher(reader.readLine());
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
	
}
