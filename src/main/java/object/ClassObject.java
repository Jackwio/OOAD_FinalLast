package object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassObject extends ShapeObject {

    public ClassObject(Point e) {
        this.name = "class";
        this.width = 130;
        this.height = 150;
        createShape(e);
        setPortLocation();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;

        double subRectHeight = height / 3.0;
        double subRect1Y = point1.getY();
        double subRect2Y = point1.getY() + subRectHeight;
        double subRect3Y = point1.getY() + 2 * subRectHeight;

        FontMetrics fm = g2d.getFontMetrics();
        double subRect1CenterY = subRect1Y + subRectHeight / 2.0;
        int textLocX = (int) (point1.getX() + (width - fm.stringWidth(name)) / 2);
        int textLocY = (int) (subRect1CenterY + fm.getHeight() / 4.0);
        g2d.setPaint(Color.WHITE);
        Rectangle2D.Double subRect1 = new Rectangle2D.Double(point1.getX(), subRect1Y, width, subRectHeight);
        g2d.draw(subRect1);

        Rectangle2D.Double subRect2 = new Rectangle2D.Double(point1.getX(), subRect2Y, width, subRectHeight);
        g2d.draw(subRect2);
        Rectangle2D.Double subRect3 = new Rectangle2D.Double(point1.getX(), subRect3Y, width, subRectHeight);
        g2d.draw(subRect3);
        g2d.draw(new Rectangle2D.Double(point1.getX(), point1.getY(), width, height));
        g2d.drawString(name, textLocX, textLocY);

    }

    @Override
    public Port findClosestPort(Point clickPoint) {
        Port closestPort = null;
        double minDistance = Double.MAX_VALUE;

        for (Port port : ports) {
            double distance = Math.sqrt(Math.pow(clickPoint.getX() - port.getPortLeftX(), 2) +
                    Math.pow(clickPoint.getY() - port.getPortLeftY(), 2));
            if (distance < minDistance) {
                minDistance = distance;
                closestPort = port;
            }
        }
        return closestPort;
    }

    @Override
    public boolean contains(Point startPoint) {
        return (startPoint.getX() >= point1.getX() && startPoint.getX() <= point1.getX() + width &&
                startPoint.getY() >= point1.getY() && startPoint.getY() <= point1.getY() + height);
    }
}
