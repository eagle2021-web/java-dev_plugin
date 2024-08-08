package com.eagle.gava.render;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Inlay;
import com.intellij.openapi.editor.InlayModel;
import com.intellij.openapi.util.Key;

import java.util.List;
import java.util.stream.Stream;


public class RenderController {

    public static final Key<RenderController> KEY = Key.create("RenderController");
    public static final Key<Editor> KEY_RENDER_EDITOR = Key.create("RenderEditor");

    public static void avvv(Editor editor){
        List<String> lines = "aaaaaa\ndsfasdf".lines().toList();
        DefaultInlayRenderer defaultInlayRenderer = new DefaultInlayRenderer(editor, new MyEditorRequest(), CopilotCompletionType.Inline, lines);

        List<String> lines2 = "bbbbb\ndsfasdf".lines().toList();

        DefaultInlayRenderer defaultInlayRenderer2 = new DefaultInlayRenderer(editor, new MyEditorRequest(), CopilotCompletionType.Block, lines2);
        InlayModel inlayModel = editor.getInlayModel();
        inlayModel.addInlineElement(0, defaultInlayRenderer);
        inlayModel.addInlineElement(100, defaultInlayRenderer2);
    }
}
