package com.haulmont.addon.datagen.web.screens.generationresult

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.addon.datagen.service.EntitiesGenerationResult
import com.haulmont.addon.datagen.web.screens.generatedentity.GeneratedEntityBrowse
import com.haulmont.cuba.core.entity.BaseGenericIdEntity
import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.core.global.MetadataTools
import com.haulmont.cuba.gui.ScreenBuilders
import com.haulmont.cuba.gui.UiComponents
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.executors.BackgroundTask
import com.haulmont.cuba.gui.executors.BackgroundTaskHandler
import com.haulmont.cuba.gui.executors.BackgroundWorker
import com.haulmont.cuba.gui.executors.TaskLifeCycle
import com.haulmont.cuba.gui.model.CollectionContainer
import com.haulmont.cuba.gui.screen.*
import org.apache.commons.lang3.exception.ExceptionUtils
import javax.inject.Inject


@UiController("datagen_GenerationResult")
@UiDescriptor("generation-result.xml")
@DialogMode(width = "800px", height = "600px", resizable = true)
class GenerationResultScreen : Screen() {
    @Inject
    private lateinit var resultsBox: VBoxLayout

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
    @Inject
    private lateinit var backgroundWorker: BackgroundWorker


    @Subscribe
    private fun onAfterShow(event: AfterShowEvent) {
        val command = this.command ?: throw IllegalStateException("Data generation command is not passed")
        doGenerate(command)
    }

    private fun doGenerate(cmd: DataGenerationCommand<*>) {
        progressBar.isVisible = true
        resultsBox.isVisible = false
        val task = object : BackgroundTask<Int?, EntitiesGenerationResult<*>>(600, this) {
            @Throws(Exception::class)
            override fun run(taskLifeCycle: TaskLifeCycle<Int?>): EntitiesGenerationResult<*> {
                return dataGenerationService.generateEntities(cmd)
            }

            override fun canceled() { // Do something in UI thread if the task is canceled
            }

            override fun done(result: EntitiesGenerationResult<*>) {
                showResult(result);
            }

            override fun progress(changes: List<Int?>) { // Show current progress in UI thread
            }
        }
        val taskHandler: BackgroundTaskHandler<*> = backgroundWorker.handle(task)
        taskHandler.execute()
    }

    private fun showResult(res: EntitiesGenerationResult<*>) {
        progressBar.isVisible = false
        resultsBox.isVisible = true
        window.expand(resultsBox)

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

    @Subscribe("browseBtn")
    private fun onBrowseBtnClick(event: Button.ClickEvent) {
        screenBuilders.screen(this)
                .withScreenClass(GeneratedEntityBrowse::class.java)
                .show()
    }


}
