package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.TestEntityJava
import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenSettings
import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenStrategy
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
import java.time.LocalDate

class LocalDateGenerationTest {

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
        val settings = LocalDatePropGenSettings()
        settings.setStrategy(null)
        val number = LocalDateGenerator.generateLocalDateProperty(settings)
        assert(number == null)
    }

    @Test
    fun manualLocalDate() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("localdate")
        val settings = createSettings(intMetaProperty)
        settings.manualIntegerValue = 42

        val date = LocalDateGenerator.generateLocalDateProperty(settings)

        assert(date is LocalDate)
        date?.isEqual(LocalDate.now().plusDays(42))?.let { assert(it) }
    }

    @Test
    fun randomLocalDate() {
        val intMetaProperty = metadata.getClassNN(TestEntityJava::class.java).getPropertyNN("localdate")
        val settings = createSettings(intMetaProperty, LocalDatePropGenStrategy.RANDOM)
        settings.minRandomValue = 0
        settings.maxRandomValue = 1

        val date = LocalDateGenerator.generateLocalDateProperty(settings)
        date?.isEqual(LocalDate.now())?.let { assert(it) }

        val negativeSettings = createSettings(intMetaProperty, LocalDatePropGenStrategy.RANDOM)
        negativeSettings.minRandomValue = -10
        negativeSettings.maxRandomValue = 0
        val historicalDate = LocalDateGenerator.generateLocalDateProperty(negativeSettings) as LocalDate
        historicalDate?.isAfter(LocalDate.now().minusDays(30))?.let { assert(it) }
        historicalDate?.isBefore(LocalDate.now())?.let { assert(it) }
    }

    private fun createSettings(intMetaProperty: MetaProperty, strategy:LocalDatePropGenStrategy = LocalDatePropGenStrategy.MANUAL): LocalDatePropGenSettings {
        val settings = LocalDatePropGenSettings()
        settings.metaProperty = intMetaProperty
        settings.setStrategy(strategy)
        return settings
    }
}
