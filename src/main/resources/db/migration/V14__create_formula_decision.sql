CREATE TABLE formula_decision
(
    formula_decision_id BIGINT AUTO_INCREMENT NOT NULL,
    formula_id          BIGINT                NULL,
    CONSTRAINT pk_formula_decision PRIMARY KEY (formula_decision_id)
);

CREATE TABLE formula_decision_mappings
(
    formula_decision_id BIGINT NOT NULL,
    formula_id          BIGINT NOT NULL
);

ALTER TABLE formula_decision
    ADD CONSTRAINT uc_formula_decision_formula UNIQUE (formula_id);

ALTER TABLE formula_decision
    ADD CONSTRAINT FK_FORMULA_DECISION_ON_FORMULA FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_decision_mappings
    ADD CONSTRAINT fk_fordecmap_on_formula FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);

ALTER TABLE formula_decision_mappings
    ADD CONSTRAINT fk_fordecmap_on_formula_decision FOREIGN KEY (formula_decision_id) REFERENCES formula_decision (formula_decision_id);