package com.eagle.gava.action;

import com.eagle.gava.factory.FieldFactory;
import com.eagle.gava.listen.EditorListener;
import com.eagle.gava.util.MethodUtil;
import com.eagle.gava.util.OpenUtil;
import com.eagle.gava.util.PsiFinder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiMethod;
import com.vladsch.flexmark.util.data.DataKey;
import org.jetbrains.annotations.NotNull;


public class AddHelloAction extends AnAction {
    static String pat = "D:\\projects\\java\\dev_plugin\\src\\test\\java\\com\\ava\\Hel.java";

    public static void checkVir(Project project) {
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, pat);
        System.out.println("checkVir virtualFile = " + virtualFile);
    }

    public static void open(Project project) {
        OpenUtil.open(project, pat, 0);
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

        // ʹ�� getData ������ȡ��ǰ�༭��
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
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
        PsiMethod method = MethodUtil.getPsiMethodAtCaret(editor);
        VirtualFile virtualFile = method.getContainingClass().getContainingFile().getVirtualFile();
        System.out.println("virtualFile = " + virtualFile);
        Editor editor1 = PsiFinder.getEditor(project, virtualFile);
        System.out.println(editor1);
    }
}
