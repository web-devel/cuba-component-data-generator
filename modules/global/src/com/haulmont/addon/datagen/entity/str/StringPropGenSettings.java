package com.haulmont.addon.datagen.entity.str;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_StringPropertyGenerationSettings")
public class StringPropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = 5115061741685345563L;
    public static final String DELIMITER = "::";

    @MetaProperty
    protected String manualValue;

    @MetaProperty
    protected String strategy = StringPropGenStrategy.FAKER.getId();

    @MetaProperty
    protected String fakerFunctionRef;


    public String getFakerFunctionRef() {
        return fakerFunctionRef;
    }

    public void setFakerFunctionRef(String fakerFunctionRef) {
        this.fakerFunctionRef = fakerFunctionRef;
    }

    public StringPropGenStrategy getStrategy() {
        return strategy == null ? null : StringPropGenStrategy.fromId(strategy);
    }

    public void setStrategy(StringPropGenStrategy strategy) {
        this.strategy = strategy == null ? null : strategy.getId();
    }

    public String getManualValue() {
        return manualValue;
    }

    public void setManualValue(String manualValue) {
        this.manualValue = manualValue;
    }
}
