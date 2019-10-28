package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand

interface DataGenerationService {
    companion object {
        const val NAME = "datagen_DataGenerationService"
    }

    fun generateEntities(command: DataGenerationCommand)
}
