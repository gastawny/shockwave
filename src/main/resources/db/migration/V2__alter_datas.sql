ALTER TABLE datas
    DROP FOREIGN KEY FK_DATAS_ON_LOCATED_OBJECT;

ALTER TABLE datas
    ADD explosive_id BIGINT NULL;

ALTER TABLE datas
    MODIFY explosive_id BIGINT NOT NULL;

ALTER TABLE datas
    ADD CONSTRAINT FK_DATAS_ON_EXPLOSIVE FOREIGN KEY (explosive_id) REFERENCES explosives (explosive_id);

ALTER TABLE datas
    DROP COLUMN located_object_id;