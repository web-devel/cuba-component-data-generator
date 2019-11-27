package com.haulmont.addon.datagen.entity

import com.haulmont.chile.core.annotations.Composition
import com.haulmont.cuba.core.entity.StandardEntity
import com.haulmont.cuba.core.entity.annotation.OnDelete
import com.haulmont.cuba.core.global.DeletePolicy
import com.haulmont.cuba.security.entity.User
import com.haulmont.cuba.security.entity.UserSubstitution
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Table(name = "DATAGEN_TEST_ENTITY")
@Entity(name = "datagen_TestEntity")
class TestEntity : StandardEntity() {

    @Column(name = "CITY")
    var city: String? = null

    @Column(name = "IS_ACTIVE")
    var bool: Boolean? = null
        private set

    @Column(name = "BIG_DECIMAL")
    var bigDecimal: BigDecimal? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    var date: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_TIME")
    var dateTime: Date? = null

    @Column(name = "INTGR")
    var intgr: Int? = null

    @Column(name = "LNG")
    var lng: Long? = null

    @Column(name = "DBL")
    var dbl: Double? = null

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_")
    var time: Date? = null

    @Column(name = "UUID")
    var uuidAttr: UUID? = null

    @Column(name = "ENUM_ATTR")
    var strEnumAttr: String? = null
        private set

    @Column(name = "INT_ENUM_ATTR")
    var intEnumAttr: Int? = null
        private set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ASSOC_ONE_TO_ONE_ID")
    var userAssocOneToOne: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ASSOCIATION_MANY_TO_ONE_ID")
    var userAssociationManyToOne: UserSubstitution? = null

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_COMPOSITION_ONE_TO_ONE_ID")
    var userCompositionOneToOne: User? = null

    fun getIntEnumAttr(): TestIntEnum? = intEnumAttr?.let { TestIntEnum.fromId(it) }

    fun setIntEnumAttr(intEnumAttr: TestIntEnum?) {
        this.intEnumAttr = intEnumAttr?.id
    }

    fun getStrEnumAttr(): TestStringEnum? = strEnumAttr?.let { TestStringEnum.fromId(it) }

    fun setStrEnumAttr(enumAttr: TestStringEnum?) {
        this.strEnumAttr = enumAttr?.id
    }

    companion object {
        private const val serialVersionUID = 8614053829978231620L
    }
}
