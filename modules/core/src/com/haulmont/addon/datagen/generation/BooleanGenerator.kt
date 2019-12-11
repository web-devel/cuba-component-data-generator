package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationStrategy
import org.apache.commons.lang3.RandomUtils

object BooleanGenerator {
    fun generateBooleanProperty(settings: BooleanPropertyGenerationSettings): Boolean? =
            when (settings.getStrategy()) {
                BooleanPropertyGenerationStrategy.MANUAL -> settings.manualValue
                BooleanPropertyGenerationStrategy.RANDOM -> RandomUtils.nextBoolean()
                null -> null
            }
}
