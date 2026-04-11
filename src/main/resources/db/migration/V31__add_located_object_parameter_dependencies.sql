ALTER TABLE parameter_dependencies
    ADD COLUMN context_fk_column VARCHAR(50) NULL;

-- tab_k (parameter_id=5) lives in grounds.k; the FK from located_objects to grounds is ground_id
UPDATE parameter_dependencies
SET context_fk_column = 'ground_id'
WHERE parameter_id = 5;

-- Register R (distance) as a DB-driven parameter resolved from located_objects.distance
INSERT INTO parameters(symbol, name, unit, value_type)
VALUES ('R', 'Distância', 'm', 'NUMBER');

INSERT INTO parameter_dependencies(parameter_id, reference_table, reference_column, context_fk_column)
VALUES ((SELECT parameter_id FROM parameters WHERE symbol = 'R'), 'located_objects', 'distance', NULL);

-- Register dep_volume as a DB-driven parameter resolved from located_objects.object_format_id
INSERT INTO parameters(symbol, name, unit, value_type)
VALUES ('dep_volume', 'Volume', NULL, 'NUMBER');

INSERT INTO parameter_dependencies(parameter_id, reference_table, reference_column, context_fk_column)
VALUES ((SELECT parameter_id FROM parameters WHERE symbol = 'dep_volume'), 'located_objects', 'object_format_id', NULL);
