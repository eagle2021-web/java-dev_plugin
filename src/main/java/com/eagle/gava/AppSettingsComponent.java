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
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // �����ı���ͼ���ұ�
        tipLabel.setToolTipText("������д������ʾ��Ϣ�����磺����ƽ������������߹������Ӿ�Ч����");
        tipLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(tipLabel);

        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(myComboBox);
        return optionPanel;
    }


    public static JComponent createJPanel2(String labelText) {
        JPanel optionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // ���ñ߾�
        constraints.anchor = GridBagConstraints.WEST;

        // ��ӱ�ǩ
        constraints.gridx = 0; // ��
        constraints.gridy = 0; // ��
        constraints.weightx = 0; // ����Ȩ��
        optionPanel.add(new JLabel(labelText), constraints);

        // ��Ӵ�ͼ��ı�ǩ
        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        tipLabel.setToolTipText("������д������ʾ��Ϣ��");
        constraints.gridx = 1;
        optionPanel.add(tipLabel, constraints);

        // �����Ͽ�
        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(200, 20)); // ���ÿ�Ⱥ͸߶�
        constraints.gridx = 2;
        constraints.weightx = 1; // ���Ӻ���Ȩ���Զ������
        optionPanel.add(myComboBox, constraints);

        return optionPanel;
    }

    public static JComponent createJPanel3(String labelText) {
//        JPanel optionPanel = new JPanel();
//        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        optionPanel.add(label);

        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        tipLabel.setToolTipText("������д������ʾ��Ϣ��");
        optionPanel.add(tipLabel);

        // ��������Ŀռ�

        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        System.out.println(myComboBox.getLocation());
        myComboBox.setLocation(300, myComboBox.getLocation().y);
        optionPanel.add(myComboBox);
//        optionPanel.add(Box.createHorizontalGlue());

        return optionPanel;
    }

    public static final JComponent createJPanel4(String labelText) {
//        JPanel optionPanel = new JPanel();
//        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS)); // Use BoxLayout
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel = new JLabel(labelText);
        jLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(jLabel);

        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // �����ı���ͼ���ұ�
        tipLabel.setToolTipText("������д������ʾ��Ϣ�����磺����ƽ������������߹������Ӿ�Ч����");
        tipLabel.setPreferredSize(new Dimension(30, 30));
//        tipLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(tipLabel);
        System.out.println(optionPanel.getSize());
        System.out.println(optionPanel.getPreferredSize());
        System.out.println(tipLabel.getPreferredSize());
        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // ���ÿ��Ϊ 200 ���أ��߶�Ϊ 20 ����
//        optionPanel.add(Box.createHorizontalGlue()); // Add glue to push the combo box to the right
        optionPanel.add(myComboBox);
        return optionPanel;
    }

    public static final JComponent createJPanel5(String labelText, JComponent checkBox, String simpleText) {
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel = new JLabel(labelText);
        jLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(jLabel);

        URL iconUrl = AppSettingsComponent.class.getClassLoader().getResource("icon/tip.png");
        assert iconUrl != null;
        ImageIcon customIcon = new ImageIcon(iconUrl);
        System.out.println(customIcon.getIconHeight());
        System.out.println(customIcon.getIconWidth());
        // tip
        JLabel tipLabel = new JLabel("", SwingConstants.LEFT);
        tipLabel.setPreferredSize(new Dimension(30, 30));
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // �����ı���ͼ���ұ�
        if (simpleText != null) {
            tipLabel.setIcon(customIcon);
            String htmlText = DIV_TEMP.replace("$1", simpleText);
            tipLabel.setToolTipText(htmlText);
        }
        optionPanel.add(tipLabel);

        checkBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        optionPanel.add(checkBox);
        optionPanel.setPreferredSize(new Dimension(0, HEIGHT));
        return optionPanel;
    }

    public static final JComponent createJPanelByForm(String labelText, JComponent checkBox, String simpleText) {
        JLabel jLabel = new JLabel(labelText);
        jLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        URL iconUrl = AppSettingsComponent.class.getClassLoader().getResource("icon/tip.png");
        assert iconUrl != null;
        ImageIcon customIcon = new ImageIcon(iconUrl);
        System.out.println(customIcon.getIconHeight());
        System.out.println(customIcon.getIconWidth());
        // tip
        JLabel tipLabel = new JLabel("", SwingConstants.LEFT);
        tipLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // �����ı���ͼ���ұ�
        if (simpleText != null) {
            tipLabel.setIcon(customIcon);
            String htmlText = DIV_TEMP.replace("$1", simpleText);
            tipLabel.setToolTipText(htmlText);
        }
        JComboBox<String> box4 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        checkBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel panel = FormBuilder.createFormBuilder()
                .setVerticalGap(0)
                .addComponent(jLabel)
                .addComponent(tipLabel)
                .addComponent(checkBox)
                .getPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        return panel;
    }
    public static final JComponent createJPanel(String labelText) {
        return createJPanel4(labelText);
    }

    public static final JComponent createJPanel(String labelText, JComponent checkBox, String simpleText) {
        return createJPanel5(labelText, checkBox, simpleText);
    }

    public AppSettingsComponent() {
        JComponent panel1 = createJPanel("MAX_NEW_TOKENS��");
        JComponent panel2 = createJPanel("MAX_1��");
        JComponent panel3 = createJPanel("MAX_NEW_TOKENSMAX_NEW_TOKENS��");
        JComponent panel4 = createJPanel("MAX_TOKEN:", new ComboBox<>(new String[]{"sdf", "222"}), "�����ı������ʿ��������ʿ���ʿ���ʿ��� wordafsdf df asdf sdf sd ");
        JComponent panel5 = createJPanel("����ʲô����:", new ComboBox<>(new String[]{"sdf", "222"}), "���Ƽ����࣬��Ϊ�������");
        JComponent panel6 = createJPanel("����ʲô����222:", new ComboBox<>(new String[]{"sdf", "222"}), null);

        ComboBox<String> box1 = new ComboBox<>(new String[]{"sdf", "222"});
        JComboBox<String> box2 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        JComboBox<String> box3 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        JComboBox<String> box4 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        JComboBox<String> box31 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        JComboBox<String> box41 = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        box1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box2.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box3.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box4.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box31.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        box41.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel panel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("333333333"), box3, 1, false)
                .addLabeledComponent(new JBLabel("444444444444"), box4, 1, false)
                .getPanel();

        JPanel panel22 = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("333333333"), box31, 1, false)
                .addLabeledComponent(new JBLabel("444444444444"), box41, 1, false)
                .getPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(0, 30));
        panel22.setLayout(new FlowLayout(FlowLayout.LEFT));
        JComponent panel44 = createJPanelByForm("MAX_TOKEN:", new ComboBox<>(new String[]{"sdf", "222"}), "�����ı������ʿ��������ʿ���ʿ���ʿ��� wordafsdf df asdf sdf sd ");
        JComponent panel54 = createJPanelByForm("����ʲô����:", new ComboBox<>(new String[]{"sdf", "222"}), "���Ƽ����࣬��Ϊ�������");
        JComponent panel64 = createJPanelByForm("����ʲô����222:", new ComboBox<>(new String[]{"sdf", "222"}), null);
        myMainPanel = FormBuilder.createFormBuilder()
                .setVerticalGap(-5)
//                .addComponent(panel1)
//                .addComponent(panel2)
//                .addComponent(panel3)
                .addComponent(panel4,1)
                .addComponent(panel5, 1)
                .addComponent(panel6, 1)
                .addLabeledComponent(new JBLabel("�ָ�֣�"), new JSeparator(), 1, false)
                .addLabeledComponent(new JBLabel("bbbbb"), box1, 1, false)
//                .addComponent(box2)
                .addComponent(panel)
                .addLabeledComponent(new JBLabel("AAAAA"), panel22, 1, false)
//                .addComponent(panel22)
                .addComponent(panel44)
                .addComponent(panel54)
                .addComponent(panel64)
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
