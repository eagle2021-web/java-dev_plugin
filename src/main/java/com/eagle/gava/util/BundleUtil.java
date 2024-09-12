package com.eagle.gava.util;

import com.intellij.CommonBundle;
import org.codehaus.groovy.util.ReferenceBundle;

import javax.swing.text.html.Option;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.ResourceBundle;


public class BundleUtil {
    private static final String BUNDLE_NAME = "messages.keyValue";
    private static Reference<ResourceBundle> bundleRef;

    public static ResourceBundle getBundle() {
        ResourceBundle bundle;
        // 检查 Reference 是否为空或其引用的对象是否已被回收
        if (bundleRef == null || (bundle = bundleRef.get()) == null) {
            // 创建新的 ResourceBundle 实例
            bundle = ResourceBundle.getBundle(BUNDLE_NAME);
            // 将新的 ResourceBundle 实例包装在 Reference 中
            bundleRef = new SoftReference<>(bundle);
        }

        return bundle;
    }

    public static String message(String key) {
        return Optional.ofNullable(getBundle())
                .map(bundleObj -> CommonBundle.messageOrNull(bundleObj, key))
                .orElse("");
    }
}

