package com.eagle.gava.factory;

import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.PsiParserFacadeImpl;
import org.jetbrains.annotations.NotNull;

public class FieldFactory {
    private Project project;
    private static FieldFactory instance;
    private PsiParserFacadeImpl psiParserFacade;

    public static FieldFactory getInstance(@NotNull Project project) {
        if (instance != null) {
            return instance;
        }
        instance = new FieldFactory();
        instance.psiParserFacade = new PsiParserFacadeImpl(project);
        return instance;
    }
}
