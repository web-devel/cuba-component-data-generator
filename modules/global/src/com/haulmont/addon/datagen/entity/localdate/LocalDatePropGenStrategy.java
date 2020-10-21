package com.haulmont.addon.datagen.entity.localdate;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum LocalDatePropGenStrategy implements EnumClass<String> {

    MANUAL("MANUAL"),
    RANDOM("RANDOM");

    private String id;

    LocalDatePropGenStrategy(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static LocalDatePropGenStrategy fromId(String id) {
        for (LocalDatePropGenStrategy at : LocalDatePropGenStrategy.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}