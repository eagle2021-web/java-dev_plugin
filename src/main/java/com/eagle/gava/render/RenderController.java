package com.eagle.gava.render;

import com.eagle.gava.util.MyStringUtil;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static com.eagle.gava.entity.cons.SpaceConst.SPACE8;


public class RenderController {

    public static final Key<RenderController> KEY = Key.create("RenderController");

    public static final Key<List<DefaultInlayRenderer>> KEY_RENDER_EDITOR = Key.create("RenderEditor");

    @NotNull
    public static List<CopilotInlayRenderer> collectInlays(@NotNull Editor editor, int startOffset, int endOffset) {
        InlayModel model = editor.getInlayModel();
        ArrayList<Inlay<?>> inlays = new ArrayList();
        inlays.addAll(model.getInlineElementsInRange(startOffset, endOffset));
        inlays.addAll(model.getAfterLineEndElementsInRange(startOffset, endOffset));
        inlays.addAll(model.getBlockElementsInRange(startOffset, endOffset));
        ArrayList<CopilotInlayRenderer> renderers = new ArrayList();
        Iterator var7 = inlays.iterator();

        while (var7.hasNext()) {
            Inlay<?> inlay = (Inlay) var7.next();
            if (inlay.getRenderer() instanceof CopilotInlayRenderer renderer) {
                System.out.println("inlay = " + inlay);
//                renderer.setInlay(inlay);
                renderers.add(renderer);
            }
        }

        return renderers;
    }

    private static boolean dispose(Editor editor) {
        Document document = editor.getDocument();
        int textLength = document.getTextLength();
        List<CopilotInlayRenderer> copilotInlayRenderers = collectInlays(editor, 0, textLength);
        System.out.println("copilotInlayRenderers.size() = " + copilotInlayRenderers.size());
        for (CopilotInlayRenderer defaultInlayRenderer : copilotInlayRenderers) {
            Inlay<CopilotInlayRenderer> inlay = defaultInlayRenderer.getInlay();
            System.out.println("inlay = " + inlay);
            if (inlay != null) {
                Disposer.dispose(inlay);
            }
        }

        return !copilotInlayRenderers.isEmpty();
    }

    public static void renderLines(Editor editor, List<String> lines) {
        boolean ex = dispose(editor);
        if (lines == null || lines.isEmpty()) {
            return;
        }

        InlayModel inlayModel = editor.getInlayModel();
        int offset = editor.getCaretModel().getOffset();
        ArrayList<DefaultInlayRenderer> renderList = new ArrayList<>();
        String lineOne = lines.get(0);
        List<String> first = List.of(lineOne);
        DefaultInlayRenderer defaultInlayRenderer = new DefaultInlayRenderer(editor, new MyEditorRequest(), CopilotCompletionType.Inline, first);
        renderList.add(defaultInlayRenderer);

        if (lines.size() > 1) {
            Document document = editor.getDocument();
            int lineNumber = document.getLineNumber(offset);
            int lineStartOffset = document.getLineStartOffset(lineNumber);
            String spaceStr = MyStringUtil.createString(offset - lineStartOffset);

            List<String> tempSubList = lines.subList(1, lines.size());
            List<String> blockLines = MyStringUtil.appendPrefix(tempSubList, spaceStr);

            DefaultInlayRenderer defaultInlayRenderer2 = new DefaultInlayRenderer(editor, new MyEditorRequest(), CopilotCompletionType.Block, blockLines);
            renderList.add(defaultInlayRenderer2);
        }

        int index = 0;
        for (DefaultInlayRenderer renderer : renderList) {
            Inlay<CopilotInlayRenderer> inlay = switch (renderer.getType()) {
                case Inline -> inlayModel.addInlineElement(offset, true, Integer.MAX_VALUE - index, renderer);
                case AfterLineEnd -> inlayModel.addAfterLineEndElement(offset, true, renderer);
                case Block -> inlayModel.addBlockElement(offset, true, false, Integer.MAX_VALUE - index, renderer);
            };
            if (inlay != null) {
                renderer.setInlay(inlay);
            }
            index++;
        }

    }
}
