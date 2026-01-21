CREATE TABLE archives
(
    archive_id BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NULL,
    `path`     VARCHAR(255) NULL,
    data       BLOB NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_archives PRIMARY KEY (archive_id)
);

CREATE TABLE bomb_threat_archive
(
    archive_id     BIGINT NOT NULL,
    bomb_threat_id BIGINT NOT NULL
);

CREATE TABLE bomb_threats
(
    bomb_threat_id               BIGINT AUTO_INCREMENT NOT NULL,
    user_id                      BIGINT NULL,
    form_threat_id               BIGINT NULL,
    located_object_id            BIGINT NULL,
    name                         VARCHAR(255) NULL,
    form_threat_description      TEXT NULL,
    object_not_found_description TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_bomb_threats PRIMARY KEY (bomb_threat_id)
);

CREATE TABLE explosive_parameters
(
    explosive_parameter_id BIGINT AUTO_INCREMENT NOT NULL,
    explosive_id           BIGINT                NULL,
    parameter_id           BIGINT                NULL,
    sequence               TINYINT               NOT NULL,
    value_id               BIGINT                NULL,
    CONSTRAINT pk_explosive_parameters PRIMARY KEY (explosive_parameter_id)
);

CREATE TABLE explosives
(
    explosive_id BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_explosives PRIMARY KEY (explosive_id)
);

CREATE TABLE form_threats
(
    form_threat_id BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_form_threats PRIMARY KEY (form_threat_id)
);

CREATE TABLE grounds
(
    ground_id  BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    k          DOUBLE NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_grounds PRIMARY KEY (ground_id)
);

CREATE TABLE located_objects
(
    located_object_id BIGINT AUTO_INCREMENT NOT NULL,
    explosive_id      BIGINT NULL,
    ground_id         BIGINT NULL,
    object_format_id  BIGINT NULL,
    name              VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_located_objects PRIMARY KEY (located_object_id)
);

CREATE TABLE object_formats
(
    object_format_id BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_object_formats PRIMARY KEY (object_format_id)
);

CREATE TABLE permissions
(
    permission_id BIGINT AUTO_INCREMENT NOT NULL,
    `description` VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_permissions PRIMARY KEY (permission_id)
);

CREATE TABLE post_explosion_archive
(
    archive_id        BIGINT NOT NULL,
    post_explosion_id BIGINT NOT NULL
);

CREATE TABLE post_explosions
(
    post_explosion_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id           BIGINT NULL,
    vestige_distance DOUBLE NULL,
    `description`     VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_post_explosions PRIMARY KEY (post_explosion_id)
);

CREATE TABLE user_permission
(
    permission_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL
);

CREATE TABLE users
(
    user_id                 BIGINT AUTO_INCREMENT NOT NULL,
    user_name               VARCHAR(255) NULL,
    password                VARCHAR(255) NULL,
    account_non_expired     BIT(1) NULL,
    account_non_locked      BIT(1) NULL,
    credentials_non_expired BIT(1) NULL,
    enabled                 BIT(1) NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE value_entity
(
    data_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_valueentity PRIMARY KEY (data_id)
);

CREATE TABLE value_num_entity
(
    data_id BIGINT NOT NULL,
    value DOUBLE NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_valuenumentity PRIMARY KEY (data_id)
);

CREATE TABLE value_str_entity
(
    data_id BIGINT NOT NULL,
    value   VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_valuestrentity PRIMARY KEY (data_id)
);

CREATE TABLE value_text_entity
(
    data_id BIGINT NOT NULL,
    value   TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted BIT(1) NULL,
    CONSTRAINT pk_valuetextentity PRIMARY KEY (data_id)
);

CREATE TABLE parameters
(
    parameter_id BIGINT AUTO_INCREMENT  NOT NULL,
    deleted      BIT(1)                 NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NULL,
    symbol       VARCHAR(50)            NULL,
    name         VARCHAR(100)           NULL,
    unit         VARCHAR(20)            NULL,
    value_type   VARCHAR(20)            NULL,
    CONSTRAINT pk_parameters PRIMARY KEY (parameter_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_user_name UNIQUE (user_name);

ALTER TABLE bomb_threats
    ADD CONSTRAINT FK_BOMB_THREATS_ON_FORM_THREAT FOREIGN KEY (form_threat_id) REFERENCES form_threats (form_threat_id);

ALTER TABLE bomb_threats
    ADD CONSTRAINT FK_BOMB_THREATS_ON_LOCATED_OBJECT FOREIGN KEY (located_object_id) REFERENCES located_objects (located_object_id);

ALTER TABLE bomb_threats
    ADD CONSTRAINT FK_BOMB_THREATS_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE explosive_parameters
    ADD CONSTRAINT uc_explosive_parameters_value UNIQUE (value_id);

ALTER TABLE explosive_parameters
    ADD CONSTRAINT FK_EXPLOSIVE_PARAMETERS_ON_EXPLOSIVE FOREIGN KEY (explosive_id) REFERENCES explosives (explosive_id);

ALTER TABLE explosive_parameters
    ADD CONSTRAINT FK_EXPLOSIVE_PARAMETERS_ON_PARAMETER FOREIGN KEY (parameter_id) REFERENCES parameters (parameter_id);

ALTER TABLE located_objects
    ADD CONSTRAINT FK_LOCATED_OBJECTS_ON_EXPLOSIVE FOREIGN KEY (explosive_id) REFERENCES explosives (explosive_id);

ALTER TABLE located_objects
    ADD CONSTRAINT FK_LOCATED_OBJECTS_ON_GROUND FOREIGN KEY (ground_id) REFERENCES grounds (ground_id);

ALTER TABLE located_objects
    ADD CONSTRAINT FK_LOCATED_OBJECTS_ON_OBJECT_FORMAT FOREIGN KEY (object_format_id) REFERENCES object_formats (object_format_id);

ALTER TABLE post_explosions
    ADD CONSTRAINT FK_POST_EXPLOSIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE bomb_threat_archive
    ADD CONSTRAINT fk_bomthrarc_on_archive_entity FOREIGN KEY (archive_id) REFERENCES archives (archive_id);

ALTER TABLE bomb_threat_archive
    ADD CONSTRAINT fk_bomthrarc_on_bomb_threat_entity FOREIGN KEY (bomb_threat_id) REFERENCES bomb_threats (bomb_threat_id);

ALTER TABLE post_explosion_archive
    ADD CONSTRAINT fk_posexparc_on_archive_entity FOREIGN KEY (archive_id) REFERENCES archives (archive_id);

ALTER TABLE post_explosion_archive
    ADD CONSTRAINT fk_posexparc_on_post_explosion_entity FOREIGN KEY (post_explosion_id) REFERENCES post_explosions (post_explosion_id);

ALTER TABLE user_permission
    ADD CONSTRAINT fk_useper_on_permission_entity FOREIGN KEY (permission_id) REFERENCES permissions (permission_id);

ALTER TABLE user_permission
    ADD CONSTRAINT fk_useper_on_user_entity FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE parameters
    ADD CONSTRAINT uc_parameters_name UNIQUE (name);

ALTER TABLE parameters
    ADD CONSTRAINT uc_parameters_symbol UNIQUE (symbol);