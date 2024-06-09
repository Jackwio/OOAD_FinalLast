package action;

import object.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SelectAction extends ActionObject {

    private Point startClickPoint;
    private boolean isClickOnShape = false;

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("press觸發");
        double clickX = e.getX();
        double clickY = e.getY();
        startClickPoint = e.getPoint();

        for (UMLObject shape : umlEditor.getShapes()) {
            if (shape.contains(e.getPoint())) {
                isClickOnShape = true;
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("select mode");
        startClickPoint = e.getPoint();
        umlEditor.resetIsSelected(false);
        umlEditor.getSelectShapes().clear();

//        umlEditor.getCurrentSelectedObject().setSelected(true);

        int flag = 0;
        List<UMLObject> shapes = umlEditor.getShapes();
        // 取消原本被選擇的
        if (umlEditor.getCurrentSelectedObject() != null) {
            umlEditor.getCurrentSelectedObject().setSelected(false);
            umlEditor.setCurrentSelectedObject(null);
        }

        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).contains(e.getPoint())) {
                umlEditor.setCurrentSelectedObject(shapes.get(i));
                shapes.get(i).setSelected(true);
//                umlEditor.setTopObject(shapes.get(i));
                flag = 1;
                break;
            }
        }


//        ArrayList<HashSet<UMLObject>> compositeShape = CompositeObject.getCompositeShape();
//        for (int i = compositeShape.size() - 1; i >= 0; i--) {
//            if (compositeShape.get(i).contains(umlEditor.getCurrentSelectedObject())) {
//                umlEditor.getSelectShapes().addAll(compositeShape.get(i));
//                break;
//            }
//        }
//
//        if (umlEditor.getCurrentSelectedObject() != null && flag == 0) {
//            umlEditor.getCurrentSelectedObject().setSelected(false);
////            umlEditor.setCurrentSelectedObject(null);
////            umlEditor.setSelectShapes(null);
//        }
//        isClickOnShape = umlEditor.getCurrentSelectedObject() == null ? false : true;
//        umlEditor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (umlEditor.getCurrentSelectedObject() == null) {
            isClickOnShape = false;
            System.out.println("-----------------------------------------------------");
            umlEditor.repaint();
            return;
        }
        System.out.println("select mode dragging");

        double clickX = e.getX();
        double clickY = e.getY();
        double offsetX;
        double offsetY;
//        offsetX = clickX - umlEditor.getCurrentSelectedObject().getObjectCenterX();
//        offsetY = clickY - umlEditor.getCurrentSelectedObject().getObjectCenterY();
//
//        if (umlEditor.getSelectShapes().size() == 0) {
//            // 設定dragging，都是被選取狀態
//            umlEditor.getCurrentSelectedObject().setSelected(true);
//            // 更新物件位置
//            umlEditor.getCurrentSelectedObject().setClickLocationX(umlEditor.getCurrentSelectedObject().getClickLocationX() + offsetX);
//            umlEditor.getCurrentSelectedObject().setClickLocationY(umlEditor.getCurrentSelectedObject().getClickLocationY() + offsetY);
////        umlEditor.getCurrentSelectedObject().createShape(e.getPoint());
//            // 更新4個port位置
//            umlEditor.getCurrentSelectedObject().setPortLocation();
////        umlEditor.setTopObject(umlEditor.getCurrentSelectedObject());
//            // 關於該圖的線全部更新
//            List<LineObject> lines = umlEditor.getLines();
//            // 為了不要讓重複的線動到兩次
//            List<Point> points = new ArrayList<>();
//
//            for (int i = 0; i < lines.size(); i++) {
//                LineObject lineObject = lines.get(i);
//                if (lineObject.getStartShapeObject() == umlEditor.getCurrentSelectedObject() && !points.contains(lineObject.getStartPoint())) {
//                    Point startPoint = lineObject.getStartPoint();
//                    startPoint.setLocation(startPoint.getX() + offsetX, startPoint.getY() + offsetY);
//                    points.add(startPoint);
//                } else if (lineObject.getEndShapeObject() == umlEditor.getCurrentSelectedObject() && !points.contains(lineObject.getEndPoint())) {
//                    Point endPoint = lineObject.getEndPoint();
//                    endPoint.setLocation(endPoint.getX() + offsetX, endPoint.getY() + offsetY);
//                    points.add(endPoint);
//                }
//            }
//        } else {
//            for (UMLObject selectShape : umlEditor.getSelectShapes()) {
//                selectShape.setSelected(false);
//                Point point = new Point();
//                point.setLocation(offsetX + selectShape.getClickLocationX(), offsetY + selectShape.getClickLocationY());
//                selectShape.createShape(point);
//                selectShape.setPortLocation();
//
//                //關於該圖的線全部更新
//                List<LineObject> lines = umlEditor.getLines();
//                List<Point> points = new ArrayList<>();
//
//                for (int i = 0; i < lines.size(); i++) {
//                    LineObject lineObject = lines.get(i);
//                    if (lineObject.getStartShapeObject() == selectShape && !points.contains(lineObject.getStartPoint())) {
//                        Point startPoint = lineObject.getStartPoint();
//                        startPoint.setLocation(startPoint.getX() + offsetX, startPoint.getY() + offsetY);
//                        points.add(startPoint);
//                    } else if (lineObject.getEndShapeObject() == selectShape && !points.contains(lineObject.getEndPoint())) {
//                        Point endPoint = lineObject.getEndPoint();
//                        endPoint.setLocation(endPoint.getX() + offsetX, endPoint.getY() + offsetY);
//                        points.add(endPoint);
//                    }
//                }
//            }
//        }

        umlEditor.repaint();
}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isClickOnShape) {
//            isClickOnShape = false;
            return;
        }

        System.out.println("release");
        // 取消之前全部所選擇，並清除紀錄
//        umlEditor.resetIsSelected(false);
        umlEditor.getSelectShapes().clear();

        Point endClickPoint = e.getPoint();
        for (UMLObject shape : umlEditor.getShapes()) {
            boolean isContain = isShapeBetweenPoints(shape, startClickPoint, endClickPoint);
            if (isContain) {
                umlEditor.addSelectShapes(shape);
                shape.setSelected(true);
            }
        }
        isClickOnShape = false;
        umlEditor.repaint();
    }

    // 判斷圖形是否在起始點和中點之間
    private boolean isShapeBetweenPoints(UMLObject shape, Point point1, Point point2) {
        double minX = Math.min(point1.getX(), point2.getX());
        double maxX = Math.max(point1.getX(), point2.getX());
        double minY = Math.min(point1.getY(), point2.getY());
        double maxY = Math.max(point1.getY(), point2.getY());

//        double shapeX = shape.getClickLocationX();
//        double shapeY = shape.getClickLocationY();
//        double shapeWidth = shape.getWidth();
//        double shapeHeight = shape.getHeight();
//
//        if (shapeX >= minX && (shapeX + shapeWidth) <= maxX && shapeY >= minY && (shapeY + shapeHeight) <= maxY) {
//            return true;
//        }
        return false;
    }
}
