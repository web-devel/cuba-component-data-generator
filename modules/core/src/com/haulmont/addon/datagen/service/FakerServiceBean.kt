package com.haulmont.addon.datagen.service

import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
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

    override fun getProviderFunctionRefs(): List<String> {
        val providerNames = this.providerProps.keys.toList()
        val funRefs = mutableListOf<String>()
        providerNames.forEach { providerName ->
            getProviderFunctionsNameList(providerName).forEach { funName ->
                funRefs.add(providerName + StringPropGenSettings.DELIMITER + funName)
            }
        }
        return funRefs
    }

    override fun generate(providerFunctionRef: String): String {
        val faker = Faker()
        val parts = providerFunctionRef.split(StringPropGenSettings.DELIMITER)
        assert(parts.size == 2)
        val providerName = parts[0]
        val funName = parts[1]
        val prop = providerProps[providerName]
        val provider = ((prop as KProperty1<Faker, AbstractFakeDataProvider<*>>).get(faker))
        val func = provider::class.declaredMemberFunctions.find { f ->
            f.name == funName
        }
        requireNotNull(func)
        return func.call(provider) as String
    }

    private fun getProviderFunctionsNameList(providerName: String): List<String> {
        val prop = providerProps[providerName]
        requireNotNull(prop)
        val provider = prop.get(Faker())
        requireNotNull(provider)
        return provider::class.declaredMemberFunctions
                .filter { it.parameters.size == 1 } // 1st parameter is instance
                .map { it.name }
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
