package scripts.api.graphics;

import org.tribot.api.Timing;
import org.tribot.api2007.Skills;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class daxPaint {

    private String scriptName;
    private double version;
    private Skills.SKILLS skill;
    public int startXp;

    private final long START_TIME = System.currentTimeMillis();

    private final Color BLACK = new Color(0, 0, 0);
    private final Color DARK_GREY = new Color(0, 0, 0, 145);
    private final Color LIGHT_GREY = new Color(0, 0, 0, 30);
    private final Color RED = new Color(255, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);

    private final BasicStroke STROKE = new BasicStroke(1);

    private final Font REG_FONT = new Font("Century Gothic", 0, 14);
    private final Font ITALICS_FONT = new Font("Century Gothic", Font.ITALIC, 15);
    private final Font BIG_FONT = new Font("Century Gothic", 0, 18);

    private final RenderingHints ANTIALIAS = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public daxPaint(String scriptName, double version, Skills.SKILLS skill){
        this.scriptName = scriptName;
        this.version = version;
        this.skill = skill;
        startXp = skill.getXP();
    }

    public void draw(Graphics g1, String status, int profit) {

        Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(ANTIALIAS);

        //TIME
        long runTime = System.currentTimeMillis() - START_TIME;

        //CALCULATIONS
        int xpGained = Skills.getXP(skill) - startXp;
        int xpPerHour = (int) (xpGained / ( runTime/ 3600000D));
        int profitPerHour = (int) (profit/(runTime/3600000D));
        long ttl = (long) (Skills.getXPToNextLevel(skill) * 3600000D / xpPerHour);

        //LAYOUT
        g.setColor(DARK_GREY);
        g.fillRect(8, 458, 504, 17);
        g.setColor(BLACK);
        g.setStroke(STROKE);
        g.drawRect(8, 458, 504, 17);
        g.setColor(DARK_GREY);
        g.fillRect(206, 343, 290, 116);
        g.setColor(BLACK);
        g.drawRect(206, 343, 290, 116);
        g.setColor(LIGHT_GREY);
        g.fillRect(220, 427, 208, 17);
        g.setColor(BLACK);
        g.drawRect(220, 427, 208, 17);
        g.setColor(LIGHT_GREY);
        g.fillRect(220, 410, 180, 17);
        g.setColor(BLACK);
        g.drawRect(220, 410, 180, 17);
        g.setColor(LIGHT_GREY);
        g.fillRect(220, 393, 229, 17);
        g.setColor(BLACK);
        g.drawRect(220, 393, 229, 17);
        g.setColor(LIGHT_GREY);
        g.fillRect(220, 376, 141, 17);
        g.setColor(BLACK);
        g.drawRect(220, 376, 141, 17);
        g.setFont(REG_FONT);

        //LABELS
        g.setColor(RED);
        g.drawString("Status:", 228, 442);
        g.drawString("Profit:", 228, 424);
        g.drawString("Total Exp:", 228, 408);
        g.drawString("Timer:", 228, 391);

        //HEADER
        g.setFont(ITALICS_FONT);
        g.drawString("v" + version, 220 + scriptName.toCharArray().length*11, 369);
        g.setFont(BIG_FONT);
        g.setColor(WHITE);
        g.drawString(scriptName, 220, 369);

        //PROGRESS BAR
        int percentToLevel = Skills.getPercentToLevel(skill,skill.getCurrentLevel() + 1);
        int barWidth = (int) (percentToLevel * 5.04);
        g.setColor(new Color(0, 255, 0, 121));
        g.fillRect(8, 459, barWidth, 15);

        //INFORMATION
        g.setFont(REG_FONT);
        g.setColor(WHITE);
        g.drawString(status, 282, 442);
        g.drawString(NumberFormat.getNumberInstance(Locale.US).format(profit) + " (" +
                NumberFormat.getNumberInstance(Locale.US).format(profitPerHour) + "/hr)", 279, 424);
        g.drawString(NumberFormat.getNumberInstance(Locale.US).format(xpGained) + " (" +
                NumberFormat.getNumberInstance(Locale.US).format(xpPerHour) + "/hr)", 304, 408);
        g.drawString(Timing.msToString(runTime), 278, 391);
        g.drawString(percentToLevel + "% to " + (skill.getActualLevel() + 1) + " (" + Timing.msToString(ttl) + ")", 184, 472);
    }

    public void setAntiAlias(Graphics g1){
        Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(ANTIALIAS);
    }

    public static void drawCircle(Graphics2D g, int x, int y, int r, Color stroke, Color fill) {
        x = x-(r/2);
        y = y-(r/2);
        g.setColor(stroke);
        g.drawOval(x, y, r, r);
        g.setColor(fill);
        g.fillOval(x,y,r,r);
    }

}
