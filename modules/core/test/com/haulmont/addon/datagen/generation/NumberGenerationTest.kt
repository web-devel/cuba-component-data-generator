package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.TestEntityJava
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenStrategy
import com.haulmont.addon.datagen.generation.NumberGenerator.generateNumberProperty
import com.haulmont.chile.core.model.MetaProperty
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import java.math.BigDecimal

class NumberGenerationTest {

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
        val settings = NumberPropGenSettings()
        settings.setStrategy(null)
        val number = generateNumberProperty(settings)
        assert(number == null)
    }

    @Test
    fun manualInteger() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("intgr")
        val settings = createSettings(intMetaProperty)
        settings.manualIntegerValue = 42

        val number = generateNumberProperty(settings)

        assert(number is Int)
        assert(number == 42)
    }

    @Test
    fun randomInteger() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("intgr")
        val settings = createSettings(intMetaProperty, NumberPropGenStrategy.RANDOM)
        settings.minRandomValue = 0
        settings.maxRandomValue = 1

        val number = generateNumberProperty(settings)
        assert(number == 0)

        val negativeSettings = createSettings(intMetaProperty, NumberPropGenStrategy.RANDOM)
        negativeSettings.minRandomValue = -10
        negativeSettings.maxRandomValue = 0
        val negativeNumber = generateNumberProperty(negativeSettings) as Int
        assert(negativeNumber >= -10)
        assert(negativeNumber < 0)
    }

    @Test
    fun manualLong() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("lng")
        val settings = createSettings(intMetaProperty)
        settings.manualIntegerValue = 10000L

        val number = generateNumberProperty(settings)

        assert(number is Long)
        assert(number == 10000L)
    }

    @Test
    fun manualDouble() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("dbl")
        val settings = createSettings(intMetaProperty)
        settings.manualFloatValue = 1.5

        val number = generateNumberProperty(settings)

        assert(number is Double)
        assert(number == 1.5)
    }

    @Test
    fun manualBigDecimal() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("bigDecimal")
        val settings = createSettings(intMetaProperty)
        settings.manualFloatValue = 1.5

        val number = generateNumberProperty(settings)

        assert(number is BigDecimal)
        assert(number == BigDecimal(1.5))
    }

    private fun createSettings(intMetaProperty: MetaProperty, strategy:NumberPropGenStrategy = NumberPropGenStrategy.MANUAL): NumberPropGenSettings {
        val settings = NumberPropGenSettings()
        settings.metaProperty = intMetaProperty
        settings.setStrategy(strategy)
        return settings
    }
}
