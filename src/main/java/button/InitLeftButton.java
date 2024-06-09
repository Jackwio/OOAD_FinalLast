package button;

import action.LineObjectAction;
import action.SelectAction;
import action.ShapeObjectAction;
import object.ButtonRole;

import javax.swing.*;
import java.awt.*;

public class InitLeftButton extends JToolBar {
    private UMLEditor umlEditor;
    public InitLeftButton() {
        // 順便初始化umlEditor
        umlEditor = UMLEditor.getUmlEditor();
        setLayout(new GridLayout(6, 1, 3, 3));
        LeftButton selectButton = new LeftButton("C:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\left_up1.jpg", new SelectAction(), ButtonRole.SELECT);
        selectButton.addActionListener(selectButton);
        add(selectButton);

        LeftButton associationLineButton = new LeftButton("CC:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\left.jpg", new LineObjectAction(), ButtonRole.ASSOCIATION_LINE);
        associationLineButton.addActionListener(associationLineButton);
        add(associationLineButton);

        LeftButton generalizationButton = new LeftButton("C:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\left-v.jpg", new LineObjectAction(), ButtonRole.GENERALIZATION_LINE);
        generalizationButton.addActionListener(generalizationButton);
        add(generalizationButton);

        LeftButton compositionLineButton = new LeftButton("C:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\composition.png", new LineObjectAction(), ButtonRole.COMPOSITION_LINE);
        compositionLineButton.addActionListener(compositionLineButton);
        add(compositionLineButton);

        LeftButton classButton = new LeftButton("C:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\class.png", new ShapeObjectAction(), ButtonRole.CLASS);
        classButton.addActionListener(classButton);
        add(classButton);

        LeftButton useCaseButton = new LeftButton("C:\\Users\\USER\\Desktop\\OOAD_Final\\src\\main\\resources\\icon\\circle.jpg", new ShapeObjectAction(), ButtonRole.USECASE);
        useCaseButton.addActionListener(useCaseButton);
        add(useCaseButton);
    }
}
