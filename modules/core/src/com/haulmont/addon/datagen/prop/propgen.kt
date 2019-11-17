package com.haulmont.addon.datagen.prop

import com.haulmont.addon.datagen.entity.*
import com.haulmont.addon.datagen.service.FakerService
import com.haulmont.cuba.core.global.AppBeans
import org.apache.commons.lang3.RandomUtils

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
                if (settings.fakerProvider == null || settings.fakerProviderFunction == null) {
                    throw java.lang.IllegalArgumentException()
                }
                AppBeans.get(FakerService::class.java).generate(
                        settings.fakerProvider!!,
                        settings.fakerProviderFunction!!
                )
            }
            null -> null
        }


fun generateBooleanProperty(settings: BooleanPropertyGenerationSettings): Boolean? =
        when (settings.getStrategy()) {
            BooleanPropertyGenerationStrategy.MANUAL -> settings.manualValue
            BooleanPropertyGenerationStrategy.RANDOM -> RandomUtils.nextBoolean()
            null -> null
        }
