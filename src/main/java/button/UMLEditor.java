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

    public void setCurrentButtonListener(EventListener currentButtonListener) {
        this.currentButtonListener = currentButtonListener;
    }

    // 設置當前和還原前一個按鈕的監聽器
    public void setButtonListener() {
        if (preButtonListener != null) {
            if (preButtonListener instanceof MouseListener) {
                removeMouseListener((MouseListener) preButtonListener);
            }
            if (preButtonListener instanceof MouseMotionListener) {
                removeMouseMotionListener((MouseMotionListener) preButtonListener);
            }
        }
        preButtonListener = currentButtonListener;
        if (currentButtonListener != null) {
            if (currentButtonListener instanceof MouseListener) {
                addMouseListener((MouseListener) currentButtonListener);
            }
            if (currentButtonListener instanceof MouseMotionListener) {
                addMouseMotionListener((MouseMotionListener) currentButtonListener);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (UMLObject shape : shapes) {
            shape.draw(g2);
        }
    }

    public List<UMLObject> getSelectShapes() {
        return selectShapes;
    }

    public void addSelectShapes(UMLObject umlObject) {
        this.selectShapes.add(umlObject);
    }

    public void resetIsSelected(Boolean isSelected) {
        for (UMLObject shape : shapes) {
            shape.setSelected(isSelected);
        }
    }

    public void setTopObject(UMLObject shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) == shape) {
                shapes.add(0, shapes.get(i));
                shapes.remove(i + 1);
            }
        }
    }
}

