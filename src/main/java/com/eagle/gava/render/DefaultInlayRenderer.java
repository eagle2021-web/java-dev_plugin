package com.eagle.gava.render;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Inlay;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.JBColor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultInlayRenderer implements CopilotInlayRenderer {
    private final @NotNull List<String> lines;
    private final @NotNull String content;
    private final @NotNull CopilotCompletionType type;
    private final @NotNull TextAttributes textAttributes;
    private @Nullable Inlay<CopilotInlayRenderer> inlay;
    private int cachedWidth = -1;
    private int cachedHeight = -1;

    public DefaultInlayRenderer(@NotNull Editor editor, @NotNull EditorRequest request, @NotNull CopilotCompletionType type, @NotNull List<String> lines) {
        this.lines = replaceLeadingTabs(lines, request);
        this.type = type;
        this.content = StringUtils.join(lines, "\n");
        this.textAttributes = getTextAttributes(editor);
    }

    public @Nullable Inlay<CopilotInlayRenderer> getInlay() {
        return this.inlay;
    }



    public @NotNull CopilotCompletionType getType() {
        return this.type;
    }
    public void setInlay(@NotNull Inlay<CopilotInlayRenderer> inlay) {
        this.inlay = inlay;
    }
    public @NotNull List<String> getContentLines() {
        return this.lines;
    }

    public @Nullable @NonNls String getContextMenuGroupId(@NotNull Inlay inlay) {
        return "copilot.inlayContextMenu";
    }

    public int calcHeightInPixels(@NotNull Inlay inlay) {
        return this.cachedHeight < 0 ? (this.cachedHeight = inlay.getEditor().getLineHeight() * this.lines.size()) : this.cachedHeight;
    }

    public int calcWidthInPixels(@NotNull Inlay inlay) {
        if (this.cachedWidth < 0) {
            int width = InlayRendering.calculateWidth(inlay.getEditor(), this.content, this.lines);
            return this.cachedWidth = Math.max(1, width);
        } else {
            return this.cachedWidth;
        }
    }
    @Override
    public void paint(@NotNull Inlay inlay, @NotNull Graphics g, @NotNull Rectangle region, @NotNull TextAttributes surroundingTextAttributes) {
        Editor editor = inlay.getEditor();
        if (!editor.isDisposed()) {
            InlayRendering.renderCodeBlock(editor, this.content, this.lines, g, region, this.textAttributes);
        }
    }

    static List<String> replaceLeadingTabs(@NotNull List<String> lines, @NotNull EditorRequest request) {
        return lines.stream().map((line) -> {
            int tabCount = StringUtil.countChars(line, '\t', 0, true);
            if (tabCount > 0) {
                String tabSpaces = StringUtil.repeatSymbol(' ', tabCount * request.getTabWidth());
                return tabSpaces + line.substring(tabCount);
            } else {
                return line;
            }
        }).collect(Collectors.toList());
    }

    private static @NotNull TextAttributes getTextAttributes(@NotNull Editor editor) {
//        Color userColor = Color.CYAN;
        Color userColor = null;
        EditorColorsScheme scheme = editor.getColorsScheme();
        TextAttributes themeAttributes = scheme.getAttributes(DefaultLanguageHighlighterColors.INLAY_TEXT_WITHOUT_BACKGROUND);
        if (userColor == null && themeAttributes != null && themeAttributes.getForegroundColor() != null) {
            return themeAttributes;
        } else {
            TextAttributes customAttributes = themeAttributes != null ? themeAttributes.clone() : new TextAttributes();
            if (userColor != null) {
                customAttributes.setForegroundColor(userColor);
            }

            if (customAttributes.getForegroundColor() == null) {
                customAttributes.setForegroundColor(JBColor.GRAY);
            }

            return customAttributes;
        }
    }

    @Generated
    public @NotNull List<String> getLines() {
        return this.lines;
    }

    @Generated
    public @NotNull String getContent() {
        return this.content;
    }

    @Generated
    public void setCachedWidth(int cachedWidth) {
        this.cachedWidth = cachedWidth;
    }

    @Generated
    public void setCachedHeight(int cachedHeight) {
        this.cachedHeight = cachedHeight;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DefaultInlayRenderer)) {
            return false;
        } else {
            DefaultInlayRenderer other = (DefaultInlayRenderer)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.cachedWidth != other.cachedWidth) {
                return false;
            } else if (this.cachedHeight != other.cachedHeight) {
                return false;
            } else {
                label76: {
                    Object this$lines = this.getLines();
                    Object other$lines = other.getLines();
                    if (this$lines == null) {
                        if (other$lines == null) {
                            break label76;
                        }
                    } else if (this$lines.equals(other$lines)) {
                        break label76;
                    }

                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                label62: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label62;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$textAttributes = this.textAttributes;
                    Object other$textAttributes = other.textAttributes;
                    if (this$textAttributes == null) {
                        if (other$textAttributes == null) {
                            break label55;
                        }
                    } else if (this$textAttributes.equals(other$textAttributes)) {
                        break label55;
                    }

                    return false;
                }

                Object this$inlay = this.getInlay();
                Object other$inlay = other.getInlay();
                if (this$inlay == null) {
                    if (other$inlay != null) {
                        return false;
                    }
                } else if (!this$inlay.equals(other$inlay)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof DefaultInlayRenderer;
    }

    @Generated
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        result = result * 59 + this.cachedWidth;
        result = result * 59 + this.cachedHeight;
        Object $lines = this.getLines();
        result = result * 59 + ($lines == null ? 43 : $lines.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $textAttributes = this.textAttributes;
        result = result * 59 + ($textAttributes == null ? 43 : $textAttributes.hashCode());
        Object $inlay = this.getInlay();
        result = result * 59 + ($inlay == null ? 43 : $inlay.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        List var10000 = this.getLines();
        return "CopilotDefaultInlayRenderer(lines=" + var10000 + ", content=" + this.getContent() + ", type=" + this.getType() + ", textAttributes=" + this.textAttributes + ", cachedWidth=" + this.cachedWidth + ", cachedHeight=" + this.cachedHeight + ")";
    }
}
