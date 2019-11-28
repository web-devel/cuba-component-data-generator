package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.TestEntity
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenStrategy
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.security.entity.Role
import com.haulmont.cuba.security.entity.RoleType
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class EnumGenerationTest {

    companion object {
        @ClassRule
        @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }

    lateinit var metadata: Metadata

    @Before
    @Throws(Exception::class)
    fun setUp() {
        metadata = AppBeans.get(Metadata::class.java)
    }

    @Test
    fun nullStrategy() {
        val settings = EnumPropGenSettings()
        settings.metaProperty = metadata.getClassNN(TestEntity::class.java).getPropertyNN(TestEntity::strEnumAttr.name)
        settings.setStrategy(null)
        val enum = EnumGenerator.generateEnumProperty(settings)
        assert(enum == null)
    }

    @Test
    fun randomEnum() {
        val settings = EnumPropGenSettings()
        settings.metaProperty = metadata.getClassNN(Role::class.java).getPropertyNN("type")
        settings.setStrategy(EnumPropGenStrategy.RANDOM)

        val enum = EnumGenerator.generateEnumProperty(settings)
        assert(enum != null)
    }

    @Test
    fun manualEnum() {
        val settings = EnumPropGenSettings()
        settings.metaProperty = metadata.getClassNN(Role::class.java).getPropertyNN("type")
        settings.setStrategy(EnumPropGenStrategy.MANUAL)
        settings.manualValue = RoleType.DENYING

        val enum = EnumGenerator.generateEnumProperty(settings)
        assert(enum == RoleType.DENYING)
    }


}
