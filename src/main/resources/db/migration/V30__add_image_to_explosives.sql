ALTER TABLE explosives ADD COLUMN image_id BIGINT;
ALTER TABLE explosives ADD CONSTRAINT fk_explosives_image FOREIGN KEY (image_id) REFERENCES files (file_id);
