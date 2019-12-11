package com.haulmont.addon.datagen.entity.bool;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum BooleanPropertyGenerationStrategy implements EnumClass<String> {

    RANDOM("RANDOM"),
    MANUAL("MANUAL");

    private String id;

    BooleanPropertyGenerationStrategy(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BooleanPropertyGenerationStrategy fromId(String id) {
        for (BooleanPropertyGenerationStrategy at : BooleanPropertyGenerationStrategy.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}