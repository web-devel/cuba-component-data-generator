package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.ref.ReferencePropGenSettings

object PropertyGeneration {

    fun generateProperty(settings: PropertyGenerationSettings): Any? =
            when (settings) {
                is BooleanPropertyGenerationSettings -> BooleanGenerator.generateBooleanProperty(settings)
                is StringPropGenSettings -> StringGenerator.generateStringProperty(settings)
                is NumberPropGenSettings -> NumberGenerator.generateNumberProperty(settings)
                is LocalDatePropGenSettings -> LocalDateGenerator.generateLocalDateProperty(settings)
                is EnumPropGenSettings -> EnumGenerator.generateEnumProperty(settings)
                is ReferencePropGenSettings -> ReferenceGenerator.generateRefProperty(settings)
                else -> throw IllegalArgumentException("Property generator is not found")
            }

}


