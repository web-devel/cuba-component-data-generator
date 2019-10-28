package com.haulmont.addon.datagen

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.chile.core.model.MetaProperty

object PropertyGeneration {

    private val mapping = mapOf(
            java.lang.String::class.java to StringPropertyGenerationSettings::class.java,
            java.lang.Boolean::class.java to BooleanPropertyGenerationSettings::class.java
    )


    fun isSupported(prop: MetaProperty): Boolean {
        return when (prop.type) {
            MetaProperty.Type.DATATYPE -> mapping.containsKey(prop.range.asDatatype<Any>().javaClass)
            else -> false
        }
    }

    fun createSettings(prop: MetaProperty): PropertyGenerationSettings? {
        return mapping[prop.range.asDatatype<Any>().javaClass]?.newInstance()
    }
}