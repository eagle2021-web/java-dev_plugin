package com.eagle.gava.util;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class OpenUtil {
    public static void open(Project project, String path, int offset){
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path);
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        FileEditor[] fileEditors = fileEditorManager.openFile(virtualFile, true);
        // 打开文件编辑器
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile, offset);
        Editor editor = fileEditorManager.openTextEditor(descriptor, true);

        // 打开文件
        // 定位到offset行
        // 显示
    }
}
