package com.eagle.gava.enums;

public enum SubTemplateEnum {
    A1("A", "A1"),
    A2("A", "A2"),
    B1("B", "B1"),
    B2("B", "B2");
    private final String key;
    private final String value;
    private SubTemplateEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
