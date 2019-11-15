package com.haulmont.addon.datagen.web.screens.props.booleanpropertygenerationsettings

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationStrategy
import com.haulmont.cuba.gui.components.CheckBox
import com.haulmont.cuba.gui.components.HasValue
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.ScreenFragment
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_BooleanPropertyGenerationSettingsFragment")
@UiDescriptor("boolean-property-generation-settings-fragment.xml")
class BooleanPropertyGenerationSettingsFragment : ScreenFragment() {


    @Inject
    private lateinit var dc: InstanceContainer<BooleanPropertyGenerationSettings>
    @Inject
    private lateinit var manualValueField: CheckBox

    fun setItem(setting:BooleanPropertyGenerationSettings): BooleanPropertyGenerationSettingsFragment {
        dc.setItem(setting)
        return this
    }

    @Subscribe
    private fun onInit(event: InitEvent) {
        updateShownFields(dc.item.getStrategy())
    }

    @Subscribe("strategyField")
    private fun onStrategyFieldValueChange(event: HasValue.ValueChangeEvent<BooleanPropertyGenerationStrategy>) {
        updateShownFields(event.value)
    }

    private fun updateShownFields(strategy: BooleanPropertyGenerationStrategy?) {
        manualValueField.isVisible = strategy == BooleanPropertyGenerationStrategy.MANUAL
    }
    
}
