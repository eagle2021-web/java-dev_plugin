package com.eagle.gava.render;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public interface EditorRequest extends Cancellable {

//    boolean equalsRequest(@NotNull EditorRequest var1);
//
//    @NotNull String getDocumentContent();
//
//    @NotNull LanguageInfo getFileLanguage();
//
//    @NotNull Project getProject();
//
//    int getOffset();
//
//    boolean isUseTabIndents();

    default int getTabWidth() {
        return 4;
    };

//    int getRequestId();
//
//    int getDocumentVersion();
//
//    Disposable getDisposable();
//
//    long getRequestTimestamp();
//
//    long getDocumentModificationSequence();
}
