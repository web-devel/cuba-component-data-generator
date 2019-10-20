package com.haulmont.addon.datagen.web.screens.genarate

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.chile.core.model.MetaClass
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.components.ScrollBoxLayout
import com.haulmont.cuba.gui.components.VBoxLayout
import com.haulmont.cuba.gui.components.data.options.ListOptions
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.Screen
import com.haulmont.cuba.gui.screen.Subscribe
import com.haulmont.cuba.gui.screen.UiController
import com.haulmont.cuba.gui.screen.UiDescriptor
import com.haulmont.cuba.web.gui.components.WebGroupBox
import javax.inject.Inject

@Suppress("IncorrectCreateEntity")
@UiController("datagen_EntitySelect")
@UiDescriptor("datagen-entity-select.xml")
class DatagenEntitySelect : Screen() {

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
        metaClass.properties.forEach {
            val webGroupBox = WebGroupBox()
            webGroupBox.caption = it.name
            propertiesConfigBox.add(webGroupBox)
        }
    }

    @Subscribe("generateBtn")
    private fun onGenerateBtnClick(event: Button.ClickEvent) {
        dataGenerationService.generateEntities(this.generationCommandDc.item)
    }


}

