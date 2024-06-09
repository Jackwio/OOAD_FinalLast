package button;

import object.CompositeObject;
import object.ShapeObject;
import object.UMLObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class MenuBarButton extends JMenuBar {
    UMLEditor umlEditor;
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenuItem ungroupMenu = new JMenuItem("Ungroup");
    JMenuItem groupMenu = new JMenuItem("Group");
    JMenuItem renameMenuItem = new JMenuItem("Rename");

    public MenuBarButton() {
        umlEditor = UMLEditor.getUmlEditor();
        editMenu.add(renameMenuItem);
        editMenu.add(groupMenu);
        editMenu.add(ungroupMenu);
        add(fileMenu);
        add(editMenu);

        // 讓基本物件重新命名
        renameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog renameDialog = new JDialog();
                JTextField newObjectName = new JTextField("Default");
                JButton confirmButton = new JButton("Confirm");
                JButton cancelButton = new JButton("Cancel");
                Point renameDialogLocation = renameDialog.getLocation();
                int nameLocX = (int) renameDialogLocation.getX() + 25;
                int nameLocY = (int) renameDialogLocation.getY() + 10;
                int buttonLocY = nameLocY + 50;
                newObjectName.setBounds(nameLocX, nameLocY, 250, 35);

                confirmButton.setBounds(nameLocX + 10, buttonLocY, 100, 45);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (UMLObject shape : umlEditor.getShapes()) {
                            if (umlEditor.getCurrentSelectedObject() == shape) {
                                shape.setName(newObjectName.getText());
                                umlEditor.repaint();
                                break;
                            }
                        }
                        renameDialog.dispose();
                    }
                });

                cancelButton.setBounds(nameLocX + 140, buttonLocY, 100, 45);
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        renameDialog.dispose();
                    }
                });
                renameDialog.setLayout(null);
                renameDialog.add(newObjectName);
                renameDialog.add(confirmButton);
                renameDialog.add(cancelButton);
                renameDialog.setSize(300, 200);
                renameDialog.setLocationRelativeTo(null);
                renameDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                renameDialog.setVisible(true);
            }
        });

        groupMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判斷是否有選取大於1個基本物件
                if (umlEditor.getSelectShapes().size() < 2) {
                    JOptionPane.showMessageDialog(null, "請選擇多於一個基本物件");
                    return;
                }

                HashSet<UMLObject> groupShape = new HashSet<>(umlEditor.getSelectShapes());
                CompositeObject.addCompositeShape(groupShape);

                umlEditor.resetIsSelected(false);
                umlEditor.getSelectShapes().clear();

                umlEditor.repaint();
            }
        });

        ungroupMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判斷是否有選取大於1個基本物件
                if (CompositeObject.getCompositeShape().size() == 0) {
                    JOptionPane.showMessageDialog(null, "請選取一個group物件");
                    return;
                }
                ArrayList<HashSet<UMLObject>> compositeShape = CompositeObject.getCompositeShape();
//                for (int i = compositeShape.size() - 1; i >= 0; i--) {
//                    if (compositeShape.get(i).contains(canvas.getSelectShapes().get(0))) {
//                        compositeShape.remove(i);
//                        canvas.getSelectShapes().clear();
//                        break;
//                    }
//                }

                Vector<Integer> removeIndex = new Vector<>();
                int size = 0;
                HashSet<UMLObject> temp = new HashSet<>();
                for (int i = compositeShape.size() - 1; i >= 0; i--) {
                    if (umlEditor.getSelectShapes().containsAll(compositeShape.get(i))) {
                        if (size == 0) {
                            size = compositeShape.get(i).size();
                            temp = compositeShape.get(i);
                        }
                        if (size > compositeShape.get(i).size() && temp.containsAll(compositeShape.get(i))) {
                            continue;
                        }
                        removeIndex.add(i);
//                        canvas.getSelectShapes().clear();
//                        compositeShape.remove(i);
//                        canvas.getSelectShapes().clear();
//                        break;
                    }
                }
                umlEditor.getSelectShapes().clear();
                if (removeIndex.size() > 1) {
                    JOptionPane.showMessageDialog(null, "只能選取一個group物件");
                    return;
                }
                int indexToRemove = removeIndex.get(0);
                compositeShape.remove(indexToRemove);

//                umlEditor.refreshBackGround();
                umlEditor.repaint();
            }
        });
    }
}
