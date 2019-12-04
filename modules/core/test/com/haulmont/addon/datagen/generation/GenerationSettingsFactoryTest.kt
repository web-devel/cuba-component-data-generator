package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.TestEntityKotlin
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class GenerationSettingsFactoryTest {

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
    fun testSettingsGeneration() {
        assert(createSettings(TestEntityKotlin::city.name) is StringPropertyGenerationSettings)
        assert(createSettings(TestEntityKotlin::bigDecimal.name) is NumberPropGenSettings)
        assert(createSettings(TestEntityKotlin::strEnumAttr.name) is EnumPropGenSettings)
    }

    private fun createSettings(propName: String): PropertyGenerationSettings? {
        val metaClass = metadata.getClassNN(TestEntityKotlin::class.java)
        return GenerationSettingsFactory.createSettings(metaClass.getPropertyNN(propName))
    }


}
