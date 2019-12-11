package com.haulmont.addon.datagen.entity.bool;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_BooleanPropertyGenerationSettings")
public class BooleanPropertyGenerationSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = -891814723279425924L;

    @MetaProperty
    protected Boolean manualValue;

    @MetaProperty
    protected String strategy = BooleanPropertyGenerationStrategy.RANDOM.getId();

    public BooleanPropertyGenerationStrategy getStrategy() {
        return strategy == null ? null : BooleanPropertyGenerationStrategy.fromId(strategy);
    }

    public void setStrategy(BooleanPropertyGenerationStrategy strategy) {
        this.strategy = strategy == null ? null : strategy.getId();
    }

    public Boolean getManualValue() {
        return manualValue;
    }

    public void setManualValue(Boolean manualValue) {
        this.manualValue = manualValue;
    }
}