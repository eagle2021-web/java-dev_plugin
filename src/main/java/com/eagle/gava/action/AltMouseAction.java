package com.eagle.gava.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.project.Project;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * ����alt + ������
 * ���hello����
 */
public class AltMouseAction extends AnAction implements EditorMouseListener {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // ����Ƿ��ڱ༭���д�����alt + ������
        System.out.println("editor222 = ����������������");
        Project project = e.getProject();
        if (project!= null) {
            Editor editor = e.getData(CommonDataKeys.EDITOR);
            if (editor!= null) {
                System.out.println("editor222 = " + editor);
                editor.addEditorMouseListener(this);
            }
        }
    }



    @Override
    public void mousePressed(EditorMouseEvent e) {
        // ����mouseClicked������������갴���¼�
//        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(EditorMouseEvent e) {
        // ��������ͷ��¼�
//        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(EditorMouseEvent e) {
        // �����������¼�
//        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(EditorMouseEvent e) {
        // ��������뿪�¼�
//        System.out.println("mouseExited");
    }
}