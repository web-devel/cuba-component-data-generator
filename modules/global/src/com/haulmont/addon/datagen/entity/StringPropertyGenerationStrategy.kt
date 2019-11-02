package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class StringPropertyGenerationStrategy(private val id: String) : EnumClass<String> {
    MANUAL("MANUAL"),
    FAKER("FAKER");

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: String): StringPropertyGenerationStrategy?
                = StringPropertyGenerationStrategy.values().find { it.id == id }
    }
}
