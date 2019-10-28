package com.haulmont.addon.datagen.web.screens.props.booleanpropertygenerationsettings

import com.haulmont.addon.datagen.entity.BooleanPropertyGenerationSettings
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.ScreenFragment
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_BooleanPropertyGenerationSettingsFragment")
@UiDescriptor("boolean-property-generation-settings-fragment.xml")
class BooleanPropertyGenerationSettingsFragment : ScreenFragment() {


    @Inject
    private lateinit var booleanPropertyGenerationSettingsDc: InstanceContainer<BooleanPropertyGenerationSettings>

    fun setItem(setting:BooleanPropertyGenerationSettings): BooleanPropertyGenerationSettingsFragment {
        booleanPropertyGenerationSettingsDc.setItem(setting)
        return this
    }
}
