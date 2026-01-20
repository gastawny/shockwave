# noinspection SpellCheckingInspectionForFile

INSERT INTO parameters(parameter_id, name, symbol, unit, value_type) VALUES
    (1, 'Largura', 'largura', 'cm', 'NUMBER'),
    (2, 'Comprimento', 'comprimento', 'cm', 'NUMBER'),
    (3, 'Altura', 'altura', 'cm', 'NUMBER'),
    (4, 'Diâmetro', 'diametro', 'cm', 'NUMBER'),
    (5, 'K solo', 'tab_k', NULL, NULL),
    (6, 'Ficha técnica', 'ficha_tecnica', NULL, 'STRING'),
    (7, 'Nomes', 'nomes', NULL, 'STRING'),
    (8, 'Composição', 'composicao', NULL, 'STRING'),
    (9, 'Fórmula química', 'formula_quimica', NULL, 'STRING'),
    (10, 'Utilização', 'utilizacao', NULL, 'TEXT'),
    (11, 'Efeito Relativo TNT', 'efeito_relativo_tnt', NULL, 'NUMBER'),
    (12, 'Densidade', 'densidade', 'g/cm³', 'NUMBER'),
    (13, 'Velocidade de detonação', 'velocidade_detonação', 'm/s', 'NUMBER'),
    (14, 'Sensibilidade ao choque', 'sensibilidade_choque', 'cm', 'NUMBER'),
    (15, 'Sensibilidade ao calor', 'sensibilidade_calor', '°C', 'NUMBER'),
    (16, 'Produção de gases', 'producao_gases', 'L/Kg', 'NUMBER'),
    (17, 'Produção de gases tóxicos (Balanço de oxigênio em %)', 'producao_gases_toxicos', NULL, NULL),
    (18, 'Resistência a água', 'resistencia_agua', NULL, NULL),
    (19, 'Características físicas', 'caracteristicas_fisicas', NULL, NULL);