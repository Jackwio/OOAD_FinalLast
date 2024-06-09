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
        // 重置所有所有選擇狀態
        umlEditor.resetIsSelected(false);
        // 清除所有被選擇的
        umlEditor.getSelectShapes().clear();

        int flag = 0;
        for (UMLObject shape : umlEditor.getShapes()) {
            if (shape.contains(e.getPoint())) {
                umlEditor.setCurrentSelectedObject(shape);
                shape.setSelected(true);
                umlEditor.setTopObject(shape);
                flag = 1;
                break;
            }
        }

        ArrayList<HashSet<UMLObject>> compositeShape = CompositeObject.getCompositeShape();
        for (int i = compositeShape.size() - 1; i >= 0; i--) {
            if (compositeShape.get(i).contains(umlEditor.getCurrentSelectedObject())) {
                umlEditor.getSelectShapes().addAll(compositeShape.get(i));
                break;
            }
        }

        if (umlEditor.getCurrentSelectedObject() != null && flag == 0) {
            umlEditor.setCurrentSelectedObject(null);
        }
        
        isClickOnShape = umlEditor.getCurrentSelectedObject() == null ? false : true;
        umlEditor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        UMLObject currentSelectedObject = umlEditor.getCurrentSelectedObject();
        if (currentSelectedObject == null) {
            return;
        }

        System.out.println("select mode dragging");

        double clickX = e.getX();
        double clickY = e.getY();
        double offsetX = clickX - ((currentSelectedObject.getPoint1().getX()+currentSelectedObject.getPoint2().getX())/2);
        double offsetY = clickY - ((currentSelectedObject.getPoint1().getY()+currentSelectedObject.getPoint2().getY())/2);;


        if (umlEditor.getSelectShapes().size() == 0) {
            // 設定dragging，都是被選取狀態
            currentSelectedObject.setSelected(true);
            // 更新物件位置
            Point point1 = currentSelectedObject.getPoint1();
            point1.x += offsetX;
            point1.y += offsetY;
            currentSelectedObject.createShape(currentSelectedObject.getPoint1());
            // 更新4個port位置
            umlEditor.getCurrentSelectedObject().setPortLocation();
            // 為了不要讓重複的線動到兩次
            List<Point> points = new ArrayList<>();

            for (UMLObject shape : umlEditor.getShapes()) {
                // 代表是線不是圖形
                if(shape.getStartObject() != null){
                    if (shape.getStartObject() == umlEditor.getCurrentSelectedObject() && !points.contains(shape.getPoint1())) {
                        Point startPoint = shape.getPoint1();
                        startPoint.setLocation(startPoint.getX() + offsetX, startPoint.getY() + offsetY);
                        points.add(startPoint);
                    } else if (shape.getEndObject() == umlEditor.getCurrentSelectedObject() && !points.contains(shape.getPoint2())) {
                        Point endPoint = shape.getPoint2();
                        endPoint.setLocation(endPoint.getX() + offsetX, endPoint.getY() + offsetY);
                        points.add(endPoint);
                    }
                }
            }
        }
        else {
            for (UMLObject selectShape : umlEditor.getSelectShapes()) {
                selectShape.setSelected(false);
                Point point = new Point();
                point.setLocation(offsetX + selectShape.getPoint1().getX(), offsetY + selectShape.getPoint1().getY());
                selectShape.createShape(point);
                selectShape.setPortLocation();

                //關於該圖的線全部更新
                List<Point> points = new ArrayList<>();

                for (UMLObject shape : umlEditor.getShapes()) {
                    if(shape.getStartObject() != null){
                        if (shape.getStartObject() == selectShape && !points.contains(shape.getPoint1())) {
                            Point startPoint = shape.getPoint1();
                            startPoint.setLocation(startPoint.getX() + offsetX, startPoint.getY() + offsetY);
                            points.add(startPoint);
                        } else if (shape.getEndObject() == selectShape && !points.contains(shape.getPoint2())) {
                            Point endPoint = shape.getPoint2();
                            endPoint.setLocation(endPoint.getX() + offsetX, endPoint.getY() + offsetY);
                            points.add(endPoint);
                        }
                    }
                }
            }
        }
        umlEditor.repaint();
}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isClickOnShape) {
            isClickOnShape = false;
            return;
        }
        System.out.println("release");

        Point endClickPoint = e.getPoint();
        for (UMLObject shape : umlEditor.getShapes()) {
            boolean isContain = shape.isCover(shape, startClickPoint, endClickPoint);
            if (isContain) {
                umlEditor.addSelectShapes(shape);
                shape.setSelected(true);
            }
        }
        isClickOnShape = false;
        umlEditor.repaint();
    }

}
