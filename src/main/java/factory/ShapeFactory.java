package factory;

import button.UMLEditor;
import object.ButtonRole;
import object.ClassObject;
import object.UMLObject;
import object.UseCaseObject;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ShapeFactory implements ObjectFactory {
    private static UMLEditor umlEditor = UMLEditor.getUmlEditor();

    public ShapeFactory() {
    }

    @Override
    public UMLObject createShape(ButtonRole buttonRole, MouseEvent e) {
        UMLObject umlObject = null;
        if (umlEditor.getCurrentButtonRole().equals(ButtonRole.CLASS)) {
            umlObject = new ClassObject(e.getPoint());
        } else if (umlEditor.getCurrentButtonRole().equals(ButtonRole.USECASE)) {
            umlObject = new UseCaseObject(e.getPoint());
        }
        return umlObject;
    }
}
