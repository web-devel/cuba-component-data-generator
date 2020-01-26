package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.ref.ReferencePropGenSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.chile.core.model.MetaProperty

object GenerationSettingsFactory {

    private val dType2Settings = mapOf(
            java.lang.String::class.java to StringPropGenSettings::class.java,
            java.lang.Boolean::class.java to BooleanPropertyGenerationSettings::class.java,
            java.lang.Integer::class.java to NumberPropGenSettings::class.java,
            java.lang.Long::class.java to NumberPropGenSettings::class.java,
            java.lang.Double::class.java to NumberPropGenSettings::class.java,
            java.math.BigDecimal::class.java to NumberPropGenSettings::class.java
    )

    fun createSettings(prop: MetaProperty): PropertyGenerationSettings? {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> {
                val dtypeJavaClass = prop.range.asDatatype<Any>().javaClass
                val settingsClass = dType2Settings[dtypeJavaClass] ?: throw IllegalArgumentException("Unsupported datatype for property ${prop.name}")
                val settings = settingsClass.newInstance()
                settings.metaProperty = prop
                settings
            }
            MetaProperty.Type.ENUM -> {
                val enumPropGenSettings = EnumPropGenSettings()
                enumPropGenSettings.metaProperty = prop
                enumPropGenSettings
            }
            MetaProperty.Type.COMPOSITION, MetaProperty.Type.ASSOCIATION -> {
                val refPropSettings = ReferencePropGenSettings()
                refPropSettings.metaProperty = prop
                refPropSettings
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun isGeneratorAvailable(prop: MetaProperty): Boolean {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> dType2Settings.containsKey(prop.range.asDatatype<Any>().javaClass)
            MetaProperty.Type.ENUM -> true
            MetaProperty.Type.ASSOCIATION, MetaProperty.Type.COMPOSITION -> isReferenceGenAvailable(prop)
            else -> false
        }
    }

    private fun isReferenceGenAvailable(prop: MetaProperty) = !prop.range.cardinality.isMany

}


fun stringPropertyGenerationSettings(init: StringPropGenSettings.() -> Unit): StringPropGenSettings {
    val settings = StringPropGenSettings()
    settings.init()
    return settings
}
