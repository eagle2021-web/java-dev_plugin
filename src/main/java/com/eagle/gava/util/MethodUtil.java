package com.eagle.gava.util;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;

public class MethodUtil {
    public static PsiMethod getPsiMethodAtCaret(Editor editor) {
        CaretModel caretModel = editor.getCaretModel();
        int offset = caretModel.getOffset();
        Project project = editor.getProject();
        if (project == null) {
            return null;
        }
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile != null) {
            PsiElement element = psiFile.findElementAt(offset);
            return PsiTreeUtil.getParentOfType(element, PsiMethod.class, true);
        }
        return null;
    }
}
