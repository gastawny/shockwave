CREATE TABLE object_format_parameter_values
(
    object_format_parameter_value_id BIGINT AUTO_INCREMENT NOT NULL,
    located_object_id                BIGINT                NOT NULL,
    object_format_parameter_id       BIGINT                NOT NULL,
    value_id                         BIGINT                NOT NULL,
    CONSTRAINT pk_object_format_parameter_values PRIMARY KEY (object_format_parameter_value_id)
);

ALTER TABLE object_format_parameter_values
    ADD CONSTRAINT uc_object_format_parameter_values_value UNIQUE (value_id);

ALTER TABLE object_format_parameter_values
    ADD CONSTRAINT FK_OBJECT_FORMAT_PARAMETER_VALUES_ON_LOCATED_OBJECT FOREIGN KEY (located_object_id) REFERENCES located_objects (located_object_id);

ALTER TABLE object_format_parameter_values
    ADD CONSTRAINT FK_OBJECT_FORMAT_PARAMETER_VALUES_ON_OBJECT_FORMAT_PARAMETER FOREIGN KEY (object_format_parameter_id) REFERENCES object_format_parameters (object_format_parameter_id);

ALTER TABLE object_format_parameter_values
    ADD CONSTRAINT FK_OBJECT_FORMAT_PARAMETER_VALUES_ON_VALUE FOREIGN KEY (value_id) REFERENCES value_num (value_id);