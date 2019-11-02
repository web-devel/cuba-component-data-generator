package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty

@MetaClass(name = "datagen_StringPropertyGenerationSettings")
class StringPropertyGenerationSettings : PropertyGenerationSettings() {
    @MetaProperty
    var manualValue: String? = null

    @MetaProperty
    private var strategy: String? = StringPropertyGenerationStrategy.FAKER.id

    fun getStrategy(): StringPropertyGenerationStrategy? = strategy?.let { StringPropertyGenerationStrategy.fromId(it) }

    fun setStrategy(strategy: StringPropertyGenerationStrategy?) {
        this.strategy = strategy?.id
    }
}
