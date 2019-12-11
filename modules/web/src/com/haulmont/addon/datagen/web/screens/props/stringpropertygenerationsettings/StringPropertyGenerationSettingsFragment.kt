package com.haulmont.addon.datagen.web.screens.props.stringpropertygenerationsettings

import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenStrategy
import com.haulmont.addon.datagen.service.FakerService
import com.haulmont.addon.datagen.web.screens.props.PropChangeListener
import com.haulmont.addon.datagen.web.screens.props.PropGenFragment
import com.haulmont.cuba.gui.components.HasValue
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.components.TextField
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import javax.inject.Inject

@UiController("datagen_StringPropertyGenerationSettingsFragment")
@UiDescriptor("string-property-generation-settings-fragment.xml")
class StringPropertyGenerationSettingsFragment : PropGenFragment<StringPropGenSettings>() {

    @Inject
    private lateinit var fakerService: FakerService

    @Inject
    private lateinit var dc: InstanceContainer<StringPropGenSettings>

    // UI
    @Inject
    private lateinit var fakerProviderField: LookupField<String>
    @Inject
    private lateinit var fakerProviderFunctionField: LookupField<String>
    @Inject
    private lateinit var manualValueField: TextField<String>

    @Inject
    private lateinit var strategyField: LookupField<StringPropGenStrategy>

    override fun setItem(settings: StringPropGenSettings): PropGenFragment<StringPropGenSettings> {
        dc.setItem(settings)
        return this
    }

    override fun addDcPropChangeListener(listener: PropChangeListener<StringPropGenSettings>) {
        dc.addItemPropertyChangeListener(listener)
    }

    @Subscribe
    private fun onInit(event: InitEvent) {
        updateVisibleFields()
        val fakerProviders = fakerService.getProviderNamesList()
        fakerProviderField.setOptionsList(fakerProviders)
        fakerProviderField.addValueChangeListener() { changeEvent ->
            if (changeEvent.value == null) {
                fakerProviderFunctionField.setOptionsList(emptyList())
                fakerProviderFunctionField.value = null
            } else {
                val funNames = fakerService.getProviderFunctionsNameList(changeEvent.value!!)
                fakerProviderFunctionField.setOptionsList(funNames)
                fakerProviderFunctionField.value = funNames.firstOrNull()
            }
        }
        fakerProviderField.value = fakerProviders.firstOrNull()
    }

    @Subscribe("strategyField")
    private fun onStrategyFieldValueChange(event: HasValue.ValueChangeEvent<StringPropGenStrategy>) {
        updateVisibleFields()
    }

    private fun updateVisibleFields() {
        val strategy = dc.item.getStrategy()
        manualValueField.isVisible = strategy == StringPropGenStrategy.MANUAL
        fakerProviderField.isVisible = strategy == StringPropGenStrategy.FAKER
        fakerProviderFunctionField.isVisible = strategy == StringPropGenStrategy.FAKER
    }

}
