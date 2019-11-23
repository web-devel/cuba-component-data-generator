package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class TestEnum(private val id: String) : EnumClass<String> {
    VAL1("VAL1"),
    VAL2("VAL2"),
    VAL3("VAL3");

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: String): TestEnum? = TestEnum.values().find { it.id == id }
    }
}
