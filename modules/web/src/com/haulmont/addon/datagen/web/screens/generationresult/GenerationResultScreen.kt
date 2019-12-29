package com.haulmont.addon.datagen.web.screens.generationresult

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.addon.datagen.service.EntitiesGenerationResult
import com.haulmont.cuba.core.entity.BaseGenericIdEntity
import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.core.global.MetadataTools
import com.haulmont.cuba.gui.ScreenBuilders
import com.haulmont.cuba.gui.UiComponents
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.model.CollectionContainer
import com.haulmont.cuba.gui.screen.*
import org.apache.commons.lang3.exception.ExceptionUtils
import javax.inject.Inject

@UiController("datagen_GenerationResult")
@UiDescriptor("generation-result.xml")
@DialogMode(width = "800px", height = "600px", resizable = true)
class GenerationResultScreen : Screen() {

    var command: DataGenerationCommand<*>? = null
    @Inject
    private lateinit var dataGenerationService: DataGenerationService
    @Inject
    private lateinit var messages: Messages
    @Inject
    private lateinit var uiComponents: UiComponents
    @Inject
    private lateinit var metadataTools: MetadataTools
    @Inject
    private lateinit var screenBuilders: ScreenBuilders

    // Data
    @Inject
    private lateinit var committedEntitiesDc: CollectionContainer<BaseGenericIdEntity<*>>

    // UI
    @Inject
    private lateinit var progressBar: ProgressBar
    @Inject
    private lateinit var accordion: Accordion
    @Inject
    private lateinit var exceptionsTextArea: TextArea<String>


    @Subscribe
    private fun onAfterShow(event: AfterShowEvent) {
        val command = this.command ?: throw IllegalStateException("Data generation command is not passed")
        doGenerate(command)
    }

    private fun doGenerate(cmd: DataGenerationCommand<*>) {
        val result = dataGenerationService.generateEntities(cmd)
        progressBar.isVisible = false
        accordion.isVisible = true
        window.expand(accordion)
        showResult(result)
    }

    private fun showResult(res: EntitiesGenerationResult<*>) {
        accordion.getTab("committedTab").caption =
                messages.formatMessage(javaClass,"committedCaption", res.committed.size)
        val exceptionsTab = accordion.getTab("exceptionsTab")
        exceptionsTab.caption =
                messages.formatMessage(javaClass, "exceptionsCaption", res.exceptions.size)

        val entities = res.committed.map { it as BaseGenericIdEntity }
        committedEntitiesDc.mutableItems.addAll(entities)

        val exceptionsList: String = res.exceptions.joinToString(separator = "\n\n") {
            e -> ExceptionUtils.getStackTrace(e)
        }
        exceptionsTextArea.value = exceptionsList
        if (res.exceptions.size > 0 && res.committed.size == 0) {
            accordion.selectedTab = exceptionsTab
        }
    }

    @Install(to = "committedTable.id", subject = "columnGenerator")
    private fun committedTableIdColumnGenerator(entity: BaseGenericIdEntity<Any>): Component? {
        val linkComponent = uiComponents.create(LinkButton::class.java)
        linkComponent.caption = entity.id.toString()
        linkComponent.addClickListener {
            screenBuilders
                    .lookup(entity::class.java, this)
                    .withOpenMode(OpenMode.NEW_TAB)
                    .show()
        }
        return linkComponent
    }

    @Install(to = "committedTable.instanceName", subject = "columnGenerator")
    private fun committedTableInstanceNameColumnGenerator(entity: BaseGenericIdEntity<Any>?): Component? {
        val label = uiComponents.create(Label.TYPE_STRING)
        label.value = metadataTools.getInstanceName(entity)
        return label
    }


}
