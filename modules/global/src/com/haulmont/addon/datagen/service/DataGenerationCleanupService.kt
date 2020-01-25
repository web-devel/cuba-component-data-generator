package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.GeneratedEntity

interface DataGenerationCleanupService {
    companion object {
        const val NAME = "datagen_DataGenerationCleanupService"
    }

    fun removeGeneratedEntities(entitiesList: List<GeneratedEntity>)
}

