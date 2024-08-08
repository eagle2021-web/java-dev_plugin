package com.eagle.gava.render;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.intellij.ide.ui.AntialiasingType;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.editor.impl.FontInfo;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.Key;
import com.intellij.ui.paint.EffectPainter2D;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.UIUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class InlayRendering {
    private static final Key<Map<Font, FontMetrics>> KEY_CACHED_FONTMETRICS = Key.create("copilot.editorFontMetrics");
    private static final @Nullable Method getEditorFontSize2DMethod;

    private InlayRendering() {
    }

    static int calculateWidth(@NotNull Editor editor, @NotNull String text, @NotNull List<String> textLines) {
        FontMetrics metrics = fontMetrics(editor, getFont(editor, text));
        int maxWidth = 0;

        String line;
        for(Iterator var5 = textLines.iterator(); var5.hasNext(); maxWidth = Math.max(maxWidth, metrics.stringWidth(line))) {
            line = (String)var5.next();
        }

        return maxWidth;
    }

    static void renderCodeBlock(@NotNull Editor editor, @NotNull String content, @NotNull List<String> contentLines, @NotNull Graphics g, @NotNull Rectangle2D region, @NotNull TextAttributes attributes) {
        if (!content.isEmpty() && !contentLines.isEmpty()) {
            Rectangle clipBounds = g.getClipBounds();
            Graphics2D g2 = (Graphics2D)g.create();
            GraphicsUtil.setupAAPainting(g2);
            Font font = getFont(editor, content);
            g2.setFont(font);
            FontMetrics metrics = fontMetrics(editor, font);
            double lineHeight = (double)editor.getLineHeight();
            double fontBaseline = Math.ceil(font.createGlyphVector(metrics.getFontRenderContext(), "Alb").getVisualBounds().getHeight());
            double linePadding = (lineHeight - fontBaseline) / 2.0;
            double offsetX = region.getX();
            double offsetY = region.getY() + fontBaseline + linePadding;
            int lineOffset = 0;
            g2.setClip(clipBounds != null && !clipBounds.equals(region) ? region.createIntersection(clipBounds) : region);

            for(Iterator var21 = contentLines.iterator(); var21.hasNext(); lineOffset += (int)lineHeight) {
                String line = (String)var21.next();
                renderBackground(g2, attributes, offsetX, region.getY() + (double)lineOffset, region.getWidth(), lineHeight);
                g2.setColor(attributes.getForegroundColor());
                g2.drawString(line, (float)offsetX, (float)(offsetY + (double)lineOffset));
                if (editor instanceof EditorImpl) {
                    renderEffects(g2, offsetX, offsetY + (double)lineOffset, (double)metrics.stringWidth(line), ((EditorImpl)editor).getCharHeight(), ((EditorImpl)editor).getDescent(), attributes, font);
                }
            }

            g2.dispose();
        }
    }

    private static void renderBackground(@NotNull Graphics2D g, @NotNull TextAttributes attributes, double x, double y, double width, double height) {
        Color color = attributes.getBackgroundColor();
        if (color != null) {
            g.setColor(color);
            g.fillRoundRect((int)x, (int)y, (int)width, (int)height, 1, 1);
        }

    }

    private static void renderEffects(@NotNull Graphics2D g, double x, double baseline, double width, int charHeight, int descent, @NotNull TextAttributes textAttributes, @Nullable Font font) {
        Color effectColor = textAttributes.getEffectColor();
        if (effectColor != null) {
            EffectType effectType = textAttributes.getEffectType();
            if (effectType != null) {
                g.setColor(effectColor);
                switch (effectType) {
                    case LINE_UNDERSCORE:
                        EffectPainter2D.LINE_UNDERSCORE.paint(g, x, baseline, width, (double)descent, font);
                        break;
                    case BOLD_LINE_UNDERSCORE:
                        EffectPainter2D.BOLD_LINE_UNDERSCORE.paint(g, x, baseline, width, (double)descent, font);
                        break;
                    case STRIKEOUT:
                        EffectPainter2D.STRIKE_THROUGH.paint(g, x, baseline, width, (double)charHeight, font);
                        break;
                    case WAVE_UNDERSCORE:
                        EffectPainter2D.WAVE_UNDERSCORE.paint(g, x, baseline, width, (double)descent, font);
                        break;
                    case BOLD_DOTTED_LINE:
                        EffectPainter2D.BOLD_DOTTED_UNDERSCORE.paint(g, x, baseline, width, (double)descent, font);
                    case BOXED:
                }
            }
        }

    }

    private static FontMetrics fontMetrics(@NotNull Editor editor, @NotNull Font font) {
        FontRenderContext editorContext = FontInfo.getFontRenderContext(editor.getContentComponent());
        FontRenderContext context = new FontRenderContext(editorContext.getTransform(), AntialiasingType.getKeyForCurrentScope(false), editorContext.getFractionalMetricsHint());
        Map<Font, FontMetrics> cachedMap = (Map)KEY_CACHED_FONTMETRICS.get(editor, Collections.emptyMap());
        FontMetrics fontMetrics = (FontMetrics)cachedMap.get(font);
        if (fontMetrics == null || !context.equals(fontMetrics.getFontRenderContext())) {
            fontMetrics = FontInfo.getFontMetrics(font, context);
            KEY_CACHED_FONTMETRICS.set(editor, Maps.merge(new Map[]{cachedMap, Map.of(font, fontMetrics)}));
        }

        return fontMetrics;
    }

    private static @NotNull Font getFont(@NotNull Editor editor, @NotNull String text) {
        Font font = editor.getColorsScheme().getFont(EditorFontType.PLAIN).deriveFont(2);
        Font fallbackFont = UIUtil.getFontWithFallbackIfNeeded(font, text);
        return fallbackFont.deriveFont(fontSize(editor));
    }

    static float fontSize(@NotNull Editor editor) {
        EditorColorsScheme scheme = editor.getColorsScheme();
        if (getEditorFontSize2DMethod != null) {
            try {
                return (Float)getEditorFontSize2DMethod.invoke(scheme);
            } catch (InvocationTargetException | IllegalAccessException var3) {
            }
        }

        return (float)scheme.getEditorFontSize();
    }

    static {
        Method method = null;
        if (ApplicationInfo.getInstance().getBuild().getBaselineVersion() >= 221) {
            try {
                method = EditorColorsScheme.class.getMethod("getEditorFontSize2D");
            } catch (NoSuchMethodException var2) {
            }
        }

        getEditorFontSize2DMethod = method;
    }
}
