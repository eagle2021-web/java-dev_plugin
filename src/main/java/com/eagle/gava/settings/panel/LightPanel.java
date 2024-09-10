package com.eagle.gava.settings.panel;

import javax.swing.*;
import java.awt.*;

public class LightPanel {
    private JPanel root;
    public LightPanel(){
        init();
    }

    public JPanel getjPanel() {
        return root;
    }

    public void init(){
        root = new JPanel(new GridBagLayout());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        root.add(new JLabel("密码："), gbc);
        gbc.gridx = 1;
        // 设置密码框的列宽
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平方向填充
        root.add(passwordField, gbc);

        JTextField usernameField = new JTextField();
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        root.add(new JLabel("用户名："), gbc);
        gbc.gridx = 1;
        // 设置用户名框的列宽
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平方向填充
        root.add(usernameField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH; // 填充整个区域
        root.add(new JPanel(), gbc); // 添加一个空的面板以填充底部空间
    }
}
