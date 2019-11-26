package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.generation.BooleanGenerator.generateBooleanProperty
import com.haulmont.addon.datagen.generation.StringGenerator.generateStringProperty

object PropertyGeneration {

    fun generateProperty(settings: PropertyGenerationSettings): Any? =
            when (settings) {
                is BooleanPropertyGenerationSettings -> generateBooleanProperty(settings)
                is StringPropertyGenerationSettings -> generateStringProperty(settings)
                else -> throw IllegalArgumentException("Property generator is not found")
            }

}


