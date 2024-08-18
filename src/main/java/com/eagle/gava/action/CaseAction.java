package com.eagle.gava.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CaseAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Display a message dialog
        Messages.showMessageDialog("My Custom Action was triggered!", "Information", Messages.getInformationIcon());
        System.out.println("e.getProject() = " + e.getProject());
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
//        e.getPresentation().setEnabledAndVisible(false);
        e.getPresentation().setEnabledAndVisible(e.getProject() != null && isJavaFile(e) );
        System.out.println("update");

        if(e.getInputEvent() instanceof KeyEvent keyEvent){
            System.out.println("Action may have been performed by a shortcut or direct keyboard input");
            System.out.println("KeyEvent key code: " + keyEvent.getKeyCode());
        }
        if (isActionPerformedByMouseClick(e)) {
            System.out.println("Action performed by clicking button or using shortcut");
            // Proceed with your code here
            isTestMethodSelected(e);
        } else {
            System.out.println("Action performed by other means");
            // Do nothing or handle the case accordingly
        }
    }
    private boolean isTestMethodSelected(@NotNull AnActionEvent e) {
        // Logic to determine if the caret is inside a method with @Test annotation
        PsiFile file = e.getData(CommonDataKeys.PSI_FILE);
        PsiElement element = e.getData(CommonDataKeys.PSI_ELEMENT);

        if (file == null || element == null) {
            return false;
        }
        if(!(element instanceof PsiMethod)){
            return false;
        }
        PsiModifierList modifierList = ((PsiMethod)element).getModifierList();
        boolean b = modifierList.hasAnnotation("org.junit.Test");
        b |= modifierList.hasAnnotation("org.junit.jupiter.api.Test");
        System.out.println("b = " + b);
        return b;

    }
    private boolean isActionPerformedByMouseClick(@NotNull AnActionEvent e) {
        return e.getInputEvent() instanceof MouseEvent;
    }

    // Method to check if the action was performed by using a keyboard shortcut
    private boolean isActionPerformedByShortcut(@NotNull AnActionEvent e) {
        return e.getInputEvent() instanceof KeyEvent;
    }

    private boolean isJavaFile(@NotNull AnActionEvent e) {
        // Logic to determine if the current file is a Java file
        // For example, check the file extension or file type
        return e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE)
                .getExtension().equalsIgnoreCase("java");
    }
}