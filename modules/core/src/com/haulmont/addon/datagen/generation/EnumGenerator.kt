package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenStrategy
import com.haulmont.chile.core.datatypes.impl.EnumClass
import kotlin.random.Random

object EnumGenerator {

    fun generateEnumProperty(settings: EnumPropGenSettings): Enum<*>? =
            when (settings.getStrategy()) {
                null -> null
                EnumPropGenStrategy.MANUAL -> generateManual(settings)
                EnumPropGenStrategy.RANDOM -> generateRandom(settings)
            }

    private fun generateManual(settings: EnumPropGenSettings): Enum<*>? {
        return  settings.manualValue
    }

    private fun generateRandom(settings: EnumPropGenSettings): Enum<*>? {
        val values = settings.metaProperty.range.asEnumeration().values
        val enumSize = values.size
        return values[Random.nextInt(enumSize)]
    }
}
