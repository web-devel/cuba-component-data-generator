package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum DataGenerationType implements EnumClass<String> {

    COMMIT_SEPARATELY("COMMIT_SEPARATELY"),
    COMMIT_IN_SINGLE_TRANSACTION("COMMIT_IN_SINGLE_TRANSACTION"),
    JSON("JSON");

    private String id;

    DataGenerationType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DataGenerationType fromId(String id) {
        for (DataGenerationType at : DataGenerationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
