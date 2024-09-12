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

import java.io.BufferedReader;
import java.io.IOException;

@Service(Service.Level.PROJECT)
public class WebStartService implements Disposable {
    private final Project project;
    @Getter
    @Setter
    private Process process;
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
            process = new ProcessBuilder("d:/bin/hello_web.exe").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void dispose() {
        if (process != null) {
            try {
                // 尝试杀死进程
                process.destroy();
                // 等待进程完全结束
                if (process.waitFor() == 0) {
                    System.out.println("进程已成功终止");
                } else {
                    System.out.println("进程未能终止，尝试强制杀死");
                    // 强制杀死进程
                    Runtime.getRuntime().exec("taskkill /F /PID " + process.pid());
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                process = null;
            }
        }
    }
}
