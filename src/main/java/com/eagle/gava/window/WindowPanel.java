package com.eagle.gava.window;

import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBTextArea;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
@Service(Service.Level.PROJECT)
public class WindowPanel {
    private Project project;
    private ConsoleView consoleView;
    private JTextArea textArea;

    public WindowPanel(Project project) {
        System.out.println("init");
        this.project = project;
        consoleView = createConsoleView(project);
        textArea = new JBTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public void setConsoleView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    private ConsoleView createConsoleView(Project project) {
        // 创建 ConsoleView 实例
        ConsoleView consoleView = new ConsoleViewImpl(project, true);
//        consoleView.setOutputMarkup(true); // 设置输出标记
        return consoleView;
    }
    public void sss(){
        System.out.println("-ssssssss");
    }
}
