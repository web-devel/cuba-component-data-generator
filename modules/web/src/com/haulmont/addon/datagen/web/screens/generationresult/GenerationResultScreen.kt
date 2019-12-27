package com.haulmont.addon.datagen.web.screens.generationresult

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.cuba.gui.screen.*
import java.lang.IllegalStateException
import javax.inject.Inject

@UiController("datagen_GenerationResult")
@UiDescriptor("generation-result.xml")
class GenerationResultScreen : Screen() {

    var command: DataGenerationCommand<*>? = null
    @Inject
    private lateinit var dataGenerationService: DataGenerationService


    @Subscribe
    private fun onAfterShow(event: AfterShowEvent) {
        val command = this.command ?: throw IllegalStateException("Data generation command is not passed")
        doGenerate(command)
    }

    private fun doGenerate(cmd: DataGenerationCommand<*>) {
        dataGenerationService.generateEntities(cmd)
    }

}
