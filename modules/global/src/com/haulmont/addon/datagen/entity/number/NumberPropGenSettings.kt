package com.haulmont.addon.datagen.entity.number

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "datagen_NumberPropGenSettings")
class NumberPropGenSettings : PropertyGenerationSettings() {
    @Column(name = "MANUAL_VALUE")
    var manualIntegerValue: Long? = null

    @Column(name = "MIN_RANDOM_VALUE")
    var minRandomValue: Long? = null

    @Column(name = "MAX_RANDOM_VALUE")
    var maxRandomValue: String? = null

    @Column(name = "MANUAL_FLOAT_VALUE")
    var manualFloatValue: Double? = null


    companion object {
        private const val serialVersionUID = -6572186461873560891L
    }
}
