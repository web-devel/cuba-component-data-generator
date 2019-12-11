package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@MetaClass(name = "datagen_EntityGenerationSettings")
public class EntityGenerationSettings<T extends Entity<?>> extends BaseUuidEntity {
    private static final long serialVersionUID = -8648617942361377567L;

    @MetaProperty
    protected Integer amount = 1;

    @MetaProperty
    protected List<PropertyGenerationSettings> properties = new ArrayList<>();

    private Class<T> entityClass;

    public void setProperties(List<PropertyGenerationSettings> properties) {
        this.properties = properties;
    }

    public List<PropertyGenerationSettings> getProperties() {
        return properties;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}