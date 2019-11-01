package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.cuba.core.entity.Entity

interface DataGenerationService {
    companion object {
        const val NAME = "datagen_DataGenerationService"
    }

    fun generateEntities(command: DataGenerationCommand, dryRun: Boolean = false): List<Entity<Any>>
}
