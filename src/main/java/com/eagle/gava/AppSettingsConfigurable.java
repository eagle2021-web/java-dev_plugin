package com.eagle.gava;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.util.Objects;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "SDK: Application Settings Example";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean modified = !mySettingsComponent.getUserNameText().equals(settings.userId);
        modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
        modified |= !mySettingsComponent.getTemplateName().equals(settings.templateName);
        modified |= !Objects.equals(mySettingsComponent.getSubTemplateName(), settings.subTemplateName);
        return modified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.userId = mySettingsComponent.getUserNameText();
        settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
        settings.templateName = mySettingsComponent.getTemplateName();
        settings.subTemplateName = mySettingsComponent.getSubTemplateName();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        mySettingsComponent.setUserNameText(settings.userId);
        mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
        mySettingsComponent.setTemplateName(settings.templateName);
        mySettingsComponent.setSubTemplateName(settings.subTemplateName);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}