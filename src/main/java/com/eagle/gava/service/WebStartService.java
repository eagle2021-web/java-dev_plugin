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


    public void startWeb(){
        // 启动d:/bin/hello_web.exe 补充完整
        // 启动 d:/bin/hello_web.exe
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setExePath("d:/bin/hello_web.exe");
        try {
            handler = new KillableProcessHandler(commandLine);
            handler.startNotify(); // 启动进程
            // 创建控制台视图


            // 创建可杀死的进程处理器
            KillableProcessHandler handler = new KillableProcessHandler(commandLine);

            // 启动进程并通知控制台
            handler.startNotify();
//            handler.getProcessInput().

            // 添加进程监听器
            handler.addProcessListener(new ProcessListener() {
                @Override
                public void startNotified(ProcessEvent event) {
                    System.out.println("Process started22222.\\n");
                }

                @Override
                public void onTextAvailable(ProcessEvent event, Key outputType) {
                    System.out.println("event.getText() = " + event.getText());
                    System.out.println("outputType = " + outputType);
//                    consoleView.print(event.getText(), ConsoleViewContentType.NORMAL_OUTPUT);
                }

                @Override
                public void processTerminated(ProcessEvent event) {
                    System.out.println("event.getText() = " + event.getText());
//                    consoleView.print("Process terminated with exit code: " + event.getExitCode() + "\\n", ConsoleViewContentType.SYSTEM_OUTPUT);
                }

                @Override
                public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
                    System.out.println("event.getText() = " + event.getText());
//                    consoleView.print("Process will terminate.\\n", ConsoleViewContentType.SYSTEM_OUTPUT);
                }
            });
        } catch (ExecutionException e) {
            Messages.showErrorDialog(project, "启动进程失败: " + e.getMessage(), "错误");
            e.printStackTrace();
        }

    }

    @Override
    public void dispose(){
        // 实现哪个接口可以有这个方法
        if(handler != null){
            if (handler.canKillProcess()) {
                System.out.println("-11");
                handler.killProcess();;
                // 等待进程完全结束
                handler.waitFor();
                System.out.println("handler.canKillProcess() = " + handler.canKillProcess());
                if (handler.canKillProcess()) {
                    System.out.println("-222");
                    handler.killProcess();
                }
            }
            handler = null;
        }
    }
}
