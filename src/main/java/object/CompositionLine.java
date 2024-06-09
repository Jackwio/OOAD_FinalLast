package object;

import java.awt.*;

public class CompositionLine extends LineObject {
    public CompositionLine() {
    }

    @Override
    public void draw(Graphics g) {
        // Draw the line
        g.setColor(Color.WHITE);
        int x1 = point1.x;
        int x2 = point2.x;
        int y1 = point1.y;
        int y2 = point2.y;
        g.drawLine(x1, y1, x2, y2);

        int dx = x2 - x1;
        int dy = y2 - y1;
        double point1_to_point2 = Math.sqrt((dx * dx) + (dy * dy));
        double cos = dx / point1_to_point2;
        double sin = dy / point1_to_point2;

        int pointA_x = (int) ((point1_to_point2 - 10) * cos - 10 * sin + x1);
        int pointA_y = (int) ((point1_to_point2 - 10) * sin + 10 * cos + y1);
        int pointB_x = (int) ((point1_to_point2 - 10) * cos + 10 * sin + x1);
        int pointB_y = (int) ((point1_to_point2 - 10) * sin - 10 * cos + y1);
        int pointC_x = (int) ((point1_to_point2 - 10 * 2) * cos + x1);
        int pointC_y = (int) ((point1_to_point2 - 10 * 2) * sin + y1);

        Polygon diamond = new Polygon();
        diamond.addPoint(pointA_x, pointA_y);
        diamond.addPoint(pointC_x, pointC_y);
        diamond.addPoint(pointB_x, pointB_y);
        diamond.addPoint(x2, y2);
        g.drawPolygon(diamond);
    }
}
