package com.haulmont.addon.datagen.web.screens.props.referencepropgensettings

import com.haulmont.addon.datagen.entity.ref.ReferencePropGenSettings
import com.haulmont.addon.datagen.web.screens.props.PropChangeListener
import com.haulmont.addon.datagen.web.screens.props.PropGenFragment
import com.haulmont.cuba.core.entity.BaseGenericIdEntity
import com.haulmont.cuba.gui.components.PickerField
import com.haulmont.cuba.gui.model.DataComponents
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_ReferencePropGenSettingsFragment")
@UiDescriptor("reference-prop-gen-settings-fragment.xml")
class ReferencePropGenSettingsFragment : PropGenFragment<ReferencePropGenSettings>() {

    @Inject
    private lateinit var dc: InstanceContainer<ReferencePropGenSettings>
    @Inject
    private lateinit var refPicker: PickerField<BaseGenericIdEntity<*>>


    @Subscribe
    private fun onInit(event: InitEvent) {
        val item: ReferencePropGenSettings = this.dc.item
        val metaClass = item.metaProperty.range.asClass()
        refPicker.metaClass = metaClass
        refPicker.addValueChangeListener {
            item.referenceEntity = it.value
        }
    }

    override fun setItem(settings: ReferencePropGenSettings): PropGenFragment<*> {
        dc.setItem(settings)
        return this
    }

    override fun addDcPropChangeListener(listener: PropChangeListener<ReferencePropGenSettings>) {
        dc.addItemPropertyChangeListener(listener)
    }
}
