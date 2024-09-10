package com.eagle.gava.settings.panel;

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

    protected void appendLineComp(GridBagConstraints gbc, JPanel root, String text, JTextField component) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.WEST;
        JLabel jLabel = new JLabel(text);
        jLabel.setPreferredSize(new Dimension(60, jLabel.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.weightx = 0.1;
        root.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        // 设置固定宽度为 100 像素
//        component.setPreferredSize(new Dimension(100, component.getPreferredSize().height));
        component.setColumns(20);
        root.add(component, gbc);

        gbc.fill = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.5;
        root.add(new JPanel(), gbc);
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
        JPasswordField jPasswordField = new JPasswordField();
        appendLineComp(gbc, root, "密碼：", jPasswordField);
        jPasswordField.setColumns(30);

        // 添加按钮
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.WEST;
        JButton button = new JButton("发送请求");
//        button.setPreferredSize(new Dimension(50, button.getPreferredSize().height));

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


        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        root.add(new JPanel(), gbc);


    }
}
