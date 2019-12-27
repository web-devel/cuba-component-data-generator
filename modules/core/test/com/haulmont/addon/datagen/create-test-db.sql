-- begin DATAGEN_TEST_ENTITY
create table DATAGEN_TEST_ENTITY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CITY varchar(255),
    IS_ACTIVE boolean,
    BIG_DECIMAL decimal(19, 2),
    DATE_ date,
    DATE_TIME timestamp,
    INTGR integer,
    LNG bigint,
    DBL double precision,
    TIME_ time,
    UUID varchar(36),
    ENUM_ATTR varchar(255),
    INT_ENUM_ATTR integer,
    USER_ASSOC_ONE_TO_ONE_ID varchar(36),
    USER_ASSOCIATION_MANY_TO_ONE_ID varchar(36),
    USER_COMPOSITION_ONE_TO_ONE_ID varchar(36),
    --
    primary key (ID)
)^
-- end DATAGEN_TEST_ENTITY
