CREATE TABLE parameter_dependencies
(
    parameter_dependency_id BIGINT AUTO_INCREMENT NOT NULL,
    parameter_id            BIGINT                NOT NULL,
    reference_table         VARCHAR(50)           NOT NULL,
    reference_column        VARCHAR(50)           NOT NULL,
    reference_id            BIGINT                NULL,
    CONSTRAINT pk_parameter_dependencies PRIMARY KEY (parameter_dependency_id)
);

ALTER TABLE parameter_dependencies
    ADD CONSTRAINT uc_parameter_dependencies_parameter UNIQUE (parameter_id);

ALTER TABLE parameter_dependencies
    ADD CONSTRAINT FK_PARAMETER_DEPENDENCIES_ON_PARAMETER FOREIGN KEY (parameter_id) REFERENCES parameters (parameter_id);


CREATE TABLE formula_dependencies
(
    formula_dependency_id BIGINT AUTO_INCREMENT NOT NULL,
    alias                 VARCHAR(50)           NOT NULL,
    reference_table       VARCHAR(50)           NOT NULL,
    CONSTRAINT pk_formula_dependencies PRIMARY KEY (formula_dependency_id)
);

CREATE TABLE formula_dependency_mappings
(
    reference_id          BIGINT NOT NULL,
    formula_dependency_id BIGINT NOT NULL,
    formula_id            BIGINT NOT NULL,
    CONSTRAINT pk_formula_dependency_mappings PRIMARY KEY (formula_dependency_id, formula_id)
);

ALTER TABLE formula_dependency_mappings
    ADD CONSTRAINT FK_FORMULA_DEPENDENCY_MAPPINGS_ON_FORMULA FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_dependency_mappings
    ADD CONSTRAINT FK_FORMULA_DEPENDENCY_MAPPINGS_ON_FORMULA_DEPENDENCY FOREIGN KEY (formula_dependency_id) REFERENCES formula_dependencies (formula_dependency_id);


CREATE TABLE formula_formula_dependencies
(
    formula_dependency_id BIGINT NOT NULL,
    formula_id            BIGINT NOT NULL
);

ALTER TABLE formula_formula_dependencies
    ADD CONSTRAINT fk_forfordep_on_formula FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_formula_dependencies
    ADD CONSTRAINT fk_forfordep_on_formula_dependency FOREIGN KEY (formula_dependency_id) REFERENCES formula_dependencies (formula_dependency_id);
