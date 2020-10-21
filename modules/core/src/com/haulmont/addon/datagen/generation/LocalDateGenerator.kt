package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenSettings
import com.haulmont.addon.datagen.entity.localdate.LocalDatePropGenStrategy
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenStrategy
import java.lang.IllegalArgumentException
import java.time.LocalDate
import kotlin.random.Random

object LocalDateGenerator {

    fun generateLocalDateProperty(settings: LocalDatePropGenSettings): LocalDate? =
            when (settings.getStrategy()) {
                LocalDatePropGenStrategy.MANUAL -> generateManualValue(settings)
                LocalDatePropGenStrategy.RANDOM -> generateRandomValue(settings)
                null -> null
            }

    private fun generateRandomValue(settings: LocalDatePropGenSettings): LocalDate? =
            when (settings.metaProperty.range.asDatatype<Any>().javaClass) {
                java.time.LocalDate::class.java -> {
                    val from = settings.minRandomValue?.toLong() ?: 0
                    val until = settings.maxRandomValue?.toLong() ?: 1
                    val days = Random.nextLong(from, until)
                    LocalDate.now().plusDays(days)
                }
                else -> throw IllegalArgumentException("Days only supports integers")
            }

    private fun generateManualValue(settings: LocalDatePropGenSettings): LocalDate? =
            when (settings.metaProperty.range.asDatatype<Any>().javaClass) {
                java.time.LocalDate::class.java -> {
                    val days = settings.manualIntegerValue?.toLong() ?: 1
                    LocalDate.now().plusDays(days)
                }
                else -> throw IllegalArgumentException("Days only supports integers")
            }
}
