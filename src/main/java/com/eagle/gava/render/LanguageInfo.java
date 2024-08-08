package com.eagle.gava.render;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.intellij.lang.Language;
import java.util.Locale;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public final class LanguageInfo {
    private final @NotNull Language language;

    public LanguageInfo(@NotNull Language language) {
        this.language = language;
    }

    public @NotNull String getId() {
        return this.language.getID().toLowerCase(Locale.ENGLISH);
    }

    @Generated
    public @NotNull Language getLanguage() {
        return this.language;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LanguageInfo)) {
            return false;
        } else {
            LanguageInfo other = (LanguageInfo)o;
            Object this$language = this.getLanguage();
            Object other$language = other.getLanguage();
            if (this$language == null) {
                if (other$language != null) {
                    return false;
                }
            } else if (!this$language.equals(other$language)) {
                return false;
            }

            return true;
        }
    }

    @Generated
    public int hashCode() {
        int result = 1;
        Object $language = this.getLanguage();
        result = result * 59 + ($language == null ? 43 : $language.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "LanguageInfo(language=" + this.getLanguage() + ")";
    }
}
