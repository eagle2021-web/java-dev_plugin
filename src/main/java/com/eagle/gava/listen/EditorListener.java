package com.eagle.gava.listen;

import com.eagle.gava.service.EditorInternal;
import com.eagle.gava.util.BundleUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.editor.ex.util.EditorUtil;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

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
                new Task.Backgroundable(project, "�������˲�����") {
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
        // �� dispose �������������

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
        // ���д��봴����һ����Ϊ editorDisposable �Ŀɶ������󣬲�Ϊ��ָ����һ����ʶ�ַ��� "eagleEditorListener" ��

        // ���д��������ʹ�� EditorUtil ���еķ������� editor �� editorDisposable �����������Խ���ĳ����Դ���ͷŻ����������
        EditorUtil.disposeWithEditor(editor, editorDisposable);

        // �ⲿ��ʹ�� ApplicationManager ��ȡӦ�ó�����󣬲�ͨ�� invokeLater ���������첽���������첽�����У�
        // Ϊ editor �Ĺ��ģ�������һ�� CaretListener���������ڼ�������λ�ñ仯���¼��������ٴ�ʹ���� editorDisposable ��
        // ���磬��һ��ͼ�ν���Ӧ���У�����Ҫ�ں�̨����һЩ��༭����ص���Դ�ͷţ�ͬʱ��Ҫ�ں��ʵ�ʱ���첽��ӹ�������
        // �Ϳ��ܻ���������Ĵ���ṹ����������ȷ����Դ�ĺ������ͽ�������������ԡ�
        ApplicationManager.getApplication().invokeLater(() -> {
            editor.getCaretModel().addCaretListener(new MyCaretListener(editor), editorDisposable);
            editor.addEditorMouseListener(new MyMouseListener(), editorDisposable);
        });
    }

    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        System.out.println("release");
    }
}
