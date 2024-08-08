package com.eagle.gava.util;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtil;

public class TypeUtil {
    public static boolean isChildClassOf(PsiType psiType, String qualifiedName) {
        // 获取 psiType 的对应 PsiClass
        PsiClass psiClass = PsiUtil.resolveClassInType(psiType);

        // 如果 psiClass 为 null，说明 psiType 不是一个类类型，直接返回 false
        if (psiClass == null) {
            return false;
        }

        // 获取要比较的类
        GlobalSearchScope scope = GlobalSearchScope.allScope(psiClass.getProject());
        PsiClass targetClass = JavaPsiFacade.getInstance(psiClass.getProject()).findClass(qualifiedName, scope);

        // 如果目标类不存在，返回 false
        if (targetClass == null) {
            return false;
        }

        // 判断 psiClass 是否是 targetClass 的子类
        return isSubclass(psiClass, targetClass);
    }

    private static boolean isSubclass(PsiClass child, PsiClass parent) {
        if (child.equals(parent)) {
            return true;
        }
        // 检查 child 是否是 parent 的直接子类
        if (child.isInheritor(parent, true)) {
            return true;
        }

        // 检查 child 的超类
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
