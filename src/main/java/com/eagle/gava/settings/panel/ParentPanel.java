package com.eagle.gava.settings.panel;

import com.eagle.gava.AppSettingsConfigurable;
import com.intellij.ide.plugins.LinkPanel;
import com.intellij.ui.components.labels.LinkLabel;

import javax.swing.*;

public class ParentPanel {
    private JPanel root;

    public JPanel getRoot() {
        return root;
    }

    public ParentPanel(){
        init();
    }
    public void init(){
        root = new JPanel();
        LinkLabel<AppSettingsConfigurable> linkLabel = new LinkLabel<>();
        root.add(linkLabel);

    }
}
