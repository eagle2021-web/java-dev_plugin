package com.eagle.gava.action;

import com.eagle.gava.settings.configurable.LightConfigurable;
import com.intellij.ide.actions.ShowSettingsAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vcs.changes.savedPatches.ShelfAction;
import org.jetbrains.annotations.NotNull;

public class SettingShowAction extends DumbAwareAction {

    public SettingShowAction() {
        super("打开设置", "Show", IconLoader.getIcon("/general/settings.svg", Thread.currentThread().getContextClassLoader()));
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ShowSettingsUtil.getInstance().showSettingsDialog(anActionEvent.getProject(), LightConfigurable.class);
    }
}
