package object;

import java.awt.*;

public class ShapeObject extends UMLObject {
    protected Integer width;
    protected Integer height;
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
        ports[0] = new Port(point1.getX(), point1.getY() + height / 2);
        ports[1] = new Port(point1.getX() + width / 2, point1.getY() + height);
        ports[2] = new Port(point1.getX() + width, point1.getY() + height / 2);
        ports[3] = new Port(point1.getX() + width / 2, point1.getY());
    }

    // 設定圖形位置
    public void createShape(Point clickLocation) {
        point1 = clickLocation;
        point2 = new Point(clickLocation.x + width, clickLocation.y + height);
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

    // 判斷圖形是否在起始點和中點之間
    @Override
    public boolean isCover(UMLObject shape, Point point1, Point point2) {
        double minX = Math.min(point1.getX(), point2.getX());
        double maxX = Math.max(point1.getX(), point2.getX());
        double minY = Math.min(point1.getY(), point2.getY());
        double maxY = Math.max(point1.getY(), point2.getY());
        double shapeX = shape.getPoint1().getX();
        double shapeY = shape.getPoint1().getY();
        double shapeWidth = width;
        double shapeHeight = height;
        if (shapeX >= minX && (shapeX + shapeWidth) <= maxX && shapeY >= minY && (shapeY + shapeHeight) <= maxY) {
            return true;
        }
        return false;
    }
}
