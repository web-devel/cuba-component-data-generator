package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings

object PropertyGeneration {

    fun generateProperty(settings: PropertyGenerationSettings): Any? =
            when (settings) {
                is BooleanPropertyGenerationSettings -> BooleanGenerator.generateBooleanProperty(settings)
                is StringPropGenSettings -> StringGenerator.generateStringProperty(settings)
                is NumberPropGenSettings -> NumberGenerator.generateNumberProperty(settings)
                is EnumPropGenSettings -> EnumGenerator.generateEnumProperty(settings)
                else -> throw IllegalArgumentException("Property generator is not found")
            }

}


