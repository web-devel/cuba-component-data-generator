package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.cuba.core.entity.Entity
import java.lang.Exception

interface DataGenerationService {
    companion object {
        const val NAME = "datagen_DataGenerationService"
    }

    fun <T : Entity<*>> generateEntities(command: DataGenerationCommand<T>): GenerationResult<T>

    fun <T : Entity<*>> generateEntity(settings: EntityGenerationSettings<T>): T

}

data class GenerationResult<T : Entity<*>>(
        var generated: List<T>,
        var committed: MutableList<T> = arrayListOf(),
        var exceptions: MutableList<Exception>  = arrayListOf()
)
