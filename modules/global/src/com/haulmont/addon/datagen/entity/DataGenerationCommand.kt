package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.cuba.core.entity.BaseUuidEntity
import com.haulmont.cuba.core.entity.Entity

@MetaClass(name = "datagen_GenerationCommand")
class DataGenerationCommand<T: Entity<*>> : BaseUuidEntity() {

    @MetaProperty
    var saveExecutionLog: Boolean? = true

    @MetaProperty
    var entityGenerationSettings: EntityGenerationSettings<T>? = null
}
