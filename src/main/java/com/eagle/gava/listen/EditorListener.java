package com.eagle.gava.listen;

import com.eagle.gava.service.EditorInternal;
import com.eagle.gava.util.BundleUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.editor.ex.util.EditorUtil;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.SubmissionPublisher;

public class EditorListener implements EditorFactoryListener {
    Disposable editorDisposable = Disposer.newDisposable("eagleEditorListener");

    private void publishPsiMethodTask(@NotNull Editor editor) {
        Project project = editor.getProject();
        if (project == null || project.isDisposed() || !EditorManger.isAvailable(editor)) {
            return;
        }
        SubmissionPublisher<PsiMethod> psiMethodSubmissionPublisher = new SubmissionPublisher<>();
        Transform transformOne = new Transform() {
            @Override
            public void onNext(PsiMethod item) {
                Transform _this = this;
                new Task.Backgroundable(project, "李果最爱的人不是我") {
                    @Override
                    public void run(@NotNull ProgressIndicator progressIndicator) {
                        System.out.println("------");
                        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        submit(item);
                        System.out.println("hh");
                        _this.requestOne();
                        System.out.println("---------");
                    }
                }.queue();
            }
        };
        Transform transformTwo = new Transform() {
            @Override
            public void onNext(PsiMethod item) {
                System.out.println("two two");
                this.requestOne();
            }
        };
        EditorInternal.getInstance().setPublisher(editor, psiMethodSubmissionPublisher);

        psiMethodSubmissionPublisher.subscribe(transformOne);
        transformOne.subscribe(transformTwo);
        // 在 dispose 方法中清除订阅

    }

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
        if (project == null || project.isDisposed() || !EditorManger.isAvailable(editor)) {
            return;
        }

        publishPsiMethodTask(editor);
        String message = BundleUtil.message("hello.world");
        System.out.println("message = " + message);
        // 这行代码创建了一个名为 editorDisposable 的可丢弃对象，并为其指定了一个标识字符串 "eagleEditorListener" 。

        // 这行代码可能是使用 EditorUtil 类中的方法，将 editor 和 editorDisposable 关联起来，以进行某种资源的释放或清理操作。
        EditorUtil.disposeWithEditor(editor, editorDisposable);

        // 这部分使用 ApplicationManager 获取应用程序对象，并通过 invokeLater 方法进行异步操作。在异步操作中，
        // 为 editor 的光标模型添加了一个 CaretListener（可能用于监听光标的位置变化等事件），并再次使用了 editorDisposable 。
        // 例如，在一个图形界面应用中，当需要在后台处理一些与编辑器相关的资源释放，同时又要在合适的时候异步添加光标监听，
        // 就可能会采用这样的代码结构。这样可以确保资源的合理管理和界面操作的流畅性。
        ApplicationManager.getApplication().invokeLater(() -> {
            editor.getCaretModel().addCaretListener(new MyCaretListener(editor), editorDisposable);
        });
    }

    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        System.out.println("release");
    }
}
