package com.haulmont.addon.datagen.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.app.dynamicattributes.PropertyType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "DATAGEN_TEST_ENTITY_JAVA")
@Entity(name = "datagen_TestEntityJava")
public class TestEntityJava extends StandardEntity {
    private static final long serialVersionUID = -1275833830632919018L;

    @Column(name = "CITY")
    protected String city;

    @Column(name = "BOOL")
    protected Boolean bool;

    @Column(name = "BIG_DECIMAL")
    protected BigDecimal bigDecimal;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    protected Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_TIME")
    protected Date dateTime;

    @Column(name = "INTGR")
    protected Integer intgr;

    @Column(name = "LNG")
    protected Long lng;

    @Column(name = "DBL")
    protected Double dbl;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_")
    protected Date time;

    @Column(name = "UUID_ATTR")
    protected UUID uuidAttr;

    @Column(name = "STR_ENUM")
    protected String strEnum;

    @Column(name = "INT_ENUM")
    protected String intEnum;

    @Column(name = "LOCAL_DATE")
    protected LocalDate localdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ASSOC_ONE_TO_ONE_ID")
    protected User userAssocOneToOne;

    @JoinTable(name = "DATAGEN_TEST_ENTITY_JAVA_USER_LINK",
            joinColumns = @JoinColumn(name = "TEST_ENTITY_JAVA_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    @ManyToMany
    protected List<User> userAssociationManyToOne;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_COMPOSITION_ONE_TO_ONE_ID")
    protected User userCompositionOneToOne;

    public User getUserCompositionOneToOne() {
        return userCompositionOneToOne;
    }

    public void setUserCompositionOneToOne(User userCompositionOneToOne) {
        this.userCompositionOneToOne = userCompositionOneToOne;
    }

    public List<User> getUserAssociationManyToOne() {
        return userAssociationManyToOne;
    }

    public void setUserAssociationManyToOne(List<User> userAssociationManyToOne) {
        this.userAssociationManyToOne = userAssociationManyToOne;
    }

    public User getUserAssocOneToOne() {
        return userAssocOneToOne;
    }

    public void setUserAssocOneToOne(User userAssocOneToOne) {
        this.userAssocOneToOne = userAssocOneToOne;
    }

    public PropertyType getIntEnum() {
        return intEnum == null ? null : PropertyType.fromId(intEnum);
    }

    public void setIntEnum(PropertyType intEnum) {
        this.intEnum = intEnum == null ? null : intEnum.getId();
    }

    public PropertyType getStrEnum() {
        return strEnum == null ? null : PropertyType.fromId(strEnum);
    }

    public void setStrEnum(PropertyType strEnum) {
        this.strEnum = strEnum == null ? null : strEnum.getId();
    }

    public UUID getUuidAttr() {
        return uuidAttr;
    }

    public void setUuidAttr(UUID uuidAttr) {
        this.uuidAttr = uuidAttr;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getDbl() {
        return dbl;
    }

    public void setDbl(Double dbl) {
        this.dbl = dbl;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public Integer getIntgr() {
        return intgr;
    }

    public void setIntgr(Integer intgr) {
        this.intgr = intgr;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getLocaldate() {
        return localdate;
    }

    public void setLocaldate(LocalDate localdate) {
        this.localdate = localdate;
    }
}
