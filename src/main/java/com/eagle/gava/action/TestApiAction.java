package com.eagle.gava.action;

import com.eagle.gava.factory.FieldFactory;
import com.eagle.gava.util.MethodUtil;
import com.eagle.gava.util.OpenUtil;
import com.eagle.gava.util.PsiFinder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;


public class TestApiAction extends AnAction {
    static String pat = "C:\\Users\\Administrator\\IdeaProjects\\untitled2\\src\\test\\java\\HTest.java";

    public static void checkVir(Project project) {
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, pat);
        System.out.println("checkVir virtualFile = " + virtualFile);
    }

    public static void open(Project project) {
        OpenUtil.open(project, pat, 2110);
    }

    private static void addMethod(Editor editor, Project project) {
        // ʹ�� getData ������ȡ��ǰ�༭��

        if (editor != null) {
            // ��ȡ���λ�õ� PsiMethod
            PsiMethod method = MethodUtil.getPsiMethodAtCaret(editor);
            if (method != null) {
                System.out.println("��ȡ���ķ���: " + method.getName());
                FieldFactory.getInstance(project).createHelloMethod(method.getContainingClass());
            } else {
                System.out.println("���λ��û�з���");
            }
        } else {
            System.out.println("û���ҵ���ı༭��");
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // ��ȡ��ǰ��Ŀ
        Project project = anActionEvent.getProject();
        if (project == null) {
            return; // ���û����Ŀ��ֱ�ӷ���
        }
        checkVir(project);
        open(project);

    }
}
