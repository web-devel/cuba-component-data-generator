package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty

@MetaClass(name = "datagen_BooleanPropertyGenerationSettings")
class BooleanPropertyGenerationSettings : PropertyGenerationSettings() {
    @MetaProperty
    var random: Boolean? = true
    @MetaProperty
    var manualValue: Boolean? = null
}
