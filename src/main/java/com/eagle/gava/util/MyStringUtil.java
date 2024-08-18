package com.eagle.gava.util;

import java.util.List;

public class MyStringUtil {
    /**
     * 创建空字符串，长度为count
     *
     * @param count 长度
     * @return 疮毒为count的字符串
     */
    public static String createString(int count) {
        if (count < 0) {
            count = 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    public static List<String> appendPrefix(List<String> lines, String pre) {
        return lines.stream().map(line -> pre + line).toList();
    }
}
