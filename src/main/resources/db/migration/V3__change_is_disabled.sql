ALTER TABLE files
    ALTER deleted SET DEFAULT (0);

ALTER TABLE bomb_threats
    ALTER deleted SET DEFAULT (0);

ALTER TABLE explosives
    ALTER deleted SET DEFAULT (0);

ALTER TABLE form_threats
    ALTER deleted SET DEFAULT (0);

ALTER TABLE grounds
    ALTER deleted SET DEFAULT (0);

ALTER TABLE located_objects
    ALTER deleted SET DEFAULT (0);

ALTER TABLE object_formats
    ALTER deleted SET DEFAULT (0);

ALTER TABLE permissions
    ALTER deleted SET DEFAULT (0);

ALTER TABLE post_explosions
    ALTER deleted SET DEFAULT (0);

ALTER TABLE parameters
    ALTER deleted SET DEFAULT (0);