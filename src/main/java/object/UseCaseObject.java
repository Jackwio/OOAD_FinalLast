package object;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UseCaseObject extends ShapeObject {
    public UseCaseObject(Point e) {
        this.objectName = "use case";
        this.width = 150;
        this.height = 80;
        createShape(e);
        setPortLocation();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(objectName);
        int textHeight = fm.getHeight();
        int textLocX = (int) (clickLocationX + (width - textWidth) / 2);
        int textLocY = (int) (clickLocationY + (height + textHeight) / 2) - textHeight / 2;

        g2d.drawOval((int) clickLocationX, (int) clickLocationY, width, height);
        g2d.drawString(objectName, textLocX, textLocY);
    }

    @Override
    public boolean contains(Point e) {
//        int circleCenterX = (int) (clickLocationX + width / 2.0);
//        int circleCenterY = (int) (clickLocationY + height / 2.0);
//        int circleRadius = Math.min(width, height) / 2;
        Ellipse2D ellipse = new Ellipse2D.Double(clickLocationX,clickLocationY,
                width, height);
        return ellipse.contains(e.getX(), e.getY());
    }

}
