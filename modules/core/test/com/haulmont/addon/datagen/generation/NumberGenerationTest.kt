package com.haulmont.addon.datagen.generation

import com.haulmont.addon.datagen.DatagenTestContainer
import org.junit.ClassRule

class NumberGenerationTest {

    companion object {
        @ClassRule
        @JvmField
        var cont: DatagenTestContainer = DatagenTestContainer.Common.INSTANCE
    }
}
