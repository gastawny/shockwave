INSERT INTO users (email, first_name, last_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('a@a.com', 'admin', 'admin', '$2a$10$7EBRx3YDmoNtXUuv7zup3Oew8Spu0UvTaD9EimNuTIFslLMI17lCW', true, true, true, true);

INSERT INTO permissions (description)
VALUES  ('ADMIN'),
        ('MANAGER'),
        ('COMMON_USER');

INSERT INTO user_permission (user_id, permission_id) VALUES
	(1, 1),
    (1, 2);