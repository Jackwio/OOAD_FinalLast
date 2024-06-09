package action;

import object.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineObjectAction extends ActionObject {

    // 當前正在畫的線
    private static LineObject currentLineObject;

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("line設定好起始點，請開始拖移");
        Point startPoint = e.getPoint();

        if (umlEditor.getCurrentButtonRole().equals(ButtonRole.GENERALIZATION_LINE)) {
            currentLineObject = new GeneralizationLine();
        } else if (umlEditor.getCurrentButtonRole().equals(ButtonRole.ASSOCIATION_LINE)) {
            currentLineObject = new AssociationLine();
        } else if (umlEditor.getCurrentButtonRole().equals(ButtonRole.COMPOSITION_LINE)) {
            currentLineObject = new CompositionLine();
        }

//        for (ShapeObject shape : umlEditor.getShapes()) {
//            // 線起始點在圖形裡面
//            if (shape.contains(startPoint)) {
//                Port closestPort = shape.findClosestPort(startPoint);
//                startPoint = closestPort.getCenter();
//                currentLineObject.setStartPoint(startPoint);
//                currentLineObject.setStartShapeObject(shape);
//                umlEditor.addLine(currentLineObject);
//                break;
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int flag = 0;
        Point endPoint = e.getPoint();
//        Boolean isCanDraw = true;
//        for (ShapeObject shapeObject : umlEditor.getShapes()) {
//            // composite物件不能畫線
////            for (HashSet<ShapeObject> compositeObjects : CompositeObject.getCompositeShape()) {
////                if (compositeObjects.contains(shapeObject)) {
////                    isCanDraw = false;
////                    break;
////                }
////            }
////            if (shapeObject.contains(endPoint) && isCanDraw) {
//            if (shapeObject.contains(endPoint)) {
//                // 若開頭和結尾的圖形都是同一個，刪除正在畫的該線
//                if (currentLineObject.getStartShapeObject() == shapeObject) {
//                    umlEditor.getLines().remove(umlEditor.getLines().size() - 1);
//                    umlEditor.repaint();
//                    return;
//                }
//                Port closestPort = shapeObject.findClosestPort(endPoint);
//                endPoint = closestPort.getCenter();
//                currentLineObject.setEndPoint(endPoint);
//                currentLineObject.setEndShapeObject(shapeObject);
//                flag = 1;
//                break;
//            }
////            isCanDraw = true;
//        }
//
//        // 代表endPoint不再圖形內，所以要刪除之前按下press加入的line物件
//        if (flag == 0) {
//            umlEditor.getLines().remove(umlEditor.getLines().size() - 1);
//        }
//        System.out.println("結束畫線，此為結束位置");
//        currentLineObject = null;
//        umlEditor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("正在畫線");
        Point endPoint = e.getPoint();
        LineObject lineObject = umlEditor.getLines().get(umlEditor.getLines().size() - 1);
        lineObject.setEndPoint(endPoint);
        umlEditor.repaint();
    }
}
