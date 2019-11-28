package com.haulmont.addon.datagen.entity.enm

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty

@MetaClass(name = "datagen_EnumPropGenSettings")
class EnumPropGenSettings : PropertyGenerationSettings() {

    @MetaProperty
    private var strategy: String? = null

    var manualValue: Enum<*>? = null

    fun getStrategy(): EnumPropGenStrategy? = strategy?.let { EnumPropGenStrategy.fromId(it) }

    fun setStrategy(strategy: EnumPropGenStrategy?) {
        this.strategy = strategy?.id
    }

    companion object {
        private const val serialVersionUID = -7094018282292550489L
    }
}
