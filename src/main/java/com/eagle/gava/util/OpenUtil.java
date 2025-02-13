package com.eagle.gava.util;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.VisualPosition;
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

    }
    public static void openCaret(Project project, String path, int offset){
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path);
        Editor editor = PsiFinder.getEditor(project, virtualFile);
        editor.getCaretModel().moveToOffset(offset);
    }
    public static void openVisible(Project project, String path, int offset){
        // 假设存在一个方法可以将文件路径转换为 VirtualFile
        // VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path); // 这行不是标准API，需要替换
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path); // 使用自定义方法

        if (virtualFile == null || !virtualFile.isValid()) {
            return; // 处理文件无法找到或无效的情况
        }

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        fileEditorManager.openFile(virtualFile, true); // 打开文件

        // 创建 OpenFileDescriptor 来导航到指定的偏移量
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile, offset);
        if (descriptor.canNavigate()) {
            descriptor.navigate(true); // 导航到指定的偏移量

            // 获取当前打开的编辑器
            Editor editor = fileEditorManager.getSelectedTextEditor();
            if (editor != null) {
                // 获取偏移量对应的逻辑位置
                LogicalPosition logicalPosition = editor.offsetToLogicalPosition(offset);
                // 将逻辑位置转换为视觉位置（这可能会因为软换行等而有所不同）
                VisualPosition visualPosition = editor.logicalToVisualPosition(logicalPosition);

                // 手动滚动编辑器以尝试将指定位置居中（这不是严格居中的方法，但可以作为起点）
                // 你可以根据编辑器的行高和可视区域的高度来计算更精确的居中位置
                int lineHeight = editor.getLineHeight();
                int visibleLineCount = editor.getSettings().getAdditionalLinesCount();
                System.out.println("visibleLineCount = " + visibleLineCount);
                int centerY = visualPosition.line - visibleLineCount / 2;
                if (centerY < 0) {
                    centerY = 0; // 确保不会滚动到文件顶部以上
                }
                editor.getScrollingModel().scrollTo(new LogicalPosition(centerY, visualPosition.column), ScrollType.MAKE_VISIBLE);
            }
        }
    }
}
