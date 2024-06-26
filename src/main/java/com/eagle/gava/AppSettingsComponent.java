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
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class AppSettingsComponent {
    public static final String TIP_TEXT_WIDTH = "200px";
    private static final String DIV_TEMP = String.format("<div style=\"white-space: pre-wrap; text-align: left; width: %s;\">$1</div>", TIP_TEXT_WIDTH);
    private static final int WIDTH = 200;
    private static final int HEIGHT = 30;
    private final JPanel myMainPanel;
    private final JBTextField myUserNameText = new JBTextField();
    private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");
    private final JComboBox<String> templateBox = new JComboBox<>(new String[]{"A", "B"});
    private final JComboBox<String> subTemplateBox = new JComboBox<>(new String[]{"A1", "A2"});
    private final JComboBox<SubTemplateEnum> temp = new ComboBox<>(new EnumComboBoxModel<>(SubTemplateEnum.class));

    public static final JComponent createJPanel1(String labelText) {
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel preLabel = new JLabel(labelText);
        preLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(preLabel);
        URL iconUrl = AppSettingsComponent.class.getResource("icon/tip.png");
        assert iconUrl != null;
        ImageIcon customIcon = new ImageIcon(iconUrl);
        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // 设置文本在图标右边
        tipLabel.setToolTipText("这里填写您的提示信息，例如：开启平滑滚动可以提高滚动的视觉效果。");
        tipLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(tipLabel);

        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(myComboBox);
        return optionPanel;
    }

    public AppSettingsComponent() {
        JComponent panel44 = createJPanel1("MAX_TOKEN:");
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(panel44)
                .addComponentFillVertically(new JPanel(), 0).getPanel();
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
