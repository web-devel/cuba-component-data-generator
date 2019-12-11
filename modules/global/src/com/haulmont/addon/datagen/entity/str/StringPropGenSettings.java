package com.haulmont.addon.datagen.entity.str;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

@MetaClass(name = "datagen_StringPropertyGenerationSettings")
public class StringPropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = 5115061741685345563L;

    @MetaProperty
    protected String manualValue;

    @MetaProperty
    protected String strategy = StringPropGenStrategy.FAKER.getId();

    @MetaProperty
    protected String fakerProvider;

    @MetaProperty
    protected String fakerProviderFunction;

    public String getFakerProviderFunction() {
        return fakerProviderFunction;
    }

    public void setFakerProviderFunction(String fakerProviderFunction) {
        this.fakerProviderFunction = fakerProviderFunction;
    }

    public String getFakerProvider() {
        return fakerProvider;
    }

    public void setFakerProvider(String fakerProvider) {
        this.fakerProvider = fakerProvider;
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