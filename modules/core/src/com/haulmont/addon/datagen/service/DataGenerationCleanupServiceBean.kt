package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.GeneratedEntity
import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.entity.IdProxy
import com.haulmont.cuba.core.global.CommitContext
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.UuidProvider
import org.springframework.stereotype.Service
import java.util.*
import javax.inject.Inject


@Service(DataGenerationCleanupService.NAME)
class DataGenerationCleanupServiceBean : DataGenerationCleanupService {

    @Inject
    private lateinit var dataManager: DataManager
    @Inject
    private lateinit var persistence: Persistence
    @Inject
    private lateinit var metadata: Metadata


    override fun removeGeneratedEntities(entitiesList: List<GeneratedEntity>) {

        persistence.createTransaction().use { tx ->
            val em = persistence.entityManager
            em.isSoftDeletion = false
            entitiesList.forEach {
                val query = em.createQuery("delete from " + it.entityName + " e where e.id = :instanceId")
                query.setParameter("instanceId", getId(it))
                query.executeUpdate()
            }
            tx.commit()
        }

        val commitContext = CommitContext()
        commitContext.removeInstances.addAll(entitiesList)
        dataManager.commit(commitContext)
    }

    fun getId(ge: GeneratedEntity): Any {
        val metaClass = metadata.session.getClass(ge.entityName)
        val primaryKey = metadata.getTools().getPrimaryKeyProperty(metaClass)
        val idString = ge.instanceId
        val type = primaryKey?.javaType
        if (UUID::class.java == type) {
            return UuidProvider.fromString(idString)
        } else if (Long::class.java == type || IdProxy::class.java == type) {
            return (idString as Int).toLong()
        } else if (Int::class.java == type) {
            return idString
        } else if (String::class.java == type) {
            return idString
        }
        return idString
    }
}
