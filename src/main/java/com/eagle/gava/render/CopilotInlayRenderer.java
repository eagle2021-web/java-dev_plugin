package com.eagle.gava.render;

import com.intellij.openapi.editor.EditorCustomElementRenderer;
import com.intellij.openapi.editor.Inlay;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CopilotInlayRenderer extends EditorCustomElementRenderer {
    @NotNull List<String> getContentLines();

    @Nullable Inlay<CopilotInlayRenderer> getInlay();

    @NotNull CopilotCompletionType getType();

    void setInlay(@NotNull Inlay<CopilotInlayRenderer> inlay);
}