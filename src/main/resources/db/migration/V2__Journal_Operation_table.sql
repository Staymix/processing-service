create table JOURNAL_OPERATION (
    UID char(36) not null,
    ACCOUNT_ID bigint not null,
    USER_ID bigint not null,
    CREATED timestamp not null,
    primary key (ACCOUNT_ID, UID)
);