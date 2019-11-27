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

object DataGenerationSettings {

    private val dType2Settings = mapOf(
            java.lang.String::class.java to StringPropertyGenerationSettings::class.java,
            java.lang.Boolean::class.java to BooleanPropertyGenerationSettings::class.java,
            java.lang.Integer::class.java to NumberPropGenSettings::class.java,
            java.lang.Long::class.java to NumberPropGenSettings::class.java,
            java.lang.Double::class.java to NumberPropGenSettings::class.java,
            java.math.BigDecimal::class.java to NumberPropGenSettings::class.java
    )

    fun isGeneratorAvailable(prop: MetaProperty): Boolean {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> dType2Settings.containsKey(prop.range.asDatatype<Any>().javaClass)
            MetaProperty.Type.ENUM -> true
            else -> false
        }
    }

    fun createSettings(prop: MetaProperty): PropertyGenerationSettings? {
        val metadata = AppBeans.get(Metadata::class.java)
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> metadata.create(dType2Settings[prop.range.asDatatype<Any>().javaClass])
            MetaProperty.Type.ENUM -> metadata.create(EnumPropGenSettings::class.java)
            else -> throw IllegalArgumentException()
        }
    }
}


fun stringPropertyGenerationSettings(init: StringPropertyGenerationSettings.() -> Unit): StringPropertyGenerationSettings {
    val settings = StringPropertyGenerationSettings()
    settings.init()
    return settings
}
