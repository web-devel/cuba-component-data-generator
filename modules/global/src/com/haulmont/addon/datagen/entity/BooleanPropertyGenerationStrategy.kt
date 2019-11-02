package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class BooleanPropertyGenerationStrategy(private val id: String) : EnumClass<String> {
    RANDOM("RANDOM"),
    MANUAL("MANUAL");

    override fun getId(): String {
        return id
    }

    companion object {

        @JvmStatic
        fun fromId(id: String): BooleanPropertyGenerationStrategy? {
            for (at in BooleanPropertyGenerationStrategy.values()) {
                if (at.getId() == id) {
                    return at
                }
            }
            return null
        }
    }
}
