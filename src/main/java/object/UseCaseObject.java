package object;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class UseCaseObject extends ShapeObject {
    public UseCaseObject(Point e) {
        this.name = "use case";
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
        int textWidth = fm.stringWidth(name);
        int textHeight = fm.getHeight();
        int textLocX = (int) (point1.getX() + (width - textWidth) / 2);
        int textLocY = (int) (point1.getY() + (height + textHeight) / 2) - textHeight / 2;

        g2d.drawOval((int) point1.getX(), (int) point1.getY(), width, height);
        g2d.drawString(name, textLocX, textLocY);
    }

    @Override
    public boolean contains(Point e) {
        Ellipse2D ellipse = new Ellipse2D.Double(point1.getX(),point1.getY(),
                width, height);
        return ellipse.contains(e.getX(), e.getY());
    }

}
