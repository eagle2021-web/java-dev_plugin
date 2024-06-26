package com.eagle.gava;

import com.eagle.gava.enums.SubTemplateEnum;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.IconUtil;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class AppSettingsComponent {
    public static final String TIP_TEXT_WIDTH = "200px";
    private static final String DIV_TEMP = String.format("<div style=\"white-space: pre-wrap; text-align: left; width: %s;\">$1</div>", TIP_TEXT_WIDTH);
    private static final int BOX_WIDTH = 200;
    private static final int HEIGHT = 30;
    private static final int LEFT_TEXT_WIDTH = 100;
    private static final int TIP_ICON_WIDTH = 30;
    private final JPanel myMainPanel;
    private final JBTextField myUserNameText = new JBTextField();
    private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");
    private final JComboBox<String> templateBox = new JComboBox<>(new String[]{"A", "B"});
    private final JComboBox<String> subTemplateBox = new JComboBox<>(new String[]{"A1", "A2"});
    private final JComboBox<SubTemplateEnum> temp = new ComboBox<>(new EnumComboBoxModel<>(SubTemplateEnum.class));

    public static final JComponent createJPanel1(String labelText, String tip) {
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel preLabel = new JLabel(labelText);
        preLabel.setPreferredSize(new Dimension(BOX_WIDTH, HEIGHT));
        optionPanel.add(preLabel);
        JLabel tipLabel = new JLabel("", UIManager.getIcon("Tree.closedIcon"), SwingConstants.LEFT);
        tipLabel.setHorizontalTextPosition(SwingConstants.LEFT); // 设置文本在图标右边

        tipLabel.setToolTipText(DIV_TEMP.replace("$1", tip));
        tipLabel.setPreferredSize(new Dimension(TIP_ICON_WIDTH, HEIGHT));
        optionPanel.add(tipLabel);

        JComboBox<String> myComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        myComboBox.setPreferredSize(new Dimension(BOX_WIDTH, HEIGHT));
        optionPanel.add(myComboBox);

        optionPanel.setPreferredSize(new Dimension(0, HEIGHT + 3));
        return optionPanel;
    }
    private final DefaultTableModel tableModel;
    private final JTable table;

    public AppSettingsComponent() {
        // Initialize the table and its model
        String[] columnNames = {"select", "sss", "pett"};
        Icon addIcon = IconUtil.getAddIcon();
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // The first column will contain checkboxes
                }
                return super.getColumnClass(columnIndex);
            }
        };
        table = new JTable(tableModel);
        JButton addButton = new JButton(addIcon);
        addButton.addActionListener(e -> tableModel.addRow(new Object[]{false, "", ""}));

        Icon removeIcon = IconUtil.getRemoveIcon();
        JButton removeButton = new JButton(removeIcon);
        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setPreferredSize(new Dimension(200, 200));
        JPanel buttonPanel = new JPanel();

        JPanel addRemoveJp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton.setPreferredSize(new Dimension(TIP_ICON_WIDTH, HEIGHT));
        addRemoveJp.add(addButton);
        removeButton.setPreferredSize(new Dimension(TIP_ICON_WIDTH, HEIGHT));
        addRemoveJp.add(removeButton);
        addRemoveJp.setPreferredSize(new Dimension(0, HEIGHT + 3));
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        JComponent panel44 = createJPanel1("MAX_TOKEN:", "The maximum number of tokens to be processed in a single request.");

        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(panel44)
                .addComponent(addRemoveJp)
                .addComponent(tablePanel)
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
