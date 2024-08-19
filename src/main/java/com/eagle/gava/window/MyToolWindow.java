package com.eagle.gava.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MyToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("这是我的 ToolWindow"));
        toolWindow.getContentManager().addContent(toolWindow.getContentManager().getFactory().createContent(panel, null, false));
    }
}