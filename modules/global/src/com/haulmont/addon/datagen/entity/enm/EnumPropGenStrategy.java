package com.haulmont.addon.datagen.entity.enm;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum EnumPropGenStrategy implements EnumClass<String> {

    MANUAL("MANUAL"),
    RANDOM("RANDOM");

    private String id;

    EnumPropGenStrategy(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EnumPropGenStrategy fromId(String id) {
        for (EnumPropGenStrategy at : EnumPropGenStrategy.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}