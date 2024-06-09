package object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassObject extends ShapeObject {

    public ClassObject(Point e) {
        this.objectName = "class";
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
        double subRect1Y = clickLocationY;
        double subRect2Y = clickLocationY + subRectHeight;
        double subRect3Y = clickLocationY + 2 * subRectHeight;

        FontMetrics fm = g2d.getFontMetrics();
        double subRect1CenterY = subRect1Y + subRectHeight / 2.0;
        int textLocX = (int) (clickLocationX + (width - fm.stringWidth(objectName)) / 2);
        int textLocY = (int) (subRect1CenterY + fm.getHeight() / 4.0);
        g2d.setPaint(Color.WHITE);
        Rectangle2D.Double subRect1 = new Rectangle2D.Double(clickLocationX, subRect1Y, width, subRectHeight);
        g2d.draw(subRect1);

        Rectangle2D.Double subRect2 = new Rectangle2D.Double(clickLocationX, subRect2Y, width, subRectHeight);
        g2d.draw(subRect2);
        Rectangle2D.Double subRect3 = new Rectangle2D.Double(clickLocationX, subRect3Y, width, subRectHeight);
        g2d.draw(subRect3);
        g2d.draw(new Rectangle2D.Double(clickLocationX, clickLocationY, width, height));
        g2d.drawString(objectName, textLocX, textLocY);

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
        return (startPoint.getX() >= clickLocationX && startPoint.getX() <= clickLocationX + width &&
                startPoint.getY() >= clickLocationY && startPoint.getY() <= clickLocationY + height);
    }
}
