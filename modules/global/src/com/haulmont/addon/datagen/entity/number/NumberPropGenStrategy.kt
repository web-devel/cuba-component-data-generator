package com.haulmont.addon.datagen.entity.number

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class NumberPropGenStrategy(private val id: String) : EnumClass<String> {
    MANUAL("MANUAL"),
    RANDOM("RANDOM");

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: String): NumberPropGenStrategy? = NumberPropGenStrategy.values().find { it.id == id }
    }
}
