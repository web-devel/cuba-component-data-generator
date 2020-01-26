package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.ref.ReferencePropGenSettings
import com.haulmont.cuba.core.entity.BaseGenericIdEntity

object ReferenceGenerator {
    fun generateRefProperty(settings: ReferencePropGenSettings): BaseGenericIdEntity<*>? {
        return settings.referenceEntity
    }
}
