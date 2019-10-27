package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.cuba.core.entity.BaseUuidEntity

@MetaClass(name = "datagen_PropertyGenerationSettings")
open class PropertyGenerationSettings : BaseUuidEntity() {
    lateinit var metaProperty: MetaProperty
}

object PropertyGeneration {

    val mapping = mapOf(
            java.lang.String::class.java to StringPropertyGenerationSettings::class.java
    )


    fun isSupported(prop: com.haulmont.chile.core.model.MetaProperty): Boolean {
        return mapping.containsKey(prop.range.asDatatype<Any>().javaClass)
    }
}
