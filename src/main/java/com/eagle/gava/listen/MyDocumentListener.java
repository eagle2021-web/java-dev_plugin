package com.eagle.gava.listen;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import org.jetbrains.annotations.NotNull;

public class MyDocumentListener implements DocumentListener {

    public void documentChanged(@NotNull DocumentEvent event) {
        System.out.println("event.getMoveOffset() = " + event.getMoveOffset());
    }
}
