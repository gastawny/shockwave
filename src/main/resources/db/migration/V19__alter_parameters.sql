ALTER TABLE parameters
    ADD `table` VARCHAR(50) NULL;

ALTER TABLE explosive_parameters
    DROP COLUMN sequence;

