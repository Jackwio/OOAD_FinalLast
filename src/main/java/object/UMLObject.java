package object;

import java.awt.*;

public abstract class UMLObject {
//    protected String objectName;
    // 圖案是否被點擊

    // 圖=>左上和右下的點;線=>兩端的點
    protected Point point1;
    protected Point point2;

    protected Boolean isSelected = false;
    public abstract void draw(Graphics g);

    public Boolean getSelected() {
        return isSelected;
    }

    public void createShape(Point clickLocation) {

    }

    public void setPortLocation() {
    }

    public Port findClosestPort(Point clickPoint) {
        return new Port();
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public boolean contains(Point startPoint) {
        return false;
    }

//    public void setObjectName(String objectName) {
//        this.objectName = objectName;
//    }
}
