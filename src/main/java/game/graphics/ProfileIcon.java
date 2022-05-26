package game.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ProfileIcon extends JComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    private Font textFont;
    private String profileName;

    public ProfileIcon(int x, int y, int w, int h, String profileName) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.textFont = new Font("Sans-serif", Font.PLAIN, 50);
        this.profileName = profileName;
    }

    public ProfileIcon(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width+50, height+50);
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(getParent() != null) {
            Graphics2D graphics2D = (Graphics2D) g;
            //Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);

            graphics2D.setPaint(Color.BLACK);
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.drawOval(x, y, width, height);
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillOval(x, y, width, height);

            //draw character on profile pic
            /*
            graphics2D.setColor(Color.BLUE);
            graphics2D.setFont(textFont);
            graphics2D.drawString(profileName, x+30, y+65);

             */
        }
    }
}
