package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.DatagenTestContainer
import com.haulmont.cuba.core.global.AppBeans
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.Address
import io.github.serpro69.kfaker.provider.Ancient
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
        val providers = fakerService.getProviderNamesList()
        val someExistingProviders = listOf(
                Faker::ancient.name,
                Faker::beer.name
        )
        assert(providers.containsAll(someExistingProviders))
    }

    @Test
    fun testProviderFunList() {
        val foundFuns = fakerService.getProviderFunctionsNameList(Faker::ancient.name)
        val existingFuns = listOf(
                Ancient::god.name,
                Ancient::primordial.name,
                Ancient::titan.name,
                Ancient::hero.name
        )
        existingFuns.forEach {
            assert(foundFuns.contains(it))
        }
    }

    @Test
    fun testGenerate() {
        val generationResult = fakerService.generate(Faker::address.name, Address::city.name)
        assert(generationResult.isNotBlank())
    }
}
