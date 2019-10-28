package com.haulmont.addon.datagen.web.screens.props.stringpropertygenerationsettings

import com.haulmont.addon.datagen.entity.StringPropertyGenerationSettings
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.ScreenFragment
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_StringPropertyGenerationSettingsFragment")
@UiDescriptor("string-property-generation-settings-fragment.xml")
class StringPropertyGenerationSettingsFragment : ScreenFragment() {

    @Inject
    private lateinit var stringPropertyGenerationSettingsDc: InstanceContainer<StringPropertyGenerationSettings>

    fun setItem(settings:StringPropertyGenerationSettings): StringPropertyGenerationSettingsFragment {
        stringPropertyGenerationSettingsDc.setItem(settings)
        return this
    }
}
