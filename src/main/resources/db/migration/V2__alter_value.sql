CREATE TABLE value
(
    value_id    BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_value PRIMARY KEY (value_id)
);

CREATE TABLE value_num
(
    value_id BIGINT NOT NULL,
    value   DOUBLE NULL,
    CONSTRAINT pk_valuenum PRIMARY KEY (value_id)
);

CREATE TABLE value_str
(
    value_id BIGINT       NOT NULL,
    value   VARCHAR(255) NULL,
    CONSTRAINT pk_valuestr PRIMARY KEY (value_id)
);

CREATE TABLE value_text
(
    value_id BIGINT       NOT NULL,
    value   TEXT NULL,
    CONSTRAINT pk_valuetext PRIMARY KEY (value_id)
);

ALTER TABLE value_num
    ADD CONSTRAINT FK_VALUENUM_ON_DATAID FOREIGN KEY (value_id) REFERENCES value (value_id);

ALTER TABLE value_str
    ADD CONSTRAINT FK_VALUESTR_ON_DATAID FOREIGN KEY (value_id) REFERENCES value (value_id);

ALTER TABLE value_text
    ADD CONSTRAINT FK_VALUETEXT_ON_DATAID FOREIGN KEY (value_id) REFERENCES value (value_id);

DROP TABLE value_entity;

DROP TABLE value_num_entity;

DROP TABLE value_str_entity;

DROP TABLE value_text_entity;


ALTER TABLE explosive_parameters
    ADD CONSTRAINT FK_EXPLOSIVE_PARAMETERS_ON_VALUE FOREIGN KEY (value_id) REFERENCES value (value_id);