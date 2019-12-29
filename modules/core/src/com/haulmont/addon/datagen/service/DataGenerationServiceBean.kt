package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.DataGenerationType
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.generation.PropertyGeneration.generateProperty
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

    override fun <T : Entity<*>> generateEntities(command: DataGenerationCommand<T>): EntitiesGenerationResult<T> {
        val generatedEntities = generate(command)
        val result = EntitiesGenerationResult(generatedEntities)
        if (command.type == DataGenerationType.JSON) {
            return result
        }

        if (command.type == DataGenerationType.COMMIT_SEPARATELY) {
            generatedEntities.forEach {
                try {
                    result.committed.add(dataManager.commit(it))
                } catch (ex: Exception) {
                    result.exceptions.add(ex)
                }
            }
            return result;
        }

        if (command.type == DataGenerationType.COMMIT_IN_SINGLE_TRANSACTION) {
            val ids = generatedEntities.map { it.id }.toSet()
            try {
                val allCommittedEntities = dataManager.commit(CommitContext(generatedEntities))
                val committed: List<T> = allCommittedEntities
                        .filter { it.id in ids }
                        .map { it as T } // todo better type cast through entityClass
                result.committed.addAll(committed)
            } catch (e: Exception) {
                result.exceptions.add(e)
            }
        }

        return EntitiesGenerationResult(listOf())
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
        return List(command.amount!!) {
            generateEntity(entityGenerationSettings)
        }
    }
}
