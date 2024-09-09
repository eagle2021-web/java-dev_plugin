package com.eagle.gava.settings.configurable;

import com.eagle.gava.AppSettingsComponent;
import com.eagle.gava.settings.panel.ParentPanel;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.components.labels.LinkListener;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ParentConfigurable implements Configurable, SearchableConfigurable {

    private JPanel myPanel;
    private JLabel helloLabel;
    private LinkLabel linkLabel1;
    private LinkLabel linkLabel2;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "My Plugin Settings";
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        myPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 添加 helloLabel
        helloLabel = new JLabel("hello");
        gbc.gridx = 0; // 列索引
        gbc.gridy = 0; // 行索引
        gbc.weightx = 1; // 水平方向占用比例
        gbc.weighty = 0; // 垂直方向占用比例
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平方向填充
        myPanel.add(helloLabel, gbc);

        // 添加 linkLabel1
        linkLabel1 = new LinkLabel("https://www.example.com", null, (aSource, aLinkData) -> {
        });
        gbc.gridy = 1; // 行索引
        gbc.weighty = 0; // 垂直方向占用比例
        myPanel.add(linkLabel1, gbc);

        // 添加 linkLabel2
        linkLabel2 = new LinkLabel("sss", null, (aSource, aLinkData) -> {
        });
        gbc.gridy = 2; // 行索引
        gbc.weighty = 0; // 垂直方向占用比例
        myPanel.add(linkLabel2, gbc);

        // 设置底部空白填充
        gbc.gridy = 3; // 行索引
        gbc.weighty = 1; // 垂直方向占用比例
        gbc.fill = GridBagConstraints.BOTH; // 填充整个区域
        myPanel.add(new JPanel(), gbc); // 添加一个空的面板以填充底部空间

        return myPanel;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {

    }
    @Override
    public @NotNull @NonNls String getId() {
        return "com.eagle.gava.settings.configurable.ParentConfigurable";
    }
}