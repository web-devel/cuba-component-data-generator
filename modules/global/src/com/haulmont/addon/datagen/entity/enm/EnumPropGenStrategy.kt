package com.haulmont.addon.datagen.entity.enm

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class EnumPropGenStrategy(private val id: String) : EnumClass<String> {
    MANUAL("MANUAL"),
    RANDOM("RANDOM");

    override fun getId() = id

    companion object {
        @JvmStatic
        fun fromId(id: String): EnumPropGenStrategy? = values().find { it.id == id }
    }
}
