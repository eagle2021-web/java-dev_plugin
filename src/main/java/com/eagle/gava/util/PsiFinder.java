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
        // ʹ�� VirtualFileManager �� findFileByUrl ����������Ҫ��·��ת��Ϊ URL ��ʽ
        // ͨ�������ڱ����ļ�������ʹ�� "file://" ǰ׺�����ļ��ľ���·��
        String url = "file://" + path.replace("\\", "/"); // Windows ·��������Ҫ�滻��б��Ϊ��б��

        // ע�⣺��ĳЩ����£��������Ҫȷ��·���Ǿ��Եģ�������Ŀ�Ѿ������˸��ļ����ڵ�Ŀ¼��
        // ����ļ�������Ŀ�������У�������޷�ֱ��ͨ�� VirtualFileManager ��ȡ����

        // ʹ�� VirtualFileManager �������ļ�
        VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
        System.out.println("getVirtualFile virtualFile = " + virtualFile);
        // ����ļ�����Ŀ�ڲ��ģ�������Ŀ�Ѿ��򿪣���Ҳ���Գ���ͨ�� LocalFileSystem ֱ�ӻ�ȡ
        // ��ͨ��������Ŀ�ļ����������ⲿ�ļ�
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
        // ͨ�� FileDocumentManager ��ȡ�� VirtualFile ������ Document ����
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
