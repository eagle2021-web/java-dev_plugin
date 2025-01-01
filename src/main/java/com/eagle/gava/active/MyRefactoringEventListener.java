package com.eagle.gava.active;

import com.eagle.gava.service.WebStartService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;

public class MyRefactoringEventListener implements RefactoringEventListener {
    private final Project myProject;

    private MyRefactoringEventListener(@NotNull Project project) {
        myProject = project;
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @NotNull RefactoringEventData beforeData) {
        System.out.println("refactoringId = " + refactoringId);
        System.out.println("beforeData.getUserDataString() = " + beforeData.getUserDataString());
    }

    @Override
    public void refactoringDone(@NotNull String refactoringId, @NotNull RefactoringEventData afterData) {
        System.out.println("refactoringId = " + refactoringId);
        System.out.println("afterData.getUserDataString() = " + afterData.getUserDataString());
    }

    @Override
    public void conflictsDetected(@NotNull String refactoringId, @NotNull RefactoringEventData conflictsData) {
    }

    @Override
    public void undoRefactoring(@NotNull String refactoringId) {
    }

    /**
     * `StartupActivity`用于在项目打开后执行一些初始化操作。<br/>
     * 在IntelliJ IDEA启动时，会执行一系列的操作，包括加载插件、初始化项目、创建窗口等。这些操作都是由StartupActivity类来管理的。
     */
    public static class MyStartupActivity implements StartupActivity {

        /**
         * 在项目打开时运行活动
         *
         * @param project
         */
        @Override
        public void runActivity(@NotNull Project project) {
//            MyRefactoringEventListener listener = new MyRefactoringEventListener(project);
//            System.out.println("project = " + project);
//            project.getService(WebStartService.class).startWeb();
        }
    }
}
