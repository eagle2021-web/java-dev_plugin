package com.eagle.gava.listen;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.editor.ex.util.EditorUtil;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import org.apache.tools.ant.taskdefs.Transform;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class EditorListener implements EditorFactoryListener {


    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        Editor editor = event.getEditor();
        Document document = editor.getDocument();
        VirtualFile file = FileDocumentManager.getInstance().getFile(document);
        if (file == null || !file.getFileType().getName().equalsIgnoreCase("JAVA")) {
            return;
        }
        System.out.println("file = " + file);
        Project project = editor.getProject();
        if (project == null && project.isDisposed() && !EditorManger.isAvailable(editor)) {
            return;
        }
        Disposable editorDisposable = Disposer.newDisposable("eagleEditorListener");
        EditorUtil.disposeWithEditor(editor, editorDisposable);
        System.out.println("-----------");
        ReadAction.nonBlocking(() -> {
            System.out.println("-----------");

            System.out.println("------222-----");
            return null;
        }).submit(new Executor() {
            @Override
            public void execute(@NotNull Runnable command) {
                command.run();
            }
        });
        ApplicationManager.getApplication().invokeLater(() -> {
            editor.getCaretModel().addCaretListener(new MyCaretListener(editor), editorDisposable);
            System.out.println("i222222nvoke");
        });
    }

    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        System.out.println("release");
    }
}
