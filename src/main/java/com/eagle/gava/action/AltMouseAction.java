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
 * 监听alt + 鼠标左键
 * 输出hello即可
 */
public class AltMouseAction extends AnAction implements EditorMouseListener {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 检查是否在编辑器中触发了alt + 鼠标操作
        System.out.println("editor222 = ········");
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
        // 类似mouseClicked方法，处理鼠标按下事件
//        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(EditorMouseEvent e) {
        // 处理鼠标释放事件
//        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(EditorMouseEvent e) {
        // 处理鼠标进入事件
//        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(EditorMouseEvent e) {
        // 处理鼠标离开事件
//        System.out.println("mouseExited");
    }
}