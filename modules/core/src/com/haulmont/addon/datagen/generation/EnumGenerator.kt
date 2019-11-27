package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenStrategy
import com.haulmont.chile.core.datatypes.impl.EnumClass

object EnumGenerator {

    fun generateEnumProperty(settings: EnumPropGenSettings): EnumClass<*>? =
            when (settings.getStrategy()) {
                null -> null
                EnumPropGenStrategy.MANUAL -> generateManual(settings)
                EnumPropGenStrategy.RANDOM -> generateRandom(settings)
            }

    private fun generateManual(settings: EnumPropGenSettings): EnumClass<*>? {
        TODO()
    }

    private fun generateRandom(settings: EnumPropGenSettings): EnumClass<*>? {
        TODO()
    }
}
