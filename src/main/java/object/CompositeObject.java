package object;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class CompositeObject extends UMLObject{
    protected static ArrayList<HashSet<ShapeObject>> compositeShape = new ArrayList<>();

    public static ArrayList<HashSet<ShapeObject>> getCompositeShape() {
        return compositeShape;
    }

    public static void addCompositeShape(HashSet<ShapeObject> shapeObject) {
        CompositeObject.compositeShape.add(shapeObject);
    }

    @Override
    public void draw(Graphics g) {

    }
}