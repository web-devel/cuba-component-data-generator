package com.haulmont.addon.datagen.entity.number;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum NumberPropGenStrategy implements EnumClass<String> {

    MANUAL("MANUAL"),
    RANDOM("RANDOM");

    private String id;

    NumberPropGenStrategy(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static NumberPropGenStrategy fromId(String id) {
        for (NumberPropGenStrategy at : NumberPropGenStrategy.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}