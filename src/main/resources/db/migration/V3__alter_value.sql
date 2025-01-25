ALTER TABLE value_entity
    DROP FOREIGN KEY FK_VALUEENTITY_ON_DATA;

ALTER TABLE value_num_entity
    DROP FOREIGN KEY FK_VALUENUMENTITY_ON_DATAID;

ALTER TABLE value_str_entity
    DROP FOREIGN KEY FK_VALUESTRENTITY_ON_DATAID;

ALTER TABLE value_text_entity
    DROP FOREIGN KEY FK_VALUETEXTENTITY_ON_DATAID;

CREATE TABLE value
(
    data_id    BIGINT   NOT NULL,
    CONSTRAINT pk_value PRIMARY KEY (data_id)
);

CREATE TABLE value_num
(
    data_id BIGINT NOT NULL,
    value   DOUBLE NULL,
    CONSTRAINT pk_valuenum PRIMARY KEY (data_id)
);

CREATE TABLE value_str
(
    data_id BIGINT       NOT NULL,
    value   VARCHAR(255) NULL,
    CONSTRAINT pk_valuestr PRIMARY KEY (data_id)
);

CREATE TABLE value_text
(
    data_id BIGINT       NOT NULL,
    value   TEXT NULL,
    CONSTRAINT pk_valuetext PRIMARY KEY (data_id)
);

ALTER TABLE value_num
    ADD CONSTRAINT FK_VALUENUM_ON_DATAID FOREIGN KEY (data_id) REFERENCES value (data_id);

ALTER TABLE value_str
    ADD CONSTRAINT FK_VALUESTR_ON_DATAID FOREIGN KEY (data_id) REFERENCES value (data_id);

ALTER TABLE value_text
    ADD CONSTRAINT FK_VALUETEXT_ON_DATAID FOREIGN KEY (data_id) REFERENCES value (data_id);

ALTER TABLE value
    ADD CONSTRAINT FK_VALUE_ON_DATA FOREIGN KEY (data_id) REFERENCES datas (data_id);

DROP TABLE value_entity;

DROP TABLE value_num_entity;

DROP TABLE value_str_entity;

DROP TABLE value_text_entity;
