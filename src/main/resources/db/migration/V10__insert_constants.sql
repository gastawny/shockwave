INSERT INTO value (value_id) VALUES
    (NULL),
    (NULL),
    (NULL),
    (NULL);

INSERT INTO value_num (value_id, value) VALUES
    (323, 3.14159),
    (324, 120),
    (325, 40),
    (326, 7);

INSERT INTO constants(symbol, name, value_id, unit) VALUES
    ('PI', 'Constante Pi', 323, NULL),
    ('FZ_DESABRIGADO', 'Fator Z - Proteção de fragmentos', 324, NULL),
    ('FZ_ABRIGADO', 'Fator Z - Proteção da sobrepressão', 325, NULL),
    ('FZ_RISCO_OPERACIONAL', 'Risco de lesão', 326, NULL);