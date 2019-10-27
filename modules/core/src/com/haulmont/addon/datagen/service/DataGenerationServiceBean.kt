package com.haulmont.addon.datagen.service;

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import org.springframework.stereotype.Service

@Service(DataGenerationService.NAME)
class DataGenerationServiceBean : DataGenerationService {
    override fun generateEntities(command: DataGenerationCommand) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
