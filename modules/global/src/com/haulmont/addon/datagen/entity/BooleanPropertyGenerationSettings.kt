package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty

@MetaClass(name = "datagen_BooleanPropertyGenerationSettings")
class BooleanPropertyGenerationSettings : PropertyGenerationSettings() {
    @MetaProperty
    var manualValue: Boolean? = null

    @MetaProperty
    private var strategy: String? = null

    fun getStrategy(): BooleanPropertyGenerationStrategy? = strategy?.let { BooleanPropertyGenerationStrategy.fromId(it) }

    fun setStrategy(strategy: BooleanPropertyGenerationStrategy?) {
        this.strategy = strategy?.id
    }
}
