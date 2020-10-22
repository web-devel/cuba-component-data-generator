package com.haulmont.addon.datagen.entity.localdate;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_LocalDatePropGenSettings")
public class LocalDatePropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = -5151773813209426340L;

    @MetaProperty
    protected String strategy = LocalDatePropGenStrategy.RANDOM.getId();

    @MetaProperty
    protected Long manualIntegerValue;

    @MetaProperty
    protected Long minRandomValue;

    @MetaProperty
    protected Long maxRandomValue;

    public Long getMaxRandomValue() {
        return maxRandomValue;
    }

    public void setMaxRandomValue(Long maxRandomValue) {
        this.maxRandomValue = maxRandomValue;
    }

    public Long getMinRandomValue() {
        return minRandomValue;
    }

    public void setMinRandomValue(Long minRandomValue) {
        this.minRandomValue = minRandomValue;
    }

    public Long getManualIntegerValue() {
        return manualIntegerValue;
    }

    public void setManualIntegerValue(Long manualIntegerValue) {
        this.manualIntegerValue = manualIntegerValue;
    }

    public LocalDatePropGenStrategy getStrategy() {
        return strategy == null ? null : LocalDatePropGenStrategy.fromId(strategy);
    }

    public void setStrategy(LocalDatePropGenStrategy strategy) {
        this.strategy = strategy == null ? null : strategy.getId();
    }
}
