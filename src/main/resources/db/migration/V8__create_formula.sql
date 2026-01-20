CREATE TABLE constants
(
    constant_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  datetime              NULL,
    deleted  BIT(1) DEFAULT 0      NULL,
    symbol      VARCHAR(50)           NULL,
    name        VARCHAR(255)          NULL,
    value_id    BIGINT                NULL,
    unit        VARCHAR(20)           NULL,
    CONSTRAINT pk_constants PRIMARY KEY (constant_id)
);

CREATE TABLE formula_compositions
(
    formula_composition_id BIGINT AUTO_INCREMENT NOT NULL,
    main_formula_id        BIGINT                NOT NULL,
    component_formula_id   BIGINT                NOT NULL,
    alias                  VARCHAR(50)           NOT NULL,
    CONSTRAINT pk_formula_compositions PRIMARY KEY (formula_composition_id)
);

CREATE TABLE formula_constants
(
    constant_id BIGINT NOT NULL,
    formula_id  BIGINT NOT NULL
);

CREATE TABLE formula_parameters
(
    formula_id   BIGINT NOT NULL,
    parameter_id BIGINT NOT NULL
);

CREATE TABLE formulas
(
    formula_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at datetime              NULL,
    deleted BIT(1) DEFAULT 0      NULL,
    name       VARCHAR(255)          NULL,
    expression VARCHAR(255)          NULL,
    CONSTRAINT pk_formulas PRIMARY KEY (formula_id)
);

CREATE TABLE object_format_parameters
(
    object_format_parameter_id  BIGINT AUTO_INCREMENT NOT NULL,
    object_format_id            BIGINT NOT NULL,
    parameter_id                BIGINT NOT NULL,
    CONSTRAINT pk_object_format_parameter_id PRIMARY KEY (object_format_parameter_id)
);

ALTER TABLE object_format_parameters
    ADD CONSTRAINT fk_objforpar_on_object_format FOREIGN KEY (object_format_id) REFERENCES object_formats (object_format_id);

ALTER TABLE object_format_parameters
    ADD CONSTRAINT fk_objforpar_on_parameter FOREIGN KEY (parameter_id) REFERENCES parameters (parameter_id);

ALTER TABLE constants
    ADD CONSTRAINT uc_constants_symbol UNIQUE (symbol);

ALTER TABLE constants
    ADD CONSTRAINT uc_constants_value UNIQUE (value_id);

ALTER TABLE formulas
    ADD CONSTRAINT uc_formulas_name UNIQUE (name);

ALTER TABLE constants
    ADD CONSTRAINT FK_CONSTANTS_ON_VALUE FOREIGN KEY (value_id) REFERENCES value (value_id);

ALTER TABLE formula_compositions
    ADD CONSTRAINT uk_main_component_formula UNIQUE (main_formula_id, component_formula_id);

ALTER TABLE formula_compositions
    ADD CONSTRAINT FK_FORMULA_COMPOSITIONS_ON_COMPONENT_FORMULA FOREIGN KEY (component_formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_compositions
    ADD CONSTRAINT FK_FORMULA_COMPOSITIONS_ON_MAIN_FORMULA FOREIGN KEY (main_formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_constants
    ADD CONSTRAINT fk_forcon_on_constant FOREIGN KEY (constant_id) REFERENCES constants (constant_id);

ALTER TABLE formula_constants
    ADD CONSTRAINT fk_forcon_on_formula FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_parameters
    ADD CONSTRAINT fk_forpar_on_formula FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_parameters
    ADD CONSTRAINT fk_forpar_on_parameter FOREIGN KEY (parameter_id) REFERENCES parameters (parameter_id);