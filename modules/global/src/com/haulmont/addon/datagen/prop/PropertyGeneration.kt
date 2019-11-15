package com.haulmont.addon.datagen.prop

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.chile.core.model.MetaProperty

object PropertyGeneration {

    private val dType2Settings = mapOf(
            java.lang.String::class.java to StringPropertyGenerationSettings::class.java,
            java.lang.Boolean::class.java to BooleanPropertyGenerationSettings::class.java
    )

    fun isGeneratorAvailable(prop: MetaProperty): Boolean {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> dType2Settings.containsKey(prop.range.asDatatype<Any>().javaClass)
            else -> false
        }
    }

    fun createSettings(prop: MetaProperty): PropertyGenerationSettings? {
        val datatype = prop.range.asDatatype<Any>()
        return dType2Settings[datatype.javaClass]?.newInstance()
    }
}


fun stringPropertyGenerationSettings(init: StringPropertyGenerationSettings.() -> Unit): StringPropertyGenerationSettings {
    val settings = StringPropertyGenerationSettings()
    settings.init()
    return settings
}
