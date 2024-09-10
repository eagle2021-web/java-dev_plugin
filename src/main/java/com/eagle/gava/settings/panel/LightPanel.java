package com.eagle.gava.settings.panel;

import javax.swing.*;
import java.awt.*;

public class LightPanel {
    private JPanel root;

    public LightPanel() {
        init();
    }

    public JPanel getjPanel() {
        return root;
    }

    protected JComponent createLeftPanel(String text, JComponent component) {
        Font systemFont = UIManager.getFont("Label.font");
        int fontSize = systemFont.getSize();
        System.out.println("fontSize = " + fontSize);
        int height = fontSize + 10;

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel = new JLabel(text);
        jLabel.setPreferredSize(new Dimension(100, height));
        jPanel.add(jLabel);

        component.setPreferredSize(new Dimension(300, height));
        jPanel.add(component);
        jPanel.setPreferredSize(new Dimension(0, height + 3));
        return jPanel;
    }

    public void init() {
        root = new JPanel(new GridBagLayout());
        JPasswordField passwordField = new JPasswordField();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 列索引
        gbc.gridy = 0;
        gbc.weightx = 1; // 水平方向占用比例
        gbc.weighty = 0; // 垂直方向占用比例
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平方向填充
        JTextField usernameField = new JTextField();

//        JComponent leftPanel = createLeftPanel("用户名   是对方", usernameField);
//        root.add(leftPanel, gbc);
//        gbc.gridy++;
//        JComponent passwordPanel = createLeftPanel("密码：", passwordField);
//        root.add(passwordPanel, gbc);

//        gbc.gridy++;
//        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        JLabel jLabel = new JLabel("第三行");
        jLabel.setPreferredSize(new Dimension(220, 30));
        root.add(jLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        Component textField = root.add(new JTextField());
        root.add(textField, gbc);
        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.weightx = 0.4;
        gbc.fill = GridBagConstraints.BOTH; // 水平方向填充
        root.add(new JPanel(), gbc);

        gbc.gridy++;
        gbc.weighty = 1; // 垂直方向占用比例
        gbc.fill = GridBagConstraints.BOTH; // 填充整个区域
        root.add(new JPanel(), gbc); // 添加一个空的面板以填充底部空间
    }
}
