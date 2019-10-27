package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass

@MetaClass(name = "datagen_BooleanPropertyGenerationSettings")
class BooleanPropertyGenerationSettings : PropertyGenerationSettings() {
    var random = false
    var manualValue: Boolean? = null
}
