package com.eagle.gava.service;

import com.eagle.gava.listen.Transform;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiMethod;

import java.util.concurrent.SubmissionPublisher;

public class EditorInternal {

    private EditorInternal() {

    }

    private static final EditorInternal INSTANCE = new EditorInternal();

    public static EditorInternal getInstance() {
        return INSTANCE;
    }

    public final Key<SubmissionPublisher<PsiMethod>> PsiMethodListen = Key.create("PsiMethodListen");

    public final Key<PsiMethod> METHOD_KEY_ED = Key.create("METHOD_KEY_ED");


    /**
     * 存储发布者
     */
    public void setPublisher(Editor editor, SubmissionPublisher<PsiMethod> publisherKey) {
        PsiMethodListen.set(editor, publisherKey);
    }

    /**
     * 获取发布者
     *
     * @return 发布者
     */
    public SubmissionPublisher<PsiMethod> getPublisher(Editor editor) {
        return PsiMethodListen.get(editor);
    }

    /**
     * 存储已经发布过的
     *
     * @param psiMethod 方法
     */
    public void setPsiMethodPublish(PsiMethod psiMethod) {
        METHOD_KEY_ED.set(psiMethod, psiMethod);
    }

    /**
     * 方法已经发布过
     *
     * @param psiMethod 编辑器
     * @return 是/否
     */
    public boolean isPsiMethodPublished(PsiMethod psiMethod) {
        return METHOD_KEY_ED.get(psiMethod) != null;
    }
}
