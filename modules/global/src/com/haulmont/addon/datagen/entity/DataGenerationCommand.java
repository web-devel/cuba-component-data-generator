package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Entity;

@MetaClass(name = "datagen_DataGenerationCommand")
public class DataGenerationCommand<T extends Entity<?>> extends BaseUuidEntity {
    private static final long serialVersionUID = -8098657610737920471L;

    @MetaProperty
    protected Boolean saveExecutionLog = true;

    @MetaProperty
    protected String type = DataGenerationType.COMMIT.getId();

    @MetaProperty
    protected Integer amount = 1;

    @MetaProperty
    protected EntityGenerationSettings<T> entityGenerationSettings;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public DataGenerationType getType() {
        return type == null ? null : DataGenerationType.fromId(type);
    }

    public void setType(DataGenerationType type) {
        this.type = type == null ? null : type.getId();
    }

    public void setEntityGenerationSettings(EntityGenerationSettings<T> entityGenerationSettings) {
        this.entityGenerationSettings = entityGenerationSettings;
    }

    public EntityGenerationSettings<T> getEntityGenerationSettings() {
        return entityGenerationSettings;
    }

    public Boolean getSaveExecutionLog() {
        return saveExecutionLog;
    }

    public void setSaveExecutionLog(Boolean saveExecutionLog) {
        this.saveExecutionLog = saveExecutionLog;
    }
}
