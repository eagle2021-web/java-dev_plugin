package com.eagle.gava.service;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.KillableProcess;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import lombok.Getter;
import lombok.Setter;

@Service(Service.Level.PROJECT)
public class WebStartService implements Disposable {
    private final Project project;
    @Getter
    @Setter
    private KillableProcessHandler processHandler;
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
            processHandler = new KillableProcessHandler(commandLine);
            processHandler.startNotify(); // 启动进程
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

        if (processHandler instanceof KillableProcess) {
            System.out.println("------sssss");
            KillableProcess killableProcess = (KillableProcess) processHandler;
            if (killableProcess.canKillProcess()) {
                if (!processHandler.waitFor(1000L)) {
                    // doing 'force quite'
                    System.out.println("kkkkkk");
                    processHandler.getProcess().destroyForcibly();
                    processHandler.killProcess();
                    System.out.println("killableProcess.canKillProcess() = " + killableProcess.canKillProcess());
                }
            }
        }
//        // 实现哪个接口可以有这个方法
//        if(processHandler != null){
//            if (processHandler.canKillProcess()) {
//                System.out.println("-11");
//                processHandler.killProcess();;
//                // 等待进程完全结束
//                processHandler.waitFor();
//                System.out.println("handler.canKillProcess() = " + processHandler.canKillProcess());
//                if (processHandler.canKillProcess()) {
//                    System.out.println("-222");
//                    processHandler.killProcess();
//                }
//            }
//            processHandler = null;
//        }
    }
}
