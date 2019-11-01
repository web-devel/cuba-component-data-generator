package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.CommitContext
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import org.springframework.stereotype.Service
import javax.inject.Inject

@Service(DataGenerationService.NAME)
class DataGenerationServiceBean : DataGenerationService {

    @Inject
    private lateinit var metadata: Metadata
    @Inject
    private lateinit var dataManager: DataManager

    override fun generateEntities(command: DataGenerationCommand, dryRun: Boolean): List<Entity<Any>> {
        val generatedEntities = generate(command);
        return if (dryRun) {
            generatedEntities
        } else {
            dataManager.commit(CommitContext(generatedEntities)).toList()
        }
    }

    private fun generate(command: DataGenerationCommand): List<Entity<Any>> {
        val entityGenerationSettings = command.entityGenerationSettings!!
        return List(entityGenerationSettings.amount!!) {
            metadata.create(entityGenerationSettings.entityMetaClass)
        }
    }
}
