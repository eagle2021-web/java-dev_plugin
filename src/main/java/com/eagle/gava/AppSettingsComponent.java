package com.eagle.gava;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppSettingsComponent implements PersistentStateComponent<AppSettingsComponent.State> {

    private final JPanel myMainPanel;
    private final JBTextField myUserNameText = new JBTextField();
    private final JBCheckBox myIdeaUserStatus = new JBCheckBox("Do you use IntelliJ IDEA? ");
    private final JComboBox<String> cascadingComboBox =
            new JComboBox<>(new String[]{"A", "B"});

    private JComboBox<String> cascadingComboBox1 =
            new JComboBox<>(new String[]{"A1", "A2"});

    private State myState = new State();

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        XmlSerializerUtil.copyBean(state, myState);
        updateCascadingComponents(); // Update cascading components after loading state
    }

    // State class to store the selected values
    public static class State {
        public String cascadingComboBoxSelectedItem = "A";
        public String cascadingComboBox1SelectedItem = "A1";

        // Default constructor
        public State() {
        }
    }


    public AppSettingsComponent() {
        cascadingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCascadingComponents();
            }
        });
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter user name: "), myUserNameText, 1, false)
                .addLabeledComponent(new JBLabel("aaa"), cascadingComboBox, 1, false)
                .addLabeledComponent(new JBLabel("bbb"), cascadingComboBox1, 1, false)
                .addComponent(myIdeaUserStatus, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private void updateCascadingComponents() {
        String selectedOption = myState.cascadingComboBoxSelectedItem;
        cascadingComboBox.setSelectedItem(selectedOption);
        cascadingComboBox1.removeAllItems();
        if ("A".equals(selectedOption)) {
            cascadingComboBox1.addItem("A1");
            cascadingComboBox1.addItem("A2");
        } else if ("B".equals(selectedOption)) {
            cascadingComboBox1.addItem("B1");
            cascadingComboBox1.addItem("B2");
        }
        cascadingComboBox1.setSelectedItem(myState.cascadingComboBox1SelectedItem);
    }
    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return myUserNameText;
    }

    @NotNull
    public String getUserNameText() {
        return myUserNameText.getText();
    }

    public void setUserNameText(@NotNull String newText) {
        myUserNameText.setText(newText);
    }

    public boolean getIdeaUserStatus() {
        return myIdeaUserStatus.isSelected();
    }

    public void setIdeaUserStatus(boolean newStatus) {
        myIdeaUserStatus.setSelected(newStatus);
    }

}
