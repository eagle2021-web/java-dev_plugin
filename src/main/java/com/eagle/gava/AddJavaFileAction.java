package com.eagle.gava;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiParserFacadeImpl;

public class AddJavaFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        DataContext dataContext = e.getDataContext();

        VirtualFile[] virtualFiles = PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        VirtualFile projectDirectory = ProjectRootManager.getInstance(project).getContentRoots()[0];
        if (virtualFiles == null || virtualFiles.length == 0) {
            return;
        }
        if (virtualFiles[0].isDirectory()) {
            VirtualFile selectedDirectory = virtualFiles[0];
            PsiManager psiManager = PsiManager.getInstance(project);
            PsiDirectory psiDirectory = psiManager.findDirectory(selectedDirectory);

            if (psiDirectory != null) {
                String className = "MyNewClass";
                createJavaFile(psiDirectory, className, project);

                System.out.println("New Java file created in directory: " + selectedDirectory.getPath());
            }
        } else {
            VirtualFile parentDirectory = virtualFiles[0].getParent();
            if (parentDirectory != null) {
            }
        }
    }

    private void createJavaFile(PsiDirectory directory, String fileName, Project project) {
        WriteCommandAction.runWriteCommandAction(project, () -> {
            FileType fileType = FileTypeManager.getInstance().getFileTypeByExtension("java");
            String fileContent = "public class " + fileName + " {\n"
                    + "    // ... Your file content here\n"
                    + "}\n";
            PsiFile file = null;
            if (fileType instanceof LanguageFileType) {
                file = PsiFileFactory.getInstance(project).createFileFromText(
                        fileName + ".java",
                        (LanguageFileType) fileType,
                        fileContent
                );
            }
            if (file != null) {
                directory.add(file);
            }

            VirtualFile virtualDirectory = directory.getVirtualFile();
            if (virtualDirectory != null) {
                virtualDirectory.refresh(false, false);
            }
        });
    }
}