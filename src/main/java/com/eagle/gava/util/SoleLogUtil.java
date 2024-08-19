package com.eagle.gava.util;

import com.eagle.gava.window.MyToolWindow;
import com.eagle.gava.window.WindowPanel;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;

import java.awt.*;

@Service(Service.Level.PROJECT)
public class SoleLogUtil {
    private Project project;
    private ConsoleView consoleView;

    public SoleLogUtil(Project project) {
        this.project = project;
        consoleView = this.project.getService(WindowPanel.class).getConsoleView();
    }

    public void info(String text) {
        // 打印绿色字体
        ConsoleViewContentType greenOutputType = new ConsoleViewContentType("GREEN_OUTPUT",
                new TextAttributes(Color.GREEN, null, null, null, Font.PLAIN));
        consoleView.print(text + "\n", greenOutputType);
    }

    public void error(String text) {
        // 打印红色字体
        ConsoleViewContentType redOutputType = new ConsoleViewContentType("RED_OUTPUT",
                new TextAttributes(Color.RED, null, null, null, Font.PLAIN));
        consoleView.print(text + "\n", redOutputType);
    }

}
