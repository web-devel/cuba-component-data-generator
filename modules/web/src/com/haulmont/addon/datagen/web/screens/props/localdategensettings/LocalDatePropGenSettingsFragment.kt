package com.haulmont.addon.datagen.web.screens.props.localdategensettings

import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenSettings
import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenStrategy
import com.haulmont.addon.datagen.web.screens.props.PropChangeListener
import com.haulmont.addon.datagen.web.screens.props.PropGenFragment
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.components.TextField
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import java.time.LocalDate
import javax.inject.Inject

@UiController("datagen_LocalDatePropGenSettingsFragment")
@UiDescriptor("localdate-prop-gen-settings-fragment.xml")
class LocalDatePropGenSettingsFragment : PropGenFragment<LocalDatePropGenSettings>() {
    
    @Inject
    private lateinit var dc: InstanceContainer<LocalDatePropGenSettings>

    // UI
    @Inject
    private lateinit var manualIntegerValue: TextField<Long>
    @Inject
    private lateinit var maxRandomValue: TextField<Long>
    @Inject
    private lateinit var minRandomValue: TextField<Long>
    @Inject
    private lateinit var strategyField: LookupField<LocalDatePropGenStrategy>

    override fun setItem(settings: LocalDatePropGenSettings): LocalDatePropGenSettingsFragment {
        dc.setItem(settings)
        return this
    }

    override fun addDcPropChangeListener(listener: PropChangeListener<LocalDatePropGenSettings>) {
        dc.addItemPropertyChangeListener(listener)
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
            LocalDatePropGenStrategy.RANDOM -> {
                minRandomValue.isVisible = true
                maxRandomValue.isVisible = true
                manualIntegerValue.isVisible = false
            }
            LocalDatePropGenStrategy.MANUAL -> {
                minRandomValue.isVisible = false
                maxRandomValue.isVisible = false
                val javaClass = dc.item.metaProperty.range.asDatatype<LocalDate>().javaClass
                if (javaClass == LocalDate::class.java) {
                    manualIntegerValue.isVisible = true
                }
            }
            else -> {
                minRandomValue.isVisible = false
                maxRandomValue.isVisible = false
                manualIntegerValue.isVisible = false
            }
        }
    }


}
