ALTER TABLE archives
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE bomb_threats
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE data_types
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE datas
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE explosives
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE form_threats
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE grounds
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE located_objects
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE object_formats
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE object_formula_parameter_values
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE object_formula_parameters
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE permissions
    ALTER is_deleted SET DEFAULT (0);

ALTER TABLE post_explosions
    ALTER is_deleted SET DEFAULT (0);