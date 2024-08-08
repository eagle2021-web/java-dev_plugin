package com.eagle.gava.listen;

import com.intellij.injected.editor.EditorWindow;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.impl.ImaginaryEditor;
import com.intellij.util.concurrency.annotations.RequiresEdt;
import org.jetbrains.annotations.NotNull;

public class EditorManger {
    @RequiresEdt
    public static boolean isAvailable(@NotNull Editor editor) {
         return  !(editor instanceof EditorWindow)
                 && !(editor instanceof ImaginaryEditor)
                 && (!(editor instanceof EditorEx) ||
                 !((EditorEx)editor).isEmbeddedIntoDialogWrapper())
                 && !editor.isViewer()
                 && !editor.isOneLineMode();

    }
}
