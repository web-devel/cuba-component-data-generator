package com.haulmont.addon.datagen.web.screens.props

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.ScreenFragment

typealias PropChangeListener<T> = (e: InstanceContainer.ItemPropertyChangeEvent<T>) -> Unit

abstract class PropGenFragment<T : PropertyGenerationSettings> : ScreenFragment() {

    abstract fun setItem(settings: T): PropGenFragment<*>

    abstract fun addDcPropChangeListener(listener: PropChangeListener<T>)

}