package com.eagle.gava;

import com.eagle.gava.enums.SubTemplateEnum;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

public class AppSettingsComponent {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 30;
    private final JPanel myMainPanel;
    private final JBTextField myUserNameText = new JBTextField();
    private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");
    private final JComboBox<String> templateBox =
            new JComboBox<>(new String[]{"A", "B"});
    private final JComboBox<String> subTemplateBox =
            new JComboBox<>(new String[]{"A1", "A2"});
    private final JComboBox<SubTemplateEnum> temp =
            new ComboBox<>(new EnumComboBoxModel<>(SubTemplateEnum.class));

    public static final JComponent createJPanel(String labelText){
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPanel.add(new JLabel(labelText));

        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // 设置文本在图标右边
        tipLabel.setToolTipText("这里填写您的提示信息，例如：开启平滑滚动可以提高滚动的视觉效果。");

        optionPanel.add(tipLabel);

        JComboBox<String> myComboBox = new JComboBox<>(new String[] {"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // 设置宽度为 200 像素，高度为 20 像素
        optionPanel.add(myComboBox);
        return optionPanel;
    }
    public AppSettingsComponent() {
//        EnumComboBoxModel<SubTemplateEnum> boxModel = new EnumComboBoxModel<>(SubTemplateEnum.class);

        templateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCascadingComponents();
            }
        });

        createJPanel("MAX_NEW_TOKENS：")


//        Border emptyBorder = BorderFactory.createEmptyBorder(1, 20, 1, 1);
//        myComboBox.setBorder(emptyBorder);
        ComboBox<String> box1 = new ComboBox<>(new String[]{"sdf", "222"});
        JComboBox<String> box2 = new JComboBox<>(new String[] {"Option 1", "Option 2"});
        box1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(optionPanel)
                .addLabeledComponent(new JBLabel("分割发现："), new JSeparator(),1,false)
                .addLabeledComponent(new JBLabel("MAX_NEW_TOKENS："), box1, 1, false)
                .addLabeledComponent(new JBLabel("MAX_TOKENS:"), box2, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();

    }

    private void updateCascadingComponents() {
        Object selectedItem = templateBox.getSelectedItem();
        System.out.println(temp.getItemCount());
        System.out.println(Arrays.toString(temp.getComponents()));

        subTemplateBox.removeAllItems();
        if ("A".equals(selectedItem)) {
            subTemplateBox.addItem("AAAAAAAAAAAAAAAAAAAAA");
            subTemplateBox.addItem("AAAAAAAAAAAAAAAAAAAAA2");
        } else if ("B".equals(selectedItem)) {
            subTemplateBox.addItem("BBBBBBBBBBBBBBBBB");
            subTemplateBox.addItem("BBBBBBBBBBBBBBBBB2");
        }
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return myUserNameText;
    }

    @NotNull
    public String getUserNameText() {
        return myUserNameText.getText();
    }

    public void setUserNameText(@NotNull String newText) {
        myUserNameText.setText(newText);
    }

    public boolean getIdeaUserStatus() {
        return myIdeaUserStatus.isSelected();
    }

    public void setIdeaUserStatus(boolean newStatus) {
        myIdeaUserStatus.setSelected(newStatus);
    }

    @NotNull
    public String getTemplateName() {
        return Objects.requireNonNull(templateBox.getSelectedItem()).toString();
    }

    public void setTemplateName(@NotNull String name) {
        this.templateBox.setSelectedItem(name);
    }

    public String getSubTemplateName() {
        return Objects.requireNonNull(subTemplateBox.getSelectedItem()).toString();
    }

    public void setSubTemplateName(@NotNull String name) {
        this.subTemplateBox.setSelectedItem(name);
    }

}
