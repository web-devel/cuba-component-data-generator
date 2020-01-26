package com.haulmont.addon.datagen.entity.ref;

import com.haulmont.addon.datagen.entity.PropertyGenerationSettings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseGenericIdEntity;

@MetaClass(name = "datagen_ReferencePropGenSettings")
public class ReferencePropGenSettings extends PropertyGenerationSettings {
    private static final long serialVersionUID = -5781450551570383283L;

    @MetaProperty
    protected BaseGenericIdEntity referenceEntity;

    public BaseGenericIdEntity getReferenceEntity() {
        return referenceEntity;
    }

    public void setReferenceEntity(BaseGenericIdEntity referenceEntity) {
        this.referenceEntity = referenceEntity;
    }

}
