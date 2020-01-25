package com.haulmont.addon.datagen.web.screens.generatedentity

import com.haulmont.cuba.gui.screen.*
import com.haulmont.addon.datagen.entity.GeneratedEntity
import com.haulmont.addon.datagen.service.DataGenerationCleanupService
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.gui.Notifications
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.GroupTable
import com.haulmont.cuba.gui.model.CollectionLoader
import javax.inject.Inject

@UiController("datagen_GeneratedEntity.browse")
@UiDescriptor("generated-entity-browse.xml")
@LookupComponent("generatedEntitiesTable")
@LoadDataBeforeShow
class GeneratedEntityBrowse : StandardLookup<GeneratedEntity>() {

    @Inject
    private lateinit var generatedEntitiesTable: GroupTable<GeneratedEntity>
    @Inject
    private lateinit var dataGenerationCleanupService: DataGenerationCleanupService
    @Inject
    private lateinit var generatedEntitiesDl: CollectionLoader<GeneratedEntity>
    @Inject
    private lateinit var notifications: Notifications
    @Inject
    private lateinit var messages: Messages


    @Subscribe("removeSelectedBtn")
    private fun onRemoveSelectedBtnClick(event: Button.ClickEvent) {
        val selected = generatedEntitiesTable.selected.toList()

        if (selected.isEmpty()) {
            notifications.create()
                    .withCaption(messages.getMessage(javaClass, "selectEntitiesNotification"))
                    .withType(Notifications.NotificationType.WARNING)
                    .show()
            return
        }

        dataGenerationCleanupService.removeGeneratedEntities(selected)
        generatedEntitiesDl.load()
        notifications.create()
                .withCaption(messages.getMessage(javaClass, "removeSuccess"))
                .show()
    }

}

