-- Add discriminator column to value table for JOINED inheritance
ALTER TABLE value ADD COLUMN value_type VARCHAR(10) NOT NULL DEFAULT 'unknown';

-- Populate the discriminator based on existing data
UPDATE value SET value_type = 'number' WHERE EXISTS (SELECT 1 FROM value_num vn WHERE vn.value_id = value.value_id) AND value_type = 'unknown';
UPDATE value SET value_type = 'string' WHERE EXISTS (SELECT 1 FROM value_str vs WHERE vs.value_id = value.value_id) AND value_type = 'unknown';
UPDATE value SET value_type = 'text' WHERE EXISTS (SELECT 1 FROM value_text vt WHERE vt.value_id = value.value_id) AND value_type = 'unknown';

-- For remaining rows without a subclass, create a text subclass row and mark them as 'text'
INSERT INTO value_text (value_id, value)
SELECT v.value_id, NULL
FROM value v
WHERE v.value_type = 'unknown'
  AND NOT EXISTS (SELECT 1 FROM value_text vt WHERE vt.value_id = v.value_id)
  AND NOT EXISTS (SELECT 1 FROM value_num vn WHERE vn.value_id = v.value_id)
  AND NOT EXISTS (SELECT 1 FROM value_str vs WHERE vs.value_id = v.value_id);

UPDATE value SET value_type = 'text' WHERE value_type = 'unknown';

-- Optionally change default to remove 'unknown' default (leave as-is if you prefer)
ALTER TABLE value ALTER COLUMN value_type DROP DEFAULT;
