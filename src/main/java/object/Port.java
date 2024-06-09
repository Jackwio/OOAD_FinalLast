package object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Port {
    private double portLeftX;
    private double portLeftY;
    private double width = 10;
    private double height = 10;
    private Point center;

    //調用port的時候需要輸入在object的哪一面
    public Port(double portMiddleX, double portMiddleY) {
        center = new Point((int)portMiddleX,(int)portMiddleY);
        this.portLeftX = portMiddleX - width / 2;
        this.portLeftY = portMiddleY - height / 2;
    }
    public Port() {
    }

    public void drawPort(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g2.fill(new Rectangle2D.Double(portLeftX, portLeftY, width, height));
    }

    public double getPortLeftX() {
        return portLeftX;
    }

    public double getPortLeftY() {
        return portLeftY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Point getCenter() {
        return center;
    }
}
