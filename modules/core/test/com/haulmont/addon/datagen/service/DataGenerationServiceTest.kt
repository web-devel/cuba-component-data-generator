package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.TestEntity
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.security.entity.User
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

    lateinit var dgs: DataGenerationService;

    @Before
    @Throws(Exception::class)
    fun setUp() {
        dgs = AppBeans.get(DataGenerationService::class.java)
    }

    @Test
    fun testGenerateEntity() {
        val settings = EntityGenerationSettings(TestEntity::class.java)
        val generated = dgs.generateEntity(settings)
        assert(TestEntity::class.java.isAssignableFrom(generated::class.java))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }
}
