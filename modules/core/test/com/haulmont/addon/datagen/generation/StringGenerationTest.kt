package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.StringPropertyGenerationStrategy
import com.haulmont.addon.datagen.generation.StringGenerator.generateStringProperty
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.Address
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
            setStrategy(StringPropertyGenerationStrategy.MANUAL)
            manualValue = "test"
        }
        assert(generateStringProperty(settings) == "test")
    }

    @Test
    fun faker() {
        val settings = stringPropertyGenerationSettings {
            setStrategy(StringPropertyGenerationStrategy.FAKER)
            fakerProvider = Faker::address.name
            fakerProviderFunction = Address::country.name
        }
        val generateStringProperty = generateStringProperty(settings)
        assert(StringUtils.isNotBlank(generateStringProperty))
    }
}
