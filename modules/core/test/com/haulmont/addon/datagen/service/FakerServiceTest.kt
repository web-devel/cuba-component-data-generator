package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.cuba.core.global.AppBeans
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.Ancient
import io.github.serpro69.kfaker.provider.Beer
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class FakerServiceTest {

    companion object {
        @ClassRule
        @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }

    lateinit var fakerService: FakerService;

    @Before
    @Throws(Exception::class)
    fun setUp() {
        fakerService = AppBeans.get(FakerService::class.java)
    }

    @Test
    fun testProvidersList() {
        val providers = fakerService.getProviderFunctionRefs()
        val someExistingProviders = listOf(
                Faker::ancient.name + StringPropGenSettings.DELIMITER + Ancient::god.name,
                Faker::beer.name + StringPropGenSettings.DELIMITER + Beer::name.name
        )
        assert(providers.containsAll(someExistingProviders))
    }

    @Test
    fun testGenerate() {
        val generationResult = fakerService.generate(Faker::ancient.name + StringPropGenSettings.DELIMITER + Ancient::god.name)
        assert(generationResult.isNotBlank())
    }
}
