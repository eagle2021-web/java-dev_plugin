package com.eagle.gava;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;

import java.io.*;

public class MyApplicationComponent implements ApplicationComponent {

    private final ProjectManager projectManager;

    public MyApplicationComponent(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public void initComponent() {
        // 读取变更日志文件
        try (InputStream is = MyApplicationComponent.class.getResourceAsStream("CHANGELOG")) {
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                System.out.println(sb);
                // 在这里，你可以将 sb.toString() 的内容展示给用户
                // 例如，通过消息框、工具窗口或其他 UI 组件
            }
        } catch (IOException e) {
            // 处理异常
        }
    }
}
