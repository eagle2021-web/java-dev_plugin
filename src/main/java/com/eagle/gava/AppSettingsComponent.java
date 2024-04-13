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

    private final JPanel myMainPanel;
    private final JBTextField myUserNameText = new JBTextField();
    private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");
    private final JComboBox<String> templateBox =
            new JComboBox<>(new String[]{"A", "B"});
    private final JComboBox<String> subTemplateBox =
            new JComboBox<>(new String[]{"A1", "A2"});
    private final JComboBox<SubTemplateEnum> temp =
            new ComboBox<>(new EnumComboBoxModel<>(SubTemplateEnum.class));

    public AppSettingsComponent() {
//        EnumComboBoxModel<SubTemplateEnum> boxModel = new EnumComboBoxModel<>(SubTemplateEnum.class);

        templateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCascadingComponents();
            }
        });
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        JLabel templateLabel = new JLabel("框架：");
        horizontalPanel.add(templateLabel);
        horizontalPanel.add(Box.createHorizontalStrut(1));
        horizontalPanel.add(templateBox);
        horizontalPanel.add(Box.createHorizontalGlue()); // 使用glue来吸收额外的空间
        horizontalPanel.add(Box.createHorizontalStrut(100));
        JLabel subTemplateLabel = new JLabel("子模板：");
        horizontalPanel.add(subTemplateLabel);
        horizontalPanel.add(Box.createHorizontalStrut(1));
        horizontalPanel.add(subTemplateBox); //我只是希望，subTemplateBox和templateBox所在的选择框不用铺满，因为内容很少，或者我可以控制它的宽度为10
        horizontalPanel.add(Box.createHorizontalGlue()); // 使用glue来吸收额外的空间
        horizontalPanel.add(Box.createHorizontalStrut(20));

        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter user name: "), myUserNameText, 1, false) // #0
                .addComponent(horizontalPanel)
//                .setHorizontalGap(111)
//                .addLabeledComponent(new JBLabel("Temp "), temp, 1, false)
//                .setAlignLabelOnRight(true)
//                .addLabeledComponent(new JBLabel("Template "), templateBox, 0, false)
//                .addComponent(templateBox)
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
