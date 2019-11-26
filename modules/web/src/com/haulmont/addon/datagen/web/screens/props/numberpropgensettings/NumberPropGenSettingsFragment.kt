package com.haulmont.addon.datagen.web.screens.props.numberpropgensettings

import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenStrategy
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.components.TextField
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.ScreenFragment
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import java.math.BigDecimal
import javax.inject.Inject

@UiController("datagen_NumberPropGenSettingsFragment")
@UiDescriptor("number-prop-gen-settings-fragment.xml")
class NumberPropGenSettingsFragment : ScreenFragment() {
    
    @Inject
    private lateinit var dc: InstanceContainer<NumberPropGenSettings>

    // UI
    @Inject
    private lateinit var manualFloatValue: TextField<Double>
    @Inject
    private lateinit var manualIntegerValue: TextField<Long>
    @Inject
    private lateinit var maxRandomValue: TextField<Long>
    @Inject
    private lateinit var minRandomValue: TextField<Long>
    @Inject
    private lateinit var strategyField: LookupField<NumberPropGenStrategy>

    fun setItem(settings: NumberPropGenSettings): NumberPropGenSettingsFragment {
        dc.setItem(settings)
        return this
    }

    @Subscribe
    private fun onInit(event: InitEvent) {
        strategyField.addValueChangeListener {
            updateFields()
        }
        updateFields()
    }

    fun updateFields() {
        when (strategyField.value) {
            NumberPropGenStrategy.RANDOM -> {
                minRandomValue.isVisible = true
                maxRandomValue.isVisible = true
                manualFloatValue.isVisible = false
                manualIntegerValue.isVisible = false
            }
            NumberPropGenStrategy.MANUAL -> {
                minRandomValue.isVisible = false
                maxRandomValue.isVisible = false
                val javaClass = dc.item.metaProperty.range.asDatatype<Number>().javaClass
                if (javaClass == Integer::class.java || javaClass == Long::class.java ) {
                    manualIntegerValue.isVisible = true
                }
                if (javaClass == Double::class.java || javaClass == BigDecimal::class.java ) {
                    manualFloatValue.isVisible = true
                }
            }
            else -> {
                minRandomValue.isVisible = false
                maxRandomValue.isVisible = false
                manualFloatValue.isVisible = false
                manualIntegerValue.isVisible = false
            }
        }
    }


}
