package com.haulmont.addon.datagen.entity


import com.haulmont.chile.core.annotations.MetaProperty
import com.haulmont.cuba.core.entity.BaseUuidEntity
import com.haulmont.cuba.core.entity.Entity

@com.haulmont.chile.core.annotations.MetaClass(name = "datagen_EntityGenerationSettings")
class EntityGenerationSettings<T: Entity<*>>(
        val entityClass: Class<T>
) : BaseUuidEntity() {

    @MetaProperty
    var amount: Int? = 1
    @MetaProperty
    var properties: MutableList<PropertyGenerationSettings> = mutableListOf()

}
