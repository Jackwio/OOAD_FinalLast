package action;

import factory.LineFactory;
import factory.ObjectFactory;
import object.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineObjectAction extends ActionObject {

    // 當前正在畫的線
    private static UMLObject currentLineObject;
    // 紀錄線兩邊端點
    private Point startPoint , endPoint;

    private ObjectFactory factory = new LineFactory();

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("line設定好起始點，請開始拖移");
        startPoint = e.getPoint();
        // 創建當前正在畫的線物件
        currentLineObject = factory.createShape(umlEditor.getCurrentButtonRole(), e);

        // 確認起始點是否在圖案裡面
        for (UMLObject shape : umlEditor.getShapes()) {
            // 線起始點在圖形裡面 ， 線的contains皆為false
            if (shape.contains(startPoint)) {
                Port closestPort = shape.findClosestPort(startPoint);
                startPoint = closestPort.getCenter();
                currentLineObject.setPoint1(startPoint);
                currentLineObject.setStartObject(shape);
                umlEditor.addShape(currentLineObject);
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("正在畫線");
        endPoint = e.getPoint();
        currentLineObject.setPoint2(endPoint);
        umlEditor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int flag = 0;
//        Boolean isCanDraw = true;
        for (UMLObject shapeObject : umlEditor.getShapes()) {
            // composite物件不能畫線
//            for (HashSet<ShapeObject> compositeObjects : CompositeObject.getCompositeShape()) {
//                if (compositeObjects.contains(shapeObject)) {
//                    isCanDraw = false;
//                    break;
//                }
//            }
//            if (shapeObject.contains(endPoint) && isCanDraw) {
            if (shapeObject.contains(endPoint)) {
                // 若開頭和結尾的圖形都是同一個，刪除正在畫的該線
                if (currentLineObject.getStartObject() == shapeObject) {
                    umlEditor.getShapes().remove(umlEditor.getShapes().size() - 1);
                    umlEditor.repaint();
                    return;
                }
                Port closestPort = shapeObject.findClosestPort(endPoint);
                endPoint = closestPort.getCenter();
                currentLineObject.setPoint2(endPoint);
                currentLineObject.setEndObject(shapeObject);
                flag = 1;
                break;
            }
//            isCanDraw = true;
        }

        // 代表endPoint不再圖形內，所以要刪除之前按下press加入的line物件
        if (flag == 0) {
            umlEditor.getShapes().remove(umlEditor.getShapes().size() - 1);
        }
        System.out.println("結束畫線，此為結束位置");
        currentLineObject = null;
        umlEditor.repaint();
    }

}
