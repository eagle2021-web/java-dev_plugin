package com.eagle.gava.factory;

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.PsiParserFacadeImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

public class FieldFactory {
    private static Project project;
    private static FieldFactory instance;
    private PsiParserFacadeImpl psiParserFacade;

    public static FieldFactory getInstance(@NotNull Project _project) {
        if (instance != null) {
            return instance;
        }
        project = _project;
        instance = new FieldFactory();
        instance.psiParserFacade = new PsiParserFacadeImpl(project);
        return instance;
    }

    // 创建 PsiMethod 的方法
    public PsiMethod createHelloMethod(PsiClass psiClass) {
        if(psiClass == null){
            return null;
        }

        PsiElementFactory factory = PsiElementFactory.getInstance(project);
        // 创建方法
        PsiMethod helloMethod = factory.createMethod("hello", factory.createTypeByFQClassName("void", GlobalSearchScope.allScope(project)));

        // 这里可以添加方法体
        helloMethod.getBody().add(factory.createStatementFromText("System.out.println(\"Hello from hello method!\");", null));
        helloMethod.getBody().add(factory.createStatementFromText("System.out.println(\"Hello from hello method2!\");", null));

        WriteCommandAction.runWriteCommandAction(psiClass.getProject(), () -> {
            // 将方法添加到指定的类中
            psiClass.addBefore(helloMethod, psiClass.getLastChild());
        });

        return helloMethod;
    }
}
