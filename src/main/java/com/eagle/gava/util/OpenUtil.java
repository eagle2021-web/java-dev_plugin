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
        // ���ļ��༭��
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile, offset);
        Editor editor = fileEditorManager.openTextEditor(descriptor, true);

    }
    public static void openCaret(Project project, String path, int offset){
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path);
        Editor editor = PsiFinder.getEditor(project, virtualFile);
        editor.getCaretModel().moveToOffset(offset);
    }
    public static void openVisible(Project project, String path, int offset){
        // �������һ���������Խ��ļ�·��ת��Ϊ VirtualFile
        // VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path); // ���в��Ǳ�׼API����Ҫ�滻
        VirtualFile virtualFile = PsiFinder.getVirtualFile(project, path); // ʹ���Զ��巽��

        if (virtualFile == null || !virtualFile.isValid()) {
            return; // �����ļ��޷��ҵ�����Ч�����
        }

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        fileEditorManager.openFile(virtualFile, true); // ���ļ�

        // ���� OpenFileDescriptor ��������ָ����ƫ����
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile, offset);
        if (descriptor.canNavigate()) {
            descriptor.navigate(true); // ������ָ����ƫ����

            // ��ȡ��ǰ�򿪵ı༭��
            Editor editor = fileEditorManager.getSelectedTextEditor();
            if (editor != null) {
                // ��ȡƫ������Ӧ���߼�λ��
                LogicalPosition logicalPosition = editor.offsetToLogicalPosition(offset);
                // ���߼�λ��ת��Ϊ�Ӿ�λ�ã�����ܻ���Ϊ���еȶ�������ͬ��
                VisualPosition visualPosition = editor.logicalToVisualPosition(logicalPosition);

                // �ֶ������༭���Գ��Խ�ָ��λ�þ��У��ⲻ���ϸ���еķ�������������Ϊ��㣩
                // ����Ը��ݱ༭�����иߺͿ�������ĸ߶����������ȷ�ľ���λ��
                int lineHeight = editor.getLineHeight();
                int visibleLineCount = editor.getSettings().getAdditionalLinesCount();
                System.out.println("visibleLineCount = " + visibleLineCount);
                int centerY = visualPosition.line - visibleLineCount / 2;
                if (centerY < 0) {
                    centerY = 0; // ȷ������������ļ���������
                }
                editor.getScrollingModel().scrollTo(new LogicalPosition(centerY, visualPosition.column), ScrollType.MAKE_VISIBLE);
            }
        }
    }
}
