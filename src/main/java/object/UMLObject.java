package object;

import java.awt.*;

public abstract class UMLObject {

    // 圖=>左上和右下的點;線=>兩端的點
    protected Point point1;
    protected Point point2;
    // 線=>兩端的圖形物件
    protected UMLObject startObject;
    protected UMLObject endObject;
    protected Boolean isSelected = false;
    protected String name;

    public abstract void draw(Graphics g);
    public void createShape(Point clickLocation){};

    public void setPortLocation() {}
    public Port findClosestPort(Point clickPoint) {
        return new Port();
    }
    public boolean contains(Point startPoint) {
        return false;
    }
    public boolean isCover(UMLObject shape, Point point1, Point point2){return false;}

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
    public Point getPoint1() {
        return point1;
    }
    public void setPoint1(Point point1) {
        this.point1 = point1;
    }
    public Point getPoint2() {
        return point2;
    }
    public void setPoint2(Point point2) {
        this.point2 = point2;
    }
    public UMLObject getStartObject() {
        return startObject;
    }
    public void setStartObject(UMLObject startObject) {
        this.startObject = startObject;
    }
    public UMLObject getEndObject() {
        return endObject;
    }
    public void setEndObject(UMLObject endObject) {
        this.endObject = endObject;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
