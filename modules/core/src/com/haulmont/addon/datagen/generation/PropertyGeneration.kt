package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings

object PropertyGeneration {

    fun generateProperty(settings: PropertyGenerationSettings): Any? =
            when (settings) {
                is BooleanPropertyGenerationSettings -> BooleanGenerator.generateBooleanProperty(settings)
                is StringPropertyGenerationSettings -> StringGenerator.generateStringProperty(settings)
                is NumberPropGenSettings -> NumberGenerator.generateNumberProperty(settings)
                else -> throw IllegalArgumentException("Property generator is not found")
            }

}


