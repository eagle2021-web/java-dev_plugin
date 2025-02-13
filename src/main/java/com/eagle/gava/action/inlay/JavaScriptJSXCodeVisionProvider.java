// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.eagle.gava.action.inlay;

import com.intellij.codeInsight.hints.*;
import com.intellij.codeInsight.hints.presentation.InlayPresentation;
import com.intellij.codeInsight.hints.presentation.MouseButton;
import com.intellij.codeInsight.hints.presentation.PresentationFactory;
import com.intellij.codeInsight.hints.presentation.SequencePresentation;
import com.intellij.codeInsight.hints.settings.InlayHintsConfigurable;
import com.intellij.codeInsight.navigation.actions.GotoDeclarationAction;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.editor.BlockInlayPriority;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.util.SmartList;
import com.intellij.util.ui.UI;
import kotlin.Unit;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Provide inlay hints for JSX language.
 */
public class JavaScriptJSXCodeVisionProvider implements InlayHintsProvider<NoSettings> {
    private static final String CODE_LENS_ID = "JavaScriptUsages";

    private static final SettingsKey<NoSettings> KEY = new SettingsKey<>(CODE_LENS_ID);

    interface InlResult {
        void onClick(@NotNull Editor editor, @NotNull PsiElement element, @NotNull MouseEvent event);

        @NotNull
        String getRegularText();

        default Icon getIcon() {
            return null;
        }

        boolean shouldClick();
    }

    @Nullable
    @Override
    public InlayHintsCollector getCollectorFor(@NotNull PsiFile file,
                                               @NotNull Editor editor,
                                               @NotNull NoSettings settings,
                                               @NotNull InlayHintsSink __) {
        return new FactoryInlayHintsCollector(editor) {
            @Override
            public boolean collect(@NotNull PsiElement element, @NotNull Editor editor, @NotNull InlayHintsSink sink) {
                if (!hintsEnabled()) {
                    return true;
                }
                if (!(element instanceof PsiMethod)) {
                    return true;
                }
                List<InlResult> hints = new SmartList<>();
                String usagesHint = "李果是個大帥哥";
                if (usagesHint != null) {
                    hints.add(new InlResult() {
                        @Override
                        public void onClick(@NotNull Editor editor, @NotNull PsiElement element, @NotNull MouseEvent event) {
                            System.out.println("22");
                        }

                        @NotNull
                        @Override
                        public String getRegularText() {
                            return "展開或者收起  "; //
                        }

                        @Override
                        public boolean shouldClick() {
                            return false;
                        }
                    });
                    hints.add(new InlResult() {
                        @Override
                        public void onClick(@NotNull Editor editor, @NotNull PsiElement element, @NotNull MouseEvent event) {
                            System.out.println("111");
                        }

                        @NotNull
                        @Override
                        public String getRegularText() {
                            return usagesHint;
                        }

                        @Override
                        public boolean shouldClick() {
                            return true;
                        }
                    });


                }

                if (!hints.isEmpty()) {
                    PresentationFactory factory = getFactory();
                    Document document = editor.getDocument();
                    int offset = PsiEditorUtil.getAnchorOffset(element);
                    int line = document.getLineNumber(offset);
                    int startOffset = document.getLineStartOffset(line);
                    int column = offset - startOffset;
                    List<InlayPresentation> presentations = new SmartList<>();

                    for (InlResult inlResult : hints) {
                        if (inlResult.getIcon() != null) {
                            presentations.add(factory.icon(inlResult.getIcon()));//));
                        }
                        presentations.add(createPresentation(factory, element, editor, inlResult));
                    }
                    SequencePresentation shiftedPresentation = new SequencePresentation(presentations);
                    InlayPresentation withSettings = addSettings(element.getProject(), factory, shiftedPresentation);
                    sink.addBlockElement(startOffset, true, true, BlockInlayPriority.CODE_VISION_USAGES, withSettings);

                }
                return true;
            }
        };
    }

    static boolean hintsEnabled() {
        return InlayHintsSettings.instance().hintsEnabled(KEY, JavaLanguage.INSTANCE);
    }


    @NotNull
    private static InlayPresentation createPresentation(@NotNull PresentationFactory factory,
                                                        @NotNull PsiElement element,
                                                        @NotNull Editor editor,
                                                        @NotNull InlResult result) {
        InlayPresentation text = factory.smallText(result.getRegularText());
        if (!result.shouldClick()) {
            return text;
        }
        return factory.referenceOnHover(text, (event, translated) -> result.onClick(editor, element, event));
    }


    // Add Settings to inlay context menu
    private static @NotNull InlayPresentation addSettings(@NotNull Project project,
                                                          @NotNull PresentationFactory factory,
                                                          @NotNull InlayPresentation presentation) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("Settings");
        item.addActionListener(e -> {
            InlayHintsConfigurable.showSettingsDialogForLanguage(project, JavaLanguage.INSTANCE);
            //, model -> model.getId().equals(CODE_LENS_ID));
        });
        popupMenu.add(item);

        return factory.onClick(presentation, MouseButton.Right, (e, __) -> {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
            return Unit.INSTANCE;
        });
    }

    @NotNull
    @Override
    public NoSettings createSettings() {
        return new NoSettings();
    }

    @NotNull
    @Override
    public @Nls String getName() {
        return "Show Usages";
    }

    public static final String RELATED_PROBLEMS_ID = "Show Usages";

    @NotNull
    @Override
    public SettingsKey<NoSettings> getKey() {
        return KEY;
    }


    @Nullable
    @Override
    public String getPreviewText() {
        return null;
    }

    @NotNull
    @Override
    public ImmediateConfigurable createConfigurable(@NotNull NoSettings settings) {
        return new ImmediateConfigurable() {
            @NotNull
            @Override
            public JComponent createComponent(@NotNull ChangeListener listener) {
                JPanel panel = UI.PanelFactory.panel(new JLabel()).
                        withComment("This will display JSX class and method usages in editor, " +
                                "provided by the React Native Console Free plugin.").createPanel();


//        JPanel panel = new JPanel();
//        panel.setVisible(true);
//        panel.add(new JLabel("This will display JSX class and method usages in editor"));
                return panel;
            }
        };
    }

    @Override
    public boolean isLanguageSupported(@NotNull Language language) {
        return true;
    }

    @Override
    public boolean isVisibleInSettings() {
        return true;
    }

}
