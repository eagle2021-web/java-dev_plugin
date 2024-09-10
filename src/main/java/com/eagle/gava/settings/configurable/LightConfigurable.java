package com.eagle.gava.settings.configurable;

import com.eagle.gava.settings.panel.LightPanel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LightConfigurable implements SearchableConfigurable {
    @Override
    public @NotNull @NonNls String getId() {
        return "";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "";
    }

    @Override
    public @Nullable JComponent createComponent() {
        LightPanel lightPanel = new LightPanel();
        return lightPanel.getjPanel();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
