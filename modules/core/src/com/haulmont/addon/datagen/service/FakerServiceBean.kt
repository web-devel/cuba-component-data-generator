package com.haulmont.addon.datagen.service;

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.provider.AbstractFakeDataProvider
import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

@Service(FakerService.NAME)
class FakerServiceBean : FakerService {

    private val providerProps: SortedMap<String, KProperty1<Faker, *>> = scanProviders()

    override fun getProviderNamesList(): List<String> {
        return this.providerProps.keys.toList()
    }

    override fun getProviderFunctionsNameList(providerName: String): List<String> {
        val prop = providerProps[providerName]
        requireNotNull(prop)
        val get = prop.get(Faker())
        requireNotNull(get)
        return get::class.declaredMemberFunctions.map { it.name }
    }

    override fun generate(providerName: String, funName: String): String {
        val faker = Faker()
        val prop = providerProps[providerName]
        val provider = ((prop as KProperty1<Faker, AbstractFakeDataProvider<*>>).get(faker))
        val func = provider::class.declaredMemberFunctions.find { f ->
            f.name == funName
        }
        requireNotNull(func)
        return func.call(provider) as String
    }

    private fun scanProviders(): SortedMap<String, KProperty1<Faker, *>> {

        return Faker::class.declaredMemberProperties
                .filter {
                    val cls = it.javaField?.type
                    if (cls != null)
                        AbstractFakeDataProvider::class.java.isAssignableFrom(cls)
                    else
                        false
                }
                .map {
                    it.name to it
                }
                .toMap()
                .toSortedMap()
    }

}
