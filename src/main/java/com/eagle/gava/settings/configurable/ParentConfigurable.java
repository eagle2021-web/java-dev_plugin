package com.eagle.gava.settings.configurable;

import com.eagle.gava.AppSettingsComponent;
import com.eagle.gava.settings.panel.ParentPanel;
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
        myPanel = new JPanel(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

        helloLabel = new JLabel("hello");
        myPanel.add(helloLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        linkLabel1 = new LinkLabel("https://www.example.com", null, new LinkListener() {
            @Override
            public void linkSelected(LinkLabel aSource, Object aLinkData) {
                System.out.println("\"sout\" = " + "sout");
            }
        });
        myPanel.add(linkLabel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        linkLabel2 = new LinkLabel();
        myPanel.add(linkLabel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        // 设置组件之间没有间隔
        ((GridLayoutManager) myPanel.getLayout()).setVGap(0);

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