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
        myComboBox.setPreferredSize(new Dimension(200, 20)); // ���ÿ��Ϊ 200 ���أ��߶�Ϊ 20 ����





// ��ȡ������ͼ��Ĭ�Ϲر�ͼ�꣨��ֻ��һ�����ӣ������ܲ�������Ҫ��ͼ�꣩
        Icon icon = UIManager.getIcon("Tree.closedIcon");

// ���� JLabel������ͼ����ı�
        // ���������ı���ͼ���JLabel����ͼ��Ĭ�������
        JLabel helpLabel = new JLabel("�ı�", icon, SwingConstants.LEFT);
        helpLabel.setHorizontalTextPosition(SwingConstants.RIGHT); // �����ı���ͼ���ұ�
        helpLabel.setIconTextGap(10); // ����ͼ����ı�֮��ļ��

        // ���ù�����ʾ
        helpLabel.setToolTipText("������д������ʾ��Ϣ�����磺����ƽ������������߹������Ӿ�Ч����");

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPanel.add(new JLabel("ѡ��1222222��"));
//        optionPanel.add(new JLabel("223"));
        optionPanel.add(helpLabel);
        optionPanel.add(myComboBox);

        ComboBox<String> box1 = new ComboBox<>(new String[]{"sdf", "222"});
        box1.setPreferredSize(new Dimension(200, 20)); // ���ÿ��Ϊ 200 ���أ��߶�Ϊ 20 ����
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
