package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.model.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "datagen_PropertyGenerationSettings")
public class PropertyGenerationSettings extends BaseUuidEntity {
    private static final long serialVersionUID = -8891244912714529962L;

    private MetaProperty metaProperty;

    public MetaProperty getMetaProperty() {
        return metaProperty;
    }

    public void setMetaProperty(MetaProperty metaProperty) {
        this.metaProperty = metaProperty;
    }
}