package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenStrategy
import com.haulmont.addon.datagen.generation.StringGenerator.generateStringProperty
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.Ancient
import org.junit.ClassRule
import org.junit.Test
import org.junit.platform.commons.util.StringUtils

class StringGenerationTest {

    companion object {
        @ClassRule
        @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }

    @Test
    fun nullStrategy() {
        val settings = stringPropertyGenerationSettings { setStrategy(null) }
        assert(generateStringProperty(settings) == null)
    }

    @Test
    fun manualValue() {
        val settings = stringPropertyGenerationSettings {
            setStrategy(StringPropGenStrategy.MANUAL)
            manualValue = "test"
        }
        assert(generateStringProperty(settings) == "test")
    }

    @Test
    fun faker() {
        val settings = stringPropertyGenerationSettings {
            setStrategy(StringPropGenStrategy.FAKER)
            fakerFunctionRef = Faker::ancient.name + StringPropGenSettings.DELIMITER + Ancient::titan.name
        }
        val generateStringProperty = generateStringProperty(settings)
        assert(StringUtils.isNotBlank(generateStringProperty))
    }

    @Test
    fun fakerNull() {
        val settings = stringPropertyGenerationSettings {
            setStrategy(StringPropGenStrategy.FAKER)
        }
        val generateStringProperty = generateStringProperty(settings)
        assert(generateStringProperty == null)
    }
}
