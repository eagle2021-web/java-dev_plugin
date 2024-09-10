package com.eagle.gava.settings.panel;

import com.intellij.ui.components.JBCheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    protected void appendLineComp(GridBagConstraints gbc, JPanel root, String text, JComponent component) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 使组件填满单元格

        // 创建 JLabel
        JLabel jLabel = new JLabel(text);
        jLabel.setBackground(Color.BLUE);
        jLabel.setOpaque(true); // 确保背景颜色可见

        // 设置第一列的组件
        gbc.gridx = 0;
        gbc.weightx = 0.05; // 设置权重
//        gbc.insets = new Insets(5, 5, 5, 5); // 设置边距
//        jLabel.setPreferredSize(new Dimension(40,jLabel.getPreferredSize().height)); // 设置首选大小

        root.add(jLabel, gbc);

        // 添加其他组件
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        component.setPreferredSize(new Dimension(200,component.getPreferredSize().height)); // 设置首选大小
        root.add(component, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.5;
        root.add(new JLabel(), gbc); // 如果不需要，可以考虑移除这一行
    }

    public void init() {
        root = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JTextField jTextField = new JTextField();
        appendLineComp(gbc, root, "111", jTextField);

        gbc.gridy++;
        JTextField jTextField2 = new JTextField();
        appendLineComp(gbc, root, "111222222", jTextField2);

        gbc.gridy++;
        JTextField jTextField3 = new JTextField();
        appendLineComp(gbc, root, "111222222", jTextField3);
        gbc.gridy++;
        JTextField jTextField4 = new JTextField();
        appendLineComp(gbc, root, "11122222s度f2", jTextField4);
        gbc.gridy++;
        JPasswordField jPasswordField = new JPasswordField();
        appendLineComp(gbc, root, "密碼：", jPasswordField);
//        jPasswordField.setColumns(30);
        // 添加checkbox
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0.1; // 设置权重
        JBCheckBox 冬天 = new JBCheckBox("冬天");
        root.add(冬天, gbc);

        // 添加按钮
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.WEST;
        JButton button = new JButton("发送请求");

        root.add(button, gbc);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = JOptionPane.showInputDialog(root, "请输入地址：");
                if (address != null && !address.isEmpty()) {
                    // 这里模拟发送请求，实际应用中需替换为真实的请求逻辑
                    JOptionPane.showMessageDialog(root, "发送请求到：" + address);
                }
            }
        });

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel.add(new JCheckBox("冬天"));
        jPanel.add(new JCheckBox("冬天"));


        gbc.gridx = -1;
        gbc.gridy++;
        gbc.weightx = 0.05; // 设置权重
        gbc.fill = GridBagConstraints.HORIZONTAL;
        root.add(jPanel, gbc);

        gbc.gridy++;
        root.add(new JCheckBox("冬季"), gbc);
        root.add(new JCheckBox("冬季"), gbc);
        root.add(new JCheckBox("冬季"), gbc);
        root.add(new JCheckBox("冬季"), gbc);
        root.add(new JCheckBox("冬季"), gbc);
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        root.add(new JPanel(), gbc);


    }
}
