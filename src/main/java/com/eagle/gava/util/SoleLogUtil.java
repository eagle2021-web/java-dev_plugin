package com.eagle.gava.util;

import com.eagle.gava.window.MyToolWindow;
import com.eagle.gava.window.WindowPanel;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import lombok.Getter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service(Service.Level.PROJECT)
public class SoleLogUtil {
    private Project project;
    @Getter
    private ConsoleView consoleView;

    public SoleLogUtil(Project project) {
        this.project = project;
        consoleView = this.project.getService(WindowPanel.class).getConsoleView();
    }

    private static String getCurTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        return dateFormat.format(new Date());
    }

    public void info(String text) {
        // 打印绿色字体
        ConsoleViewContentType greenOutputType = new ConsoleViewContentType("GREEN_OUTPUT",
                new TextAttributes(Color.GREEN, null, null, null, Font.PLAIN));
        print(text, greenOutputType);
    }

    public void error(String text) {
        // 打印红色字体
        ConsoleViewContentType redOutputType = new ConsoleViewContentType("RED_OUTPUT",
                new TextAttributes(Color.RED, null, null, null, Font.PLAIN));
        print(text, redOutputType);
    }

    private void print(String text, ConsoleViewContentType redOutputType) {
        String curTime = getCurTime();
        consoleView.print(curTime + ": " + text + "\n", redOutputType);
    }

}
