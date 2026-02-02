CREATE TABLE formula_tables
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    table_name VARCHAR(255)          NULL,
    formula_id BIGINT                NULL,
    CONSTRAINT pk_formula_tables PRIMARY KEY (id)
);

ALTER TABLE formula_tables
    ADD CONSTRAINT FK_FORMULA_TABLES_ON_FORMULA FOREIGN KEY (formula_id) REFERENCES formulas (formula_id);