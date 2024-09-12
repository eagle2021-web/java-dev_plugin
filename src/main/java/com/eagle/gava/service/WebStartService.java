package com.eagle.gava.service;

import com.eagle.gava.util.SoleLogUtil;
import com.eagle.gava.window.WindowPanel;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.KillableProcess;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.NlsActions;
import lombok.Getter;
import lombok.Setter;
import org.jdesktop.swingx.util.WindowUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.*;
@Service(Service.Level.PROJECT)
public class WebStartService implements Disposable {
    private final Project project;
    @Getter
    @Setter
    private KillableProcessHandler handler;

    public WebStartService(Project project) {
        System.out.println("WebStartService");
        this.project = project;
    }

    public void startWeb() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setExePath("d:/bin/hello_web.exe");
        try {
            handler = new KillableProcessHandler(commandLine);
            handler.startNotify(); // 启动进程
//            handler.setShouldKillProcessSoftly(true);

            // 添加进程监听器
            handler.addProcessListener(new ProcessListener() {
                @Override
                public void startNotified(ProcessEvent event) {
                    System.out.println("Process started.");
                }

                @Override
                public void onTextAvailable(ProcessEvent event, Key outputType) {
                    System.out.println("Output: " + event.getText());
                }

                @Override
                public void processTerminated(ProcessEvent event) {
                    System.out.println("Process terminated with exit code: " + event.getExitCode());
                }

                @Override
                public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
                    System.out.println("Process will terminate.");
                }
            });
        } catch (ExecutionException e) {
            Messages.showErrorDialog(project, "启动进程失败: " + e.getMessage(), "错误");
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        if (handler != null) {
            if (handler.canKillProcess()) {
                handler.killProcess(); // 尝试杀死进程
//                handler.waitFor(); // 等待进程结束
                System.out.println("handler.canKillProcess() = " + handler.canKillProcess());
            }
            handler = null; // 清理 handler 引用
        }
    }
}

