CREATE TABLE constants
(
    constant_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  datetime              NULL,
    is_deleted  BIT(1) DEFAULT 0      NULL,
    symbol      VARCHAR(50)           NULL,
    name        VARCHAR(255)          NULL,
    value_id    BIGINT                NULL,
    unit        VARCHAR(20)           NULL,
    CONSTRAINT pk_constants PRIMARY KEY (constant_id)
);

CREATE TABLE formula_compositions
(
    alias                VARCHAR(50) NOT NULL,
    main_formula_id      BIGINT      NOT NULL,
    component_formula_id BIGINT      NOT NULL,
    CONSTRAINT pk_formula_compositions PRIMARY KEY (main_formula_id, component_formula_id)
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
    is_deleted BIT(1) DEFAULT 0      NULL,
    name       VARCHAR(255)          NULL,
    expression VARCHAR(255)          NULL,
    CONSTRAINT pk_formulas PRIMARY KEY (formula_id)
);

CREATE TABLE parameters
(
    parameter_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   datetime              NULL,
    is_deleted   BIT(1) DEFAULT 0      NULL,
    symbol       VARCHAR(50)           NULL,
    name         VARCHAR(255)          NULL,
    unit         VARCHAR(20)           NULL,
    CONSTRAINT pk_parameters PRIMARY KEY (parameter_id)
);

ALTER TABLE constants
    ADD CONSTRAINT uc_constants_symbol UNIQUE (symbol);

ALTER TABLE constants
    ADD CONSTRAINT uc_constants_value UNIQUE (value_id);

ALTER TABLE formulas
    ADD CONSTRAINT uc_formulas_name UNIQUE (name);

ALTER TABLE parameters
    ADD CONSTRAINT uc_parameters_symbol UNIQUE (symbol);

ALTER TABLE constants
    ADD CONSTRAINT FK_CONSTANTS_ON_VALUE FOREIGN KEY (value_id) REFERENCES value (value_id);

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