package button;

import object.ButtonRole;
import object.LineObject;
import object.ShapeObject;
import object.UMLObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class UMLEditor extends JPanel {

    public UMLEditor() {
    }

    private static UMLEditor umlEditor;

    // 所有已畫出的圖形
    private List<UMLObject> shapes = new ArrayList<>();

    // 所有已畫出的線
    private List<LineObject> lines = new ArrayList<>();

    // 當前被點擊的圖形
    private UMLObject currentSelectedObject;

    private ButtonRole buttonRole;

    // 當前和前一個按鈕的監聽器
    private EventListener currentButtonListener;
    private EventListener preButtonListener;

    // 被選擇的圖形
    private List<UMLObject> selectShapes = new ArrayList<>();


    public static UMLEditor getUmlEditor() {
        return umlEditor = umlEditor == null ? new UMLEditor() : umlEditor;
    }

    public List<UMLObject> getShapes() {
        return shapes;
    }

    public void addShape(UMLObject umlObject) {
        this.shapes.add(umlObject);
    }

    public List<LineObject> getLines() {
        return lines;
    }

    public void addLine(LineObject line) {
        this.lines.add(line);
    }

    public UMLObject getCurrentSelectedObject() {
        return currentSelectedObject;
    }

    public void setCurrentSelectedObject(UMLObject umlObject) {
        this.currentSelectedObject = umlObject;
    }

    public ButtonRole getCurrentButtonRole() {
        return buttonRole;
    }

    public void setCurrentButtonRole(ButtonRole buttonRole) {
        this.buttonRole = buttonRole;
    }

//    public LineObject getCurrentLineObjectOfButton() {
//        return currentLineObjectOfButton;
//    }
//
//    public void setCurrentLineObjectOfButton(LineObject currentLineObjectOfButton) {
//        this.currentLineObjectOfButton = currentLineObjectOfButton;
//    }

    public void setCurrentButtonListener(EventListener currentButtonListener) {
        this.currentButtonListener = currentButtonListener;
    }

    // 設置當前和還原前一個按鈕的監聽器
    public void setButtonListener() {
        removeMouseListener((MouseListener) preButtonListener);
        removeMouseMotionListener((MouseMotionListener) preButtonListener);
        preButtonListener = currentButtonListener;
        addMouseListener((MouseListener) currentButtonListener);
        addMouseMotionListener((MouseMotionListener) currentButtonListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(g2);
        }
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).draw(g2);
        }

        g2.setPaint(new Color(155, 100, 123, 60));
//        drawRect(g2);
    }

    public List<UMLObject> getSelectShapes() {
        return selectShapes;
    }

    public void addSelectShapes(UMLObject umlObject) {
        this.selectShapes.add(umlObject);
    }

    public void resetIsSelected(Boolean isSelected){
        for (UMLObject umlObject : getSelectShapes()) {
            umlObject.setSelected(isSelected);
        }
    }
}
