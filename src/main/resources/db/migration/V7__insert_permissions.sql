INSERT INTO users (user_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('admin', '$2a$10$GhRHRKL48PeUkklsgwOl7eiSS74mb2/K78Hu/4EHISAF9kSyufdKS', true, true, true, true);

INSERT INTO permissions (description)
VALUES  ('ADMIN'),
        ('MANAGER'),
        ('COMMON_USER');

INSERT INTO user_permission (user_id, permission_id) VALUES
	(1, 1);