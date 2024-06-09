package action;

import button.UMLEditor;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class ActionObject extends MouseInputAdapter {

    protected static UMLEditor umlEditor = UMLEditor.getUmlEditor();
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
}
