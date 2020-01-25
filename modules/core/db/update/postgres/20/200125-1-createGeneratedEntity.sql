create table DATAGEN_GENERATED_ENTITY (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    --
    ENTITY_NAME varchar(255),
    ENTITY_ID varchar(255),
    INST_NAME text,
    --
    primary key (ID)
);