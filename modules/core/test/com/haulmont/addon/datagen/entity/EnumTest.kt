package com.haulmont.addon.datagen.entity

import org.junit.Test

class EnumTest {

    @Test
    fun testFromId() {
        val enumVal = StringPropertyGenerationStrategy.fromId(StringPropertyGenerationStrategy.FAKER.id)
        assert(enumVal != null)
        assert(enumVal == StringPropertyGenerationStrategy.FAKER)

        val nullVal = StringPropertyGenerationStrategy.fromId("nonexustent")
        assert(nullVal == null)
    }
}
