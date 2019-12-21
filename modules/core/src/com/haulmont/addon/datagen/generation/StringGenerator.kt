package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenStrategy.FAKER
import com.haulmont.addon.datagen.entity.str.StringPropGenStrategy.MANUAL
import com.haulmont.addon.datagen.service.FakerService
import com.haulmont.cuba.core.global.AppBeans

object StringGenerator {

    fun generateStringProperty(settings: StringPropGenSettings): String? =
            when (settings.getStrategy()) {
                MANUAL -> settings.manualValue
                FAKER -> generateByFaker(settings)
                null -> null
            }

    private fun generateByFaker(settings: StringPropGenSettings): String? {
        return if (settings.fakerFunctionRef == null) {
            null
        } else {
            AppBeans.get(FakerService::class.java).generate(
                    settings.fakerFunctionRef
            )
        }
    }

}
