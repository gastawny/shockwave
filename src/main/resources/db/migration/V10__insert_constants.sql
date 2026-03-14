INSERT INTO value (value_id) VALUES
    (NULL),
    (NULL),
    (NULL),
    (NULL);

INSERT INTO value_num (value_id, value) VALUES
    (346, 3.14159),
    (347, 120),
    (348, 40),
    (349, 7);

INSERT INTO constants(symbol, name, value_id, unit) VALUES
    ('PI', 'Constante Pi', 346, NULL),
    ('FZ_DESABRIGADO', 'Fator Z - Proteção de fragmentos', 347, NULL),
    ('FZ_ABRIGADO', 'Fator Z - Proteção da sobrepressão', 348, NULL),
    ('FZ_RISCO_OPERACIONAL', 'Risco de lesão', 349, NULL);