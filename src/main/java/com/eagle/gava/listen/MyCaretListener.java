package com.eagle.gava.listen;

import com.eagle.gava.service.EditorInternal;
import com.eagle.gava.util.MethodUtil;
import com.eagle.gava.util.TypeUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;
import com.eagle.gava.render.RenderController;

import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;

public class MyCaretListener implements CaretListener {
    private final Editor editor;

    public MyCaretListener(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        System.out.println("editor = " + editor);
        PsiMethod method = MethodUtil.getPsiMethodAtCaret(editor);
        if (method == null) {
            return;
        }
        PsiParameterList parameterList = method.getParameterList();
        PsiParameter[] parameters = parameterList.getParameters();
        for (PsiParameter parameter : parameters) {
            PsiType type = parameter.getType();
            System.out.println("type.getCanonicalText() = " + type.getCanonicalText());
            System.out.println("type.getPresentableText() = " + type.getPresentableText());
            boolean isList = TypeUtil.isList(type);
            System.out.println("param isList = " + isList);
            boolean isSet = TypeUtil.isSet(type);
            System.out.println("isSet = " + isSet);
        }
        SubmissionPublisher<PsiMethod> transform = EditorInternal.getInstance().getPublisher(editor);
        boolean psiMethodPublished = EditorInternal.getInstance().isPsiMethodPublished(method);
        if (psiMethodPublished) {
            return;
        }
        EditorInternal.getInstance().setPsiMethodPublish(method);
        System.out.println("-------");
        Consumer<SubmissionPublisher<PsiMethod>> publisherConsumer = psiMethodSubmissionPublisher -> {
            SubmissionPublisher<PsiMethod> publisher = EditorInternal.getInstance().getPublisher(editor);
            publisher.submit(method);
        };
        Optional.ofNullable(transform).ifPresent(publisherConsumer);
    }
}
