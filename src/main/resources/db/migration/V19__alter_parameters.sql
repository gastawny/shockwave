ALTER TABLE parameters
    ADD table_name VARCHAR(50) NULL;

ALTER TABLE explosive_parameters
    DROP COLUMN sequence;

