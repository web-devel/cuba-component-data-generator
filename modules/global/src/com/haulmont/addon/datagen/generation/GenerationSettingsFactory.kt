package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.chile.core.model.MetaProperty
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import java.lang.IllegalArgumentException

object GenerationSettingsFactory {

    private val dType2Settings = mapOf(
            java.lang.String::class.java to StringPropertyGenerationSettings::class.java,
            java.lang.Boolean::class.java to BooleanPropertyGenerationSettings::class.java,
            java.lang.Integer::class.java to NumberPropGenSettings::class.java,
            java.lang.Long::class.java to NumberPropGenSettings::class.java,
            java.lang.Double::class.java to NumberPropGenSettings::class.java,
            java.math.BigDecimal::class.java to NumberPropGenSettings::class.java
    )

    fun createSettings(prop: MetaProperty): PropertyGenerationSettings? {
        val metadata = AppBeans.get(Metadata::class.java)
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> {
                val dtypeJavaClass = prop.range.asDatatype<Any>().javaClass
                val settingsClass = dType2Settings[dtypeJavaClass] ?: throw IllegalArgumentException("Unsupported datatype for property ${prop.name}")
                val settings = settingsClass.newInstance()
                settings.metaProperty = prop
                return settings
            }
            MetaProperty.Type.ENUM -> {
                val enumPropGenSettings = EnumPropGenSettings()
                enumPropGenSettings.metaProperty = prop
                return enumPropGenSettings
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun isGeneratorAvailable(prop: MetaProperty): Boolean {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> dType2Settings.containsKey(prop.range.asDatatype<Any>().javaClass)
            MetaProperty.Type.ENUM -> true
            else -> false
        }
    }
}


fun stringPropertyGenerationSettings(init: StringPropertyGenerationSettings.() -> Unit): StringPropertyGenerationSettings {
    val settings = StringPropertyGenerationSettings()
    settings.init()
    return settings
}
