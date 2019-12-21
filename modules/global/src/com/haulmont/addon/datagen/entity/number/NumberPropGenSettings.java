package com.haulmont.addon.datagen.entity.number;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_NumberPropGenSettings")
public class NumberPropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = -5151773813209426340L;

    @MetaProperty
    protected String strategy = NumberPropGenStrategy.RANDOM.getId();

    @MetaProperty
    protected Long manualIntegerValue;

    @MetaProperty
    protected Double manualFloatValue;

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

    public Double getManualFloatValue() {
        return manualFloatValue;
    }

    public void setManualFloatValue(Double manualFloatValue) {
        this.manualFloatValue = manualFloatValue;
    }

    public Long getManualIntegerValue() {
        return manualIntegerValue;
    }

    public void setManualIntegerValue(Long manualIntegerValue) {
        this.manualIntegerValue = manualIntegerValue;
    }

    public NumberPropGenStrategy getStrategy() {
        return strategy == null ? null : NumberPropGenStrategy.fromId(strategy);
    }

    public void setStrategy(NumberPropGenStrategy strategy) {
        this.strategy = strategy == null ? null : strategy.getId();
    }
}
