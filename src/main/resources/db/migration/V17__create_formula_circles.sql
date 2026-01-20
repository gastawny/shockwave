CREATE TABLE formula_circles
(
    formula_circle_id BIGINT AUTO_INCREMENT NOT NULL,
    formula_id        BIGINT                NOT NULL,
    color             VARCHAR(10)           NOT NULL,
    CONSTRAINT pk_formula_circles PRIMARY KEY (formula_circle_id)
);

ALTER TABLE formula_circles
    ADD CONSTRAINT uc_formula_circles_formula UNIQUE (formula_id);

ALTER TABLE formula_circles
    ADD CONSTRAINT FK_FORMULA_CIRCLES_ON_FORMULA FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);