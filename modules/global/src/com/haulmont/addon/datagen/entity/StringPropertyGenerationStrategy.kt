package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class StringPropertyGenerationStrategy(private val id: String) : EnumClass<String> {
    MANUAL("MANUAL"),
    FAKER("FAKER");

    override fun getId(): String {
        return id
    }

    companion object {

        @JvmStatic
        fun fromId(id: String): StringPropertyGenerationStrategy? {
            for (at in StringPropertyGenerationStrategy.values()) {
                if (at.getId() == id) {
                    return at
                }
            }
            return null
        }
    }
}
