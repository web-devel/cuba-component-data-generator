package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.cuba.core.entity.BaseUuidEntity

@MetaClass(name = "datagen_PropertyGenerationSettings")
open class PropertyGenerationSettings : BaseUuidEntity() {
    lateinit var metaProperty: MetaProperty
}

@MetaClass(name = "datagen_StringPropertyGenerationSettings")
class StringPropertyGenerationSettings : PropertyGenerationSettings() {
    var manualValue: String? = null
}

@MetaClass(name = "datagen_BooleanPropertyGenerationSettings")
class BooleanPropertyGenerationSettings : PropertyGenerationSettings() {
    var random = false
    var manualValue: Boolean? = null
}
