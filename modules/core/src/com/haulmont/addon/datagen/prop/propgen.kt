package com.haulmont.addon.datagen.prop

import com.haulmont.addon.datagen.entity.*
import io.github.serpro69.kfaker.Faker
import org.apache.commons.lang3.RandomUtils
import java.lang.IllegalArgumentException

fun generateProperty(settings: PropertyGenerationSettings): Any? = when (settings) {
    is BooleanPropertyGenerationSettings -> generateBooleanProperty(settings)
    is StringPropertyGenerationSettings -> generateStringProperty(settings)
    else -> throw IllegalArgumentException("Property generator is not found")
}

fun generateStringProperty(settings: StringPropertyGenerationSettings): String? = when (settings.getStrategy()) {
    StringPropertyGenerationStrategy.MANUAL -> settings.manualValue
    StringPropertyGenerationStrategy.FAKER -> Faker().address.city()
    null -> throw IllegalArgumentException("String generation strategy is undefined")
}


fun generateBooleanProperty(settings: BooleanPropertyGenerationSettings): Boolean? = when (settings.getStrategy()) {
    BooleanPropertyGenerationStrategy.MANUAL -> settings.manualValue
    BooleanPropertyGenerationStrategy.RANDOM -> RandomUtils.nextBoolean()
    null -> throw IllegalArgumentException("Boolean generation strategy is undefined")
}
