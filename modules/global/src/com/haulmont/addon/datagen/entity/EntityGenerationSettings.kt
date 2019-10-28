package com.haulmont.addon.datagen.entity


import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.chile.core.model.MetaClass
import com.haulmont.cuba.core.entity.BaseUuidEntity

@com.haulmont.chile.core.annotations.MetaClass(name = "datagen_EntityGenerationSettings")
class EntityGenerationSettings(
        val entityMetaClass: MetaClass
) : BaseUuidEntity() {

    @MetaProperty
    var amount: Int? = 1
    @MetaProperty
    var properties: MutableList<PropertyGenerationSettings> = mutableListOf()

}
