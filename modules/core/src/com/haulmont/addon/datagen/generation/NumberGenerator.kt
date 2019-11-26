package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenStrategy
import java.lang.IllegalArgumentException
import kotlin.random.Random

object NumberGenerator {

    fun generateNumberProperty(settings: NumberPropGenSettings): Number? =
            when (settings.getStrategy()) {
                NumberPropGenStrategy.MANUAL -> generateManualValue(settings)
                NumberPropGenStrategy.RANDOM -> generateRandomValue(settings)
                null -> null
            }

    private fun generateRandomValue(settings: NumberPropGenSettings): Number? =
            when (settings.metaProperty.range.asDatatype<Any>().javaClass) {
                java.lang.Integer::class.java -> {
                    val from = settings.minRandomValue?.toInt() ?: Int.MIN_VALUE
                    val until = settings.maxRandomValue?.toInt() ?: Int.MAX_VALUE
                    Random.nextInt(from, until)
                }
                java.lang.Long::class.java -> {
                    val from = settings.minRandomValue ?: Long.MIN_VALUE
                    val until = settings.maxRandomValue ?: Long.MAX_VALUE
                    Random.nextLong(from, until)
                }
                java.lang.Double::class.java -> {
                    val from = settings.minRandomValue?.toDouble() ?: Double.MIN_VALUE
                    val until = settings.maxRandomValue?.toDouble() ?: Double.MAX_VALUE
                    Random.nextDouble(from, until)
                }
                java.math.BigDecimal::class.java -> {
                    val from = settings.minRandomValue?.toDouble() ?: Double.MIN_VALUE
                    val until = settings.maxRandomValue?.toDouble() ?: Double.MAX_VALUE
                    Random.nextDouble(from, until).toBigDecimal()
                }
                else -> throw IllegalArgumentException("Unsupported number datatype")
            }

    private fun generateManualValue(settings: NumberPropGenSettings): Number? =
            when (settings.metaProperty.range.asDatatype<Any>().javaClass) {
                java.lang.Integer::class.java -> settings.manualIntegerValue?.toInt()
                java.lang.Long::class.java -> settings.manualIntegerValue
                java.lang.Double::class.java -> settings.manualFloatValue
                java.math.BigDecimal::class.java -> settings.manualFloatValue?.toBigDecimal()
                else -> throw IllegalArgumentException("Unsupported number datatype")
            }
}
