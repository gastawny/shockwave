CREATE TABLE audit_logs
(
    audit_log_id BIGINT AUTO_INCREMENT NOT NULL,
    action       VARCHAR(20)  NOT NULL,
    entity_type  VARCHAR(100) NULL,
    entity_id    BIGINT       NULL,
    performed_by_id BIGINT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_audit_logs PRIMARY KEY (audit_log_id)
);

CREATE INDEX idx_audit_logs_action        ON audit_logs (action);
CREATE INDEX idx_audit_logs_entity_type   ON audit_logs (entity_type);
CREATE INDEX idx_audit_logs_performed_by_id ON audit_logs (performed_by_id);
