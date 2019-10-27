package com.haulmont.addon.datagen.web.screens.genarate

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGeneration
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.chile.core.model.MetaClass
import com.haulmont.chile.core.model.MetaProperty
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.data.options.ListOptions
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Screen
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import com.haulmont.cuba.web.gui.components.WebGroupBox
import javax.inject.Inject

@Suppress("IncorrectCreateEntity")
@UiController("datagen_Generation")
@UiDescriptor("data-generation.xml")
class DataGenerationScreen : Screen() {

    @Inject
    private lateinit var metadata: Metadata
    @Inject
    private lateinit var dataGenerationService: DataGenerationService

    @Inject
    private lateinit var generationCommandDc: InstanceContainer<DataGenerationCommand>
    @Inject
    private lateinit var entityLookup: LookupField<MetaClass>
    @Inject
    private lateinit var propertiesConfigBox: ScrollBoxLayout

    @Subscribe
    private fun onInit(event: InitEvent) {
        generationCommandDc.setItem(DataGenerationCommand())
        updateEntitySelectLookup()
    }

    private fun updateEntitySelectLookup() {
        val metaClasses = metadata.tools.allPersistentMetaClasses
                .sortedBy { it.name }
        entityLookup.options = ListOptions(metaClasses)
        entityLookup.addValueChangeListener { selectMetaClass(it.value) }
    }

    private fun selectMetaClass(metaClass: MetaClass?) {
        propertiesConfigBox.removeAll()
        if (metaClass == null) {
            generationCommandDc.item.entityGenerationSettings = null
            return
        }
        val entityGenerationSettings = EntityGenerationSettings(metaClass)
        generationCommandDc.item.entityGenerationSettings = entityGenerationSettings

        metaClass.properties
                .filter { PropertyGeneration.isSupported(it) }
                .forEach {
                    propertiesConfigBox.add(createPropertyGenerationComponents(it))
                }
    }

    @Subscribe("generateBtn")
    private fun onGenerateBtnClick(event: Button.ClickEvent) {
        dataGenerationService.generateEntities(this.generationCommandDc.item)
    }

    private fun createPropertyGenerationComponents(prop: MetaProperty): Component {
        val webGroupBox = WebGroupBox()
        webGroupBox.caption = prop.name
        return webGroupBox
    }

}

