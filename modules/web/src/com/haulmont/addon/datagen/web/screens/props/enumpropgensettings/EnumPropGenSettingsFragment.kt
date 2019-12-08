package com.haulmont.addon.datagen.web.screens.props.enumpropgensettings

import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenStrategy
import com.haulmont.addon.datagen.web.screens.props.PropChangeListener
import com.haulmont.addon.datagen.web.screens.props.PropGenFragment
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_EnumPropGenSettingsFragment")
@UiDescriptor("enum-prop-gen-settings-fragment.xml")
class EnumPropGenSettingsFragment : PropGenFragment<EnumPropGenSettings>() {

    // Data
    @Inject
    private lateinit var dc: InstanceContainer<EnumPropGenSettings>

    // UI
    @Inject
    private lateinit var manualValueField: LookupField<Enum<*>>
    @Inject
    private lateinit var strategyField: LookupField<EnumPropGenStrategy>

    override fun setItem(settings: EnumPropGenSettings): EnumPropGenSettingsFragment {
        dc.setItem(settings)
        return this
    }

    override fun addDcPropChangeListener(listener: PropChangeListener<EnumPropGenSettings>) {
        dc.addItemPropertyChangeListener(listener)
    }

    @Subscribe
    private fun onInit(event: InitEvent) {
        manualValueField.setOptionsList(dc.item.metaProperty.range.asEnumeration().values)
        strategyField.addValueChangeListener {
            updateFields()
        }
    }

    private fun updateFields() {
        when (strategyField.value) {
            EnumPropGenStrategy.MANUAL -> manualValueField.setVisible(true)
            else -> manualValueField.setVisible(false)
        }
    }


}
