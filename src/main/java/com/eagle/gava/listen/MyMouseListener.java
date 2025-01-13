package com.eagle.gava.listen;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class MyMouseListener implements EditorMouseListener {
//    Editor editor;
//    public MyDocumentListener(Editor editor) {
//        this.editor = editor;
//    }
    @Override
    public void mouseClicked(EditorMouseEvent e) {
        System.out.println("------mouse");
        InputEvent inputEvent = e.getMouseEvent();
        if (inputEvent instanceof MouseEvent mouseEvent) {
            if (mouseEvent.isAltDown() && mouseEvent.getButton() == MouseEvent.BUTTON1) {
                System.out.println("hello222");
            }
            Editor editor = e.getEditor();
        }
        System.out.println("other222");
    }
}
