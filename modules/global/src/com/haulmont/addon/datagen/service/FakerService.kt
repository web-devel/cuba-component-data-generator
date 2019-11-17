package com.haulmont.addon.datagen.service;

interface FakerService {
    companion object {
        const val NAME = "datagen_FakerService"
    }

    fun getProviderNamesList(): List<String>

    fun getProviderFunctionsNameList(providerName: String): List<String>

    fun generate(providerName: String, funName: String): String
}
