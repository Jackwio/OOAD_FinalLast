package object;

import java.awt.*;

public class GeneralizationLine extends LineObject{
    public GeneralizationLine() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        int x1 = point1.x;
        int x2 = point2.x;
        int y1 = point1.y;
        int y2 = point2.y;

        g2d.drawLine(x1, y1, x2, y2);

        int arrowSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
        int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));

        int[] xPoints = {x2, x3, x4};
        int[] yPoints = {y2, y3, y4};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}
