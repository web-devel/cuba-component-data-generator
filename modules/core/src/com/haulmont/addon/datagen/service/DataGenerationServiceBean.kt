package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.generation.PropertyGeneration.generateProperty
import com.haulmont.cuba.core.entity.Entity
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

    override fun <T : Entity<*>> generateEntities(command: DataGenerationCommand<T>, dryRun: Boolean): List<T> {
        val generatedEntities = generate(command)
        return if (dryRun) {
            generatedEntities
        } else {
            generatedEntities.map { dataManager.commit(it) }
        }
    }

    override fun <T : Entity<*>> generateEntity(settings: EntityGenerationSettings<T>): T {
        val entity = metadata.create(settings.entityClass)
        settings.properties.forEach {
            entity.setValue(it.metaProperty.name, generateProperty(it))
        }
        return entity
    }

    private fun <T : Entity<*>> generate(command: DataGenerationCommand<T>): List<T> {
        val entityGenerationSettings = command.entityGenerationSettings!!
        return List(entityGenerationSettings.amount!!) {
            generateEntity(entityGenerationSettings)
        }
    }
}
