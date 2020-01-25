package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Creatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@NamePattern("%s|entityName")
@Table(name = "DATAGEN_GENERATED_ENTITY")
@Entity(name = "datagen_GeneratedEntity")
public class GeneratedEntity extends BaseUuidEntity implements Creatable {
    private static final long serialVersionUID = 1202733793169790191L;

    @Column(name = "ENTITY_NAME")
    protected String entityName;

    @Column(name = "ENTITY_ID")
    protected String instanceId;

    @Lob
    @Column(name = "INST_NAME")
    protected String instName;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreateTs() {
        return createTs;
    }

    @Override
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

}
