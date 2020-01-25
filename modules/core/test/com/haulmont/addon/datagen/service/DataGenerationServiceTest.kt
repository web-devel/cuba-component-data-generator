package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.*
import com.haulmont.addon.datagen.generation.GenerationSettingsFactory
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.security.entity.Permission
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

@Suppress("IncorrectCreateEntity")
class DataGenerationServiceTest {

    companion object {
        @ClassRule
        @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }

    lateinit var dgs: DataGenerationService
    lateinit var metadata: Metadata
    lateinit var dataManager: DataManager

    @Before
    @Throws(Exception::class)
    fun setUp() {
        dgs = AppBeans.get(DataGenerationService::class.java)
        metadata = AppBeans.get(Metadata::class.java)
        dataManager = AppBeans.get(DataManager::class.java)
    }

    @Test
    fun testGeneratedEntityClass() {
        val settings = EntityGenerationSettings<TestEntityJava>()
        settings.entityClass = TestEntityJava::class.java
        val generated = dgs.generateEntity(settings)
        assert(TestEntityJava::class.java.isAssignableFrom(generated::class.java))
    }

    @Test
    fun testJSON() {
        val command = DataGenerationCommand<TestEntityJava>()
        command.amount = 3
        command.entityGenerationSettings = EntityGenerationSettings()
        command.entityGenerationSettings.entityClass = TestEntityJava::class.java
        command.type = DataGenerationType.JSON

        val res = dgs.generateEntities(command)

        assert(res.generated.size == 3)
        assert(res.committed.size == 0)
    }

    @Test
    fun testCommitSeparately() {
        val command = DataGenerationCommand<Permission>()
        command.amount = 3
        command.entityGenerationSettings = EntityGenerationSettings()
        command.entityGenerationSettings.entityClass = Permission::class.java
        command.type = DataGenerationType.COMMIT_SEPARATELY

        val res = dgs.generateEntities(command)

        assert(res.committed.size == 3)
        assert(res.generated.size == 3)
    }

    @Test
    fun testCommitSingleTransaction() {
        val command = DataGenerationCommand<Permission>()
        command.amount = 2
        command.entityGenerationSettings = EntityGenerationSettings()
        command.entityGenerationSettings.entityClass = Permission::class.java
        command.type = DataGenerationType.COMMIT_IN_SINGLE_TRANSACTION

        val res = dgs.generateEntities(command)

        assert(res.committed.size == 2)
        assert(res.generated.size == 2)
    }

    @Test
    fun testEntityWithAllSupportedPropertiesGenerated() {
        val settings = EntityGenerationSettings<TestEntityJava>()
        settings.entityClass = TestEntityJava::class.java
        val generatedEntity = dgs.generateEntity(settings)
        metadata.getClassNN(TestEntityJava::class.java).properties.forEach() { prop ->
            if (!GenerationSettingsFactory.isGeneratorAvailable(prop)) return
            assert(generatedEntity.getValue<Any>(prop.name) != null)
        }
    }

    @Test
    fun savesLogs() {
        val command = DataGenerationCommand<Permission>()
        command.amount = 2
        command.entityGenerationSettings = EntityGenerationSettings()
        command.entityGenerationSettings.entityClass = Permission::class.java
        command.type = DataGenerationType.COMMIT_IN_SINGLE_TRANSACTION

        val res = dgs.generateEntities(command)
        val ids: List<String> = res.committed.map { it.id.toString() }
        val ge = dataManager.load(GeneratedEntity::class.java)
                .query("""
                    select ge from datagen_GeneratedEntity ge 
                    where
                        ge.entityName = :entityName
                        and ge.instanceId in :instanceIds
                """)
                .parameter("entityName", "sec\$Permission")
                .parameter("instanceIds", ids)
                .list()

        assert(ge.size == 2)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }
}
