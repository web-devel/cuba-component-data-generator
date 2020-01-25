package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.DataGenerationType
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.GeneratedEntity
import com.haulmont.addon.datagen.generation.PropertyGeneration.generateProperty
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.CommitContext
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Inject

@Service(DataGenerationService.NAME)
class DataGenerationServiceBean : DataGenerationService {

    @Inject
    private lateinit var metadata: Metadata
    @Inject
    private lateinit var dataManager: DataManager

    override fun <T : Entity<*>> generateEntities(command: DataGenerationCommand<T>): EntitiesGenerationResult<T> {
        val generatedEntities = generate(command)

        val result = when (command.type) {
            DataGenerationType.JSON -> {
                val result = EntitiesGenerationResult(generatedEntities)
                return result
            }
            DataGenerationType.COMMIT_SEPARATELY -> commitSeparately(generatedEntities)
            DataGenerationType.COMMIT_IN_SINGLE_TRANSACTION -> commitInTransaction(generatedEntities)
            null -> throw IllegalArgumentException()
        }

        saveLog(result)

        return result
    }

    override fun <T : Entity<*>> generateEntity(settings: EntityGenerationSettings<T>): T {
        val metaClass = metadata.session.getClass(settings.entityClass)
        val entity = metadata.create(metaClass) as T // 7.1 / 7.2 compatibility
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

    private fun <T : Entity<*>> commitSeparately(generatedEntities: List<T>): EntitiesGenerationResult<T> {
        val result = EntitiesGenerationResult(generatedEntities)
        generatedEntities.forEach {
            try {
                result.committed.add(dataManager.commit(it))
            } catch (ex: Exception) {
                result.exceptions.add(ex)
            }
        }
        return result
    }

    private fun <T : Entity<*>> commitInTransaction(generatedEntities: List<T>): EntitiesGenerationResult<T> {
        val result = EntitiesGenerationResult(generatedEntities)
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
        return (result)
    }

    private fun <T : Entity<*>> saveLog(result: EntitiesGenerationResult<T>) {
        val logs: List<GeneratedEntity> = result.committed
                .map {
                    val generatedEntity = metadata.create(GeneratedEntity::class.java)
                    generatedEntity.entityName = it.metaClass?.name
                    generatedEntity.instanceId = it.id.toString()
                    try {
                        generatedEntity.instName = metadata.tools.getInstanceName(it)
                    } catch (e: IllegalStateException) {
                        // noop: unfetched attribute
                    }
                    generatedEntity
                }
        dataManager.commit(CommitContext(logs))
    }

}
