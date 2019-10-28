package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.MetaClass
import com.haulmont.chile.core.model.MetaProperty

import com.haulmont.cuba.core.entity.BaseUuidEntity

@MetaClass(name = "datagen_PropertyGenerationSettings")
open class PropertyGenerationSettings : BaseUuidEntity() {
    lateinit var metaProperty: MetaProperty
}

