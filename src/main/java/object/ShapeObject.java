package object;

import java.awt.*;

public class ShapeObject extends UMLObject {
    protected String objectName;
    protected Integer width;
    protected Integer height;

    // 點擊位置(左上角)開始畫出圖形的位置
    protected double clickLocationX;
    protected double clickLocationY;

    // 點擊位置(左上角)開始畫出圖形的位置
    protected double objectCenterX;
    protected double objectCenterY;
    protected static int portsNum = 4;
    // 圖形的四個port
    protected Port[] ports = new Port[portsNum];

    @Override
    public void draw(Graphics g) {
        if (isSelected == true) {
            for (Port port : ports) {
                port.drawPort(g);
            }
        }
    }

    // 初始化4邊port
    public void setPortLocation() {
        //0: left, 1: bottom, 2: right, 3: top
        ports[0] = new Port(clickLocationX, clickLocationY + height / 2);
        ports[1] = new Port(clickLocationX + width / 2, clickLocationY + height);
        ports[2] = new Port(clickLocationX + width, clickLocationY + height / 2);
        ports[3] = new Port(clickLocationX + width / 2, clickLocationY);
    }

    // 設定圖形位置
    public void createShape(Point clickLocation) {
        this.clickLocationX = clickLocation.getX();
        this.clickLocationY = clickLocation.getY();
    }


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

    public double getClickLocationX() {
        return clickLocationX;
    }

    public double getClickLocationY() {
        return clickLocationY;
    }

    public void setClickLocationX(double clickLocationX) {
        this.clickLocationX = clickLocationX;
    }

    public void setClickLocationY(double clickLocationY) {
        this.clickLocationY = clickLocationY;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public double getObjectCenterX() {
        this.objectCenterX = getClickLocationX() + width / 2;
        return objectCenterX;
    }

    public double getObjectCenterY() {
        this.objectCenterY = getClickLocationY() + height / 2;
        return objectCenterY;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
