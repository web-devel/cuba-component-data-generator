package com.haulmont.addon.datagen.service

import com.haulmont.cuba.core.entity.Entity
import java.io.Serializable
import java.lang.Exception

data class EntitiesGenerationResult<T : Entity<*>> (
        var generated: List<T>,
        var committed: MutableList<T> = arrayListOf(),
        var exceptions: MutableList<Exception>  = arrayListOf()
) : Serializable
