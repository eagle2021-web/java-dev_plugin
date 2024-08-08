package com.eagle.gava.util;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtil;

public class TypeUtil {
    public static boolean isChildClassOf(PsiType psiType, String qualifiedName) {
        // ��ȡ psiType �Ķ�Ӧ PsiClass
        PsiClass psiClass = PsiUtil.resolveClassInType(psiType);

        // ��� psiClass Ϊ null��˵�� psiType ����һ�������ͣ�ֱ�ӷ��� false
        if (psiClass == null) {
            return false;
        }

        // ��ȡҪ�Ƚϵ���
        GlobalSearchScope scope = GlobalSearchScope.allScope(psiClass.getProject());
        PsiClass targetClass = JavaPsiFacade.getInstance(psiClass.getProject()).findClass(qualifiedName, scope);

        // ���Ŀ���಻���ڣ����� false
        if (targetClass == null) {
            return false;
        }

        // �ж� psiClass �Ƿ��� targetClass ������
        return isSubclass(psiClass, targetClass);
    }

    private static boolean isSubclass(PsiClass child, PsiClass parent) {
        if (child.equals(parent)) {
            return true;
        }
        // ��� child �Ƿ��� parent ��ֱ������
        if (child.isInheritor(parent, true)) {
            return true;
        }

        // ��� child �ĳ���
        PsiClass superClass = child.getSuperClass();
        while (superClass != null) {
            if (superClass.isInheritor(parent, true)) {
                return true;
            }
            superClass = superClass.getSuperClass();
        }

        return false;
    }

    public static boolean isList(PsiType psiType){
        return isChildClassOf(psiType, "java.util.List");
    }

    public static boolean isSet(PsiType psiType){
        return isChildClassOf(psiType, "java.util.Set");
    }
}
