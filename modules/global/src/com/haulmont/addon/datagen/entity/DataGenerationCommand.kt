package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.cuba.core.entity.BaseUuidEntity

@MetaClass(name = "datagen_GenerationCommand")
class DataGenerationCommand : BaseUuidEntity() {

    @MetaProperty
    var log: Boolean? = true

    var entityGenerationSettings: EntityGenerationSettings? = null
}