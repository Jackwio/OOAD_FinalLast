package object;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class CompositeObject extends UMLObject{
    protected static ArrayList<HashSet<UMLObject>> compositeShape = new ArrayList<>();

    public static ArrayList<HashSet<UMLObject>> getCompositeShape() {
        return compositeShape;
    }

    public static void addCompositeShape(HashSet<UMLObject> shapeObject) {
        CompositeObject.compositeShape.add(shapeObject);
    }

    @Override
    public void draw(Graphics g) {

    }

}