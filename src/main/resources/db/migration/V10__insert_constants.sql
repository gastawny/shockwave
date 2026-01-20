INSERT INTO value (value_id) VALUES
    (NULL),
    (NULL),
    (NULL),
    (NULL);

INSERT INTO value_num (value_id, value) VALUES
    (85, 3.14159),
    (86, 120),
    (87, 40),
    (88, 7);

INSERT INTO constants(symbol, name, value_id, unit) VALUES
    ('PI', 'Constante Pi', 85, NULL),
    ('FZ_DESABRIGADO', 'Fator Z - Proteção de fragmentos', 86, NULL),
    ('FZ_ABRIGADO', 'Fator Z - Proteção da sobrepressão', 87, NULL),
    ('FZ_RISCO_OPERACIONAL', 'Risco de lesão', 88, NULL);