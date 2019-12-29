package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.cuba.core.entity.Entity

interface DataGenerationService {
    companion object {
        const val NAME = "datagen_DataGenerationService"
    }

    fun <T : Entity<*>> generateEntities(command: DataGenerationCommand<T>): EntitiesGenerationResult<T>

    fun <T : Entity<*>> generateEntity(settings: EntityGenerationSettings<T>): T

}

