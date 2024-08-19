package com.eagle.gava.window;

import com.eagle.gava.util.SoleLogUtil;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jdesktop.swingx.util.WindowUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        WindowPanel service = project.getService(WindowPanel.class);

        // 创建主面板
        JPanel panel = new JPanel(new BorderLayout());

        // 创建 ConsoleView
        ConsoleView consoleView = service.getConsoleView();

        // 创建 JTextArea


        // 将 ConsoleView 和 JTextArea 分别放入左侧和右侧
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(consoleView.getComponent());

        // 创建按钮
        JButton sendButton = new JButton("发送消息");
        sendButton.addActionListener(e -> {
            // 向 ConsoleView 发送消息
            consoleView.print("hello\n", ConsoleViewContentType.NORMAL_OUTPUT);
            printRedText(consoleView);
            SoleLogUtil log = project.getService(SoleLogUtil.class);
            log.info("info");
            log.error("error");
        });

        // 创建右侧面板，包含 JTextArea 和按钮
        JPanel rightPanel = new JPanel(new BorderLayout());
        JTextArea textArea = service.getTextArea();
        rightPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        rightPanel.add(sendButton, BorderLayout.SOUTH); // 将按钮放在底部
        // 右侧框框新增按钮，按钮点击后可以往consoleviw发送一个消息 hello
        splitPane.setRightComponent(rightPanel);
        splitPane.setDividerLocation(500); // 设置分隔条位置
        splitPane.setDividerSize(1); // 设置分隔条宽度为5像素
        // 将分割面板添加到主面板
        panel.add(splitPane, BorderLayout.CENTER);

        // 创建并添加内容到工具窗口
        ContentFactory contentFactory =ContentFactory.getInstance();
        Content content = contentFactory.createContent(panel, null, false);
        toolWindow.getContentManager().addContent(content);
    }

    public void printRedText(ConsoleView consoleView) {
        // 创建文本属性并设置前景色为红色
        TextAttributes textAttributes = new TextAttributes();
        textAttributes.setForegroundColor(Color.RED); // 设置前景色为红色

        // 使用自定义的 ConsoleViewContentType
        ConsoleViewContentType redOutputType = new ConsoleViewContentType("RED_OUTPUT", textAttributes);

        // 打印红色文本
        consoleView.print("hello\n", redOutputType);
    }
}