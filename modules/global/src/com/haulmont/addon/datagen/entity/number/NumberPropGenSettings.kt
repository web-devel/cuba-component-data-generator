package com.haulmont.addon.datagen.entity.number

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty

@MetaClass(name = "datagen_NumberPropGenSettings")
class NumberPropGenSettings : PropertyGenerationSettings() {

    @MetaProperty
    private var strategy: String? = NumberPropGenStrategy.RANDOM.id

    @MetaProperty
    var manualIntegerValue: Long? = null

    @MetaProperty
    var manualFloatValue: Double? = null

    @MetaProperty
    var minRandomValue: Long? = null

    @MetaProperty
    var maxRandomValue: Long? = null

    fun getStrategy(): NumberPropGenStrategy? = strategy?.let { NumberPropGenStrategy.fromId(it) }

    fun setStrategy(strategy: NumberPropGenStrategy?) {
        this.strategy = strategy?.id
    }

    companion object {
        private const val serialVersionUID = -6572186461873560891L
    }
}
