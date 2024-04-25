package com.eagle.gava;

import com.eagle.gava.enums.SubTemplateEnum;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBImageIcon;
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

        JComboBox<String> myComboBox = new JComboBox<>(new String[] {"Option 1", "Option 2"});
        JComboBox<String> box2 = new JComboBox<>(new String[] {"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(200, 20)); // 设置宽度为 200 像素，高度为 20 像素





// 获取树形视图的默认关闭图标（这只是一个例子，它可能不是你想要的图标）
        Icon icon = UIManager.getIcon("Tree.closedIcon");

// 创建 JLabel，包含图标和文本
        // 创建带有文本和图标的JLabel，但图标默认在左边
        JLabel helpLabel = new JLabel("文本", icon, SwingConstants.LEFT);
        helpLabel.setHorizontalTextPosition(SwingConstants.RIGHT); // 设置文本在图标右边
        helpLabel.setIconTextGap(10); // 设置图标和文本之间的间隔

        // 设置工具提示
        helpLabel.setToolTipText("这里填写您的提示信息，例如：开启平滑滚动可以提高滚动的视觉效果。");

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPanel.add(new JLabel("选项1222222："));
//        optionPanel.add(new JLabel("223"));
        optionPanel.add(helpLabel);
        optionPanel.add(myComboBox);

        ComboBox<String> box1 = new ComboBox<>(new String[]{"sdf", "222"});
        box1.setPreferredSize(new Dimension(200, 20)); // 设置宽度为 200 像素，高度为 20 像素
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(optionPanel)
                .addLabeledComponent(new JBLabel("sdfsdf2222222"), new JSeparator(),200,false)
                .addLabeledComponent(new JBLabel("dsfsdf2222"), box1, 20, false)
                .addLabeledComponent(new JBLabel("box2"), box2, 20, false)
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
