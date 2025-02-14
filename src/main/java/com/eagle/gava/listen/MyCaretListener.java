package com.eagle.gava.listen;

import com.eagle.gava.factory.FieldFactory;
import com.eagle.gava.service.EditorInternal;
import com.eagle.gava.util.MethodUtil;
import com.eagle.gava.window.WindowPanel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import com.eagle.gava.render.RenderController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;

public class MyCaretListener implements CaretListener {
    private final Editor editor;

    public MyCaretListener(Editor editor) {
        this.editor = editor;
    }

    private void publish() {
//        System.out.println("editor = " + editor);
        PsiMethod method = MethodUtil.getPsiMethodAtCaret(editor);
        if (method == null) {
            return;
        }
//        FieldFactory.getInstance(method.getProject()).createHelloMethod(method.getContainingClass());
//        SubmissionPublisher<PsiMethod> transform = EditorInternal.getInstance().getPublisher(editor);
//        boolean psiMethodPublished = EditorInternal.getInstance().isPsiMethodPublished(method);
//        if (psiMethodPublished) {
//            return;
//        }
//        EditorInternal.getInstance().setPsiMethodPublish(method);
//        System.out.println("-------");
//        Consumer<SubmissionPublisher<PsiMethod>> publisherConsumer = psiMethodSubmissionPublisher -> {
//            SubmissionPublisher<PsiMethod> publisher = EditorInternal.getInstance().getPublisher(editor);
//            publisher.submit(method);
//        };
//        Optional.ofNullable(transform).ifPresent(publisherConsumer);
    }

    private static List<String> createPoem() {
        ArrayList<String> strings = new ArrayList<>();

        // 将《静夜思》的诗句逐行加入到列表中
        strings.add("《静夜思》");
        strings.add("床前明月光");
        strings.add("疑是地上霜");
        strings.add("举头望明月");
        strings.add("低头思故乡");

        // 打印列表内容以验证
        for (String line : strings) {
            System.out.println(line);
        }
        return strings;
    }

    private void testUsed(Project project){

    }
    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        publish();
//        ArrayList<String> strings = new ArrayList<>();
//        List<String> strings = createPoem();
//        RenderController.renderLines(editor, strings);
//        testUsed(event.getEditor().getProject());
    }
}
