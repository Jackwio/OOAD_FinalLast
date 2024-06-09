package button;

import action.ActionObject;
import object.ButtonRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftButton extends JButton implements ActionListener {
    // 當前是哪個按鈕
    private ButtonRole buttonRole;
    private Color color;
    private Boolean isSelected = false;

    // 當前按鈕所對應的行為
    private ActionObject buttonAction;
    private static UMLEditor umlEditor;

    // 前一個被按下的按鈕
    private static LeftButton lastClickButton;

    public LeftButton() {
    }

    public LeftButton(String imageName, ActionObject buttonAction, ButtonRole buttonRole) {
        umlEditor = UMLEditor.getUmlEditor();
        this.buttonAction = buttonAction;
        this.buttonRole = buttonRole;
        ImageIcon icon = new ImageIcon(imageName);
        setIcon(icon);

    }

    // 更新當前按下和前一個被按下的按鈕狀態
    public void setSelected(){
        if(lastClickButton !=null){
            // 還原上一個被點擊的按鈕
            lastClickButton.color = null;
            lastClickButton.setBackground(lastClickButton.color);
            lastClickButton.isSelected = false;
        }
        // 設置點擊後的背景顏色
        lastClickButton = this;
        color = Color.BLACK;
        setBackground(color);
        lastClickButton.isSelected = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(buttonRole + "被點擊了");
        setSelected();
        // 設定當前按下的按鈕名稱
        umlEditor.setCurrentButtonRole(buttonRole);
        umlEditor.setCurrentButtonListener(buttonAction);
        umlEditor.setButtonListener();
    }
}
