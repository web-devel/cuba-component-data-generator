package com.haulmont.addon.datagen.service;

interface FakerService {
    companion object {
        const val NAME = "datagen_FakerService"
    }

    fun getProviderFunctionRefs(): List<String>

    fun generate(providerFunctionRef: String): String
}
