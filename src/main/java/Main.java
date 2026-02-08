import button.InitLeftButton;
import button.MenuBarButton;
import button.UMLEditor;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame editor = null;
        Container container = editor.getContentPane();
        UMLEditor umlEditor = UMLEditor.getUmlEditor();
        MenuBarButton menuBarButton = new MenuBarButton();
        InitLeftButton leftButton = new InitLeftButton();
        umlEditor.setBackground(Color.DARK_GRAY);
        container.add(umlEditor);
        container.add(leftButton, BorderLayout.WEST);
        editor.setJMenuBar(menuBarButton);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.setSize(1200, 750);
        editor.setVisible(true);
    }
}
