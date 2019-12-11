package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.TestEntityKotlin
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

@Suppress("IncorrectCreateEntity")
class DataGenerationServiceTest {

    companion object {
        @ClassRule @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }

    lateinit var dgs: DataGenerationService
    lateinit var metadata: Metadata

    @Before
    @Throws(Exception::class)
    fun setUp() {
        dgs = AppBeans.get(DataGenerationService::class.java)
        metadata = AppBeans.get(Metadata::class.java)
    }

    @Test
    fun testGeneratedEntityClass() {
        val settings = EntityGenerationSettings<TestEntityKotlin>()
        settings.entityClass = TestEntityKotlin::class.java
        val generated = dgs.generateEntity(settings)
        assert(TestEntityKotlin::class.java.isAssignableFrom(generated::class.java))
    }

//    @Test
//    fun testEntityWithAllSupportedPropertiesGenerated() {
//        val settings = EntityGenerationSettings(TestEntity::class.java)
//        val metaClass = metadata.getClass(TestEntity::class.java)
//        val generated = dgs.generateEntity(settings)
//        TestEntity::class.declaredMemberProperties.forEach {kProp ->
//
//            val metaProp = metaClass!!.getProperty(kProp.name) ?: return
//
//            if (!PropertyGeneration.isGeneratorAvailable(metaProp)) return
//
//            val generatedProp = kProp.get(generated)
//            assert(generatedProp != null)
//        }
//    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }
}
