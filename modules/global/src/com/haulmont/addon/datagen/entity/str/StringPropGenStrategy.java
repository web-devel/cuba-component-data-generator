package com.haulmont.addon.datagen.entity.str;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum StringPropGenStrategy implements EnumClass<String> {

    MANUAL("MANUAL"),
    FAKER("FAKER");

    private String id;

    StringPropGenStrategy(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static StringPropGenStrategy fromId(String id) {
        for (StringPropGenStrategy at : StringPropGenStrategy.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}