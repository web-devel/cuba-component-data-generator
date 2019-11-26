package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationStrategy
import com.haulmont.addon.datagen.service.FakerService
import com.haulmont.cuba.core.global.AppBeans
import java.lang.IllegalArgumentException

object StringGenerator {

    fun generateStringProperty(settings: StringPropertyGenerationSettings): String? =
            when (settings.getStrategy()) {
                StringPropertyGenerationStrategy.MANUAL -> settings.manualValue
                StringPropertyGenerationStrategy.FAKER -> generateByFaker(settings)
                null -> null
            }

    private fun generateByFaker(settings: StringPropertyGenerationSettings): String {
        if (settings.fakerProvider == null || settings.fakerProviderFunction == null) {
            throw IllegalArgumentException()
        }
        return AppBeans.get(FakerService::class.java).generate(
                settings.fakerProvider!!,
                settings.fakerProviderFunction!!
        )
    }

}
