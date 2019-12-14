package com.haulmont.addon.datagen.entity.enm;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_EnumPropGenSettings")
public class EnumPropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = 4050643602429340299L;

    @MetaProperty
    protected String strategy = EnumPropGenStrategy.RANDOM.getId();

    protected Enum manualValue;

    public EnumPropGenStrategy getStrategy() {
        return strategy == null ? null : EnumPropGenStrategy.fromId(strategy);
    }

    public void setStrategy(EnumPropGenStrategy strategy) {
        this.strategy = strategy == null ? null : strategy.getId();
    }

    public Enum getManualValue() {
        return manualValue;
    }

    public void setManualValue(Enum manualValue) {
        this.manualValue = manualValue;
    }
}