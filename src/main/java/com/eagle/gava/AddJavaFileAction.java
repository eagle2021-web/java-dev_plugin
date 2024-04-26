package com.eagle.gava;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.directory;

public class AddJavaFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        // 获取数据上下文，这里包含有关事件的所有数据
        DataContext dataContext = e.getDataContext();

        // 从数据上下文中获取选中的虚拟文件或目录
        VirtualFile[] virtualFiles = PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        VirtualFile projectDirectory = ProjectRootManager.getInstance(project).getContentRoots()[0];
        if (virtualFiles == null || virtualFiles.length == 0) {
            // 如果没有选中的文件或目录，则可能需要处理默认情况
            // 例如，使用项目的根目录
            return;
        }
        // 假设用户点击的是一个目录
        if (virtualFiles[0].isDirectory()) {
            // 这里我们获得了用户点击的目录的 VirtualFile 对象
            VirtualFile selectedDirectory = virtualFiles[0];

            // 接下来，您可以使用 PsiManager 来获取 PsiDirectory 对象
            PsiManager psiManager = PsiManager.getInstance(project);
            PsiDirectory psiDirectory = psiManager.findDirectory(selectedDirectory);

            if (psiDirectory != null) {
                // 现在您可以在 psiDirectory 上进行操作，比如创建新文件
                String className = "MyNewClass";
                createJavaFile(psiDirectory, className, project);

                System.out.println("New Java file created in directory: " + selectedDirectory.getPath());
            }
        } else {
            // 如果用户点击的是一个文件，而不是目录，您可能想要在其父目录中创建新文件
            // 这里您可以获取文件的父目录
            VirtualFile parentDirectory = virtualFiles[0].getParent();
            if (parentDirectory != null) {
                // 使用父目录创建新文件
                // ...
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