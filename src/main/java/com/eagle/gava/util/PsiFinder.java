package com.eagle.gava.util;

import com.esotericsoftware.kryo.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;

public class PsiFinder {
    @Nullable
    public static VirtualFile getVirtualFile(Project project, String path) {
        // 使用 VirtualFileManager 的 findFileByUrl 方法，但需要将路径转换为 URL 格式
        // 通常，对于本地文件，可以使用 "file://" 前缀加上文件的绝对路径
        String url = "file://" + path.replace("\\", "/"); // Windows 路径可能需要替换反斜杠为正斜杠

        // 注意：在某些情况下，你可能需要确保路径是绝对的，并且项目已经索引了该文件所在的目录。
        // 如果文件不在项目的索引中，你可能无法直接通过 VirtualFileManager 获取它。

        // 使用 VirtualFileManager 来查找文件
        VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
        System.out.println("getVirtualFile virtualFile = " + virtualFile);
        // 如果文件是项目内部的，并且项目已经打开，你也可以尝试通过 LocalFileSystem 直接获取
        // 这通常用于项目文件，而不是外部文件
        if (virtualFile == null && project.isOpen()) {
            LocalFileSystem localFileSystem = LocalFileSystem.getInstance();
            virtualFile = localFileSystem.findFileByPath(path);
        }

        return virtualFile;
    }

    public static Editor getEditor(Project project, VirtualFile virtualFile) {
        if (project == null || virtualFile == null) {
            return null;
        }
        // 通过 FileDocumentManager 获取与 VirtualFile 关联的 Document 对象
        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        com.intellij.openapi.editor.Document document = fileDocumentManager.getDocument(virtualFile);
        if (document == null) {
            return null;
        }
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        FileEditor[] editors = fileEditorManager.getEditors(virtualFile);
        System.out.println("editors.length = " + editors.length);
        if (editors.length > 0 && editors[0] instanceof TextEditor textEditor) {
            return textEditor.getEditor();
        }
        return null;
    }
}
