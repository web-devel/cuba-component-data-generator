package com.haulmont.addon.datagen.prop

import com.haulmont.addon.datagen.entity.*
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.Ancient
import org.apache.commons.lang3.RandomUtils
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun generateProperty(settings: PropertyGenerationSettings): Any? =
        when (settings) {
            is BooleanPropertyGenerationSettings -> generateBooleanProperty(settings)
            is StringPropertyGenerationSettings -> generateStringProperty(settings)
            else -> throw IllegalArgumentException("Property generator is not found")
        }

fun generateStringProperty(settings: StringPropertyGenerationSettings): String? =
        when (settings.getStrategy()) {
            StringPropertyGenerationStrategy.MANUAL -> settings.manualValue
            StringPropertyGenerationStrategy.FAKER -> {
                val faker = Faker()
                val prop = faker::class.memberProperties.find { it.name == "ancient" }
                ((prop as KProperty1<Any, *>).get(faker) as Ancient).god()
            }
            null -> null
        }


fun generateBooleanProperty(settings: BooleanPropertyGenerationSettings): Boolean? =
        when (settings.getStrategy()) {
            BooleanPropertyGenerationStrategy.MANUAL -> settings.manualValue
            BooleanPropertyGenerationStrategy.RANDOM -> RandomUtils.nextBoolean()
            null -> null
        }
