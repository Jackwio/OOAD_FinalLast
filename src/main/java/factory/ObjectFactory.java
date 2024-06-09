package factory;

import object.ButtonRole;
import object.UMLObject;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface ObjectFactory {
    UMLObject createShape(ButtonRole buttonRole, MouseEvent e);
}
