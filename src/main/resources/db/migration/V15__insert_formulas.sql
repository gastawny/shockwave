# noinspection SpellCheckingInspectionForFile

INSERT INTO formulas(formula_id, name, expression) VALUES
    (1, 'Volume para Caixas', '({largura} * {comprimento} * {altura}) * 0.8'),
    (2, 'Volume para Cones', '(1.0/3 * {PI} * {altura} * ({diametro}/2)^2) * 0.8'),
    (3, 'Volume para Cilindros', '(({diametro}/2)^2 * {comprimento} * {PI}) * 0.8'),
    (4, 'Volume para Esferas', '((4.0/3) * {PI} * ({diametro}/2)^3) * 0.8'),
    (5, 'Volume para Pirâmides', '(1.0/3 * {comprimento} * {altura}) * 0.8'),
    (6, 'Massa de explosivo', '{densidade} * {dep_volume}'),
    (7, 'Equivalência ao TNT', '{m_explosivo} * {efeito_relativo_tnt} / 1000.0'),
    (8, 'Distância operacional', '({FZ_RISCO_OPERACIONAL}*{m_tnt})^(1.0/3)'),
    (9, 'Distância abrigado', '({FZ_ABRIGADO}*{m_tnt})^(1.0/3)'),
    (10, 'Distância desabrigado', '({FZ_DESABRIGADO}*{m_tnt})^(1.0/3)'),
    (11, 'Diâmetro da cratera', '({tab_k}*{m_tnt})^(1.0/3)'),
    (12, 'Profundidade da cratera', '{diametro_cratera}/4.0'),
    (13, 'Distância de segurança', NULL),
    (14, '{m_tnt}*1000 < 50000', '(291.3 + 79.2 * ln(ln({m_tnt}*1000/454))) * 0.3048'),
    (15, '{m_tnt}*1000 > 50000', '(-1133.9 + 389 * ln(ln({m_tnt}*1000/454))) * 0.3048'),
    (16, 'Distância máxima de arremesso', '3 * {d_seg}'),
    (17, 'Distância para o colapso total', '(4.8*({m_tnt})^(1.0/3)) / ((1+(3175.0/{m_tnt})^2)^(1.0/6))'),
    (18, 'Distância para o colapso parcial', '(7.1*({m_tnt})^(1.0/3)) / ((1+(3175.0/{m_tnt})^2)^(1.0/6))'),
    (19, 'Distância para a formação de rachaduras', '(12.4*({m_tnt})^(1.0/3)) / ((1+(3175.0/{m_tnt})^2)^(1.0/6))'),
    (20, 'Distância para a formação de trincas', '21.3 / (12.4*{d_rach})'),
    (21, 'Distância para a ausência de danos', '(42.6*({m_tnt})^(1.0/3)) / ((1+(3175.0/{m_tnt})^2)^(1.0/6))'),
    (22, 'Distância escalar (Z)', '1.0 * {R} / {m_tnt}*(1.0/3)'), -- RESOLVER O R
    (23, 'fator K', '{z} / 0.3966'),
    (24, 'Pressão', NULL),
    (25, '{k} < 7.25', '(exp(6.9137-1.4398*ln(ln({k}))-0.2815*(ln(ln({k})))^2-0.1416*(ln(ln({k})))^3+0.0685*(ln(ln({k})))^4) / 14.5) * 100'),
    (26, '{k} >= 7.25 && {k} <= 60', '(exp(8.8035-3.7*ln(ln({k}))-0.2709*(ln(ln({k})))^2-0.1416*(ln(ln({k})))^3+0.0685*(ln(ln({k})))^4) / 14.5) * 100'),
    (27, '{k} > 60', '(exp(5.4233-1.4066*ln(ln({k}))) / 14.5) * 100'),
    (28, 'Pressão refletida', '(8.0*{pressao}^2+1400*{pressao}) / ({pressao}+700)'),
    (29, 'Distância quebra de vidros menores que 1 m²', '58 * ({m_tnt})^(1.0/3)'),
    (30, 'Distância quebra de vidros entre que 1 m² e 3 m²', '86 * ({m_tnt})^(1.0/3)'),
    (31, 'Distância quebra de vidros maiores que 3 m²', '125 * ({m_tnt})^(1.0/3)');
# 35 36 37 38 39 40
#     ('Tamanho da bola de fogo', '3.3 * ({m_tnt})^(1.0/3)');
# 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 z;

INSERT INTO parameter_dependencies(parameter_id, reference_table, reference_column, reference_id) VALUES
    (5, 'grounds', 'k', NULL);

INSERT INTO formula_parameters(formula_id, parameter_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 3),
    (2, 4),
    (3, 4),
    (3, 2),
    (4, 4),
    (5, 2),
    (5, 3),
    (6, 12),
    (7, 11),
    (11, 5);

INSERT INTO formula_constants(formula_id, constant_id) VALUES
    (2, 1),
    (3, 1),
    (4, 1),
    (8, 4),
    (9, 3),
    (10, 2);

INSERT INTO formula_compositions(main_formula_id, component_formula_id, alias) VALUES
    (7, 6, 'm_explosivo'),
    (8, 7, 'm_tnt'),
    (9, 7, 'm_tnt'),
    (10, 7, 'm_tnt'),
    (11, 7, 'm_tnt'),
    (12, 11, 'diametro_cratera'),
    (14, 7, 'm_tnt'),
    (15, 7, 'm_tnt'),
    (16, 13, 'd_seg'),
    (17, 7, 'm_tnt'),
    (18, 7, 'm_tnt'),
    (19, 7, 'm_tnt'),
    (20, 19, 'd_rach'),
    (21, 7, 'm_tnt'),
    (22, 7, 'm_tnt'),
    (23, 22, 'z'),
    (25, 23, 'k'),
    (26, 23, 'k'),
    (27, 23, 'k'),
    (28, 24, 'pressao'),
    (29, 7, 'm_tnt'),
    (30, 7, 'm_tnt'),
    (31, 7, 'm_tnt');

INSERT INTO formula_dependencies(alias, reference_table) VALUES
    ('dep_volume', 'object_formats');

INSERT INTO formula_dependency_mappings(formula_dependency_id, formula_id, reference_id) VALUES
    (1, 1, 1),
    (1, 2, 3),
    (1, 3, 5),
    (1, 4, 4),
    (1, 5, 2);

INSERT INTO formula_decision(formula_id) VALUES
    (13),
    (24);

INSERT INTO formula_decision_mappings(formula_decision_id, formula_id) VALUES
    (1, 14),
    (1, 15),
    (2, 25),
    (2, 26),
    (2, 27);

INSERT INTO formula_formula_dependencies(formula_dependency_id, formula_id) VALUES
    (1, 6);