package action;

import factory.ObjectFactory;
import factory.ShapeFactory;
import object.*;
import java.awt.event.MouseEvent;

public class ShapeObjectAction extends ActionObject {

    private ObjectFactory factory = new ShapeFactory();

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("圖案畫出");
        // 產生圖形物件
        UMLObject shape = factory.createShape(umlEditor.getCurrentButtonRole(), e);
        // 加入當前圖形
        umlEditor.addShape(shape);
        umlEditor.repaint();
    }

}
