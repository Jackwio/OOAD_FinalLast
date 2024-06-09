package object;

import java.awt.*;

public class LineObject extends UMLObject {

    protected Point startPoint;
    protected Point endPoint;
    protected ShapeObject startShapeObject;

    protected ShapeObject endShapeObject;

    public LineObject() {
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public UMLObject getStartShapeObject() {
        return startShapeObject;
    }

    public void setStartShapeObject(ShapeObject startShapeObject) {
        this.startShapeObject = startShapeObject;
    }

    public UMLObject getEndShapeObject() {
        return endShapeObject;
    }

    public void setEndShapeObject(ShapeObject endShapeObject) {
        this.endShapeObject = endShapeObject;
    }

    @Override
    public void draw(Graphics g) {
    }


}
