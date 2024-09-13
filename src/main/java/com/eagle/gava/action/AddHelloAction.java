package com.eagle.gava.action;

import com.eagle.gava.factory.FieldFactory;
import com.eagle.gava.util.MethodUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiMethod;
import com.vladsch.flexmark.util.data.DataKey;
import org.jetbrains.annotations.NotNull;


public class AddHelloAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // 获取当前项目
        Project project = anActionEvent.getProject();
        if (project == null) {
            return; // 如果没有项目，直接返回
        }

        // 使用 getData 方法获取当前编辑器
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            // 获取光标位置的 PsiMethod
            PsiMethod method = MethodUtil.getPsiMethodAtCaret(editor);
            if (method != null) {
                System.out.println("获取到的方法: " + method.getName());
                FieldFactory.getInstance(project).createHelloMethod(method.getContainingClass());
            } else {
                System.out.println("光标位置没有方法");
            }
        } else {
            System.out.println("没有找到活动的编辑器");
        }
    }
}
