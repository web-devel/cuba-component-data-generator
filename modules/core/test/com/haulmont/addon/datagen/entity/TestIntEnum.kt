package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.datatypes.impl.EnumClass

enum class TestIntEnum(private val id: Int) : EnumClass<Int> {
    ZERO(0),
    TEN(10),
    TWENTY(20);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): TestIntEnum? = TestIntEnum.values().find { it.id == id }
    }
}
