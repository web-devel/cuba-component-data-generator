package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationStrategy.FAKER
import com.haulmont.addon.datagen.entity.StringPropertyGenerationStrategy.MANUAL
import com.haulmont.addon.datagen.service.FakerService
import com.haulmont.cuba.core.global.AppBeans

object StringGenerator {

    fun generateStringProperty(settings: StringPropertyGenerationSettings): String? =
            when (settings.getStrategy()) {
                MANUAL -> settings.manualValue
                FAKER -> generateByFaker(settings)
                null -> null
            }

    private fun generateByFaker(settings: StringPropertyGenerationSettings): String? {
        val fakerProvider = settings.fakerProvider
        val fakerProviderFunction = settings.fakerProviderFunction

        if (fakerProvider == null || fakerProviderFunction == null) {
            return null
        }
        return AppBeans.get(FakerService::class.java).generate(
                fakerProvider,
                fakerProviderFunction
        )
    }

}
