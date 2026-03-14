insert into explosives (explosive_id, name)
values  (1, 'TNT'),
        (2, 'AMATOL'),
        (3, 'AMONAL'),
        (4, 'ANFO'),
        (5, 'Azida de Chumbo'),
        (6, 'Composto A3');

INSERT INTO value (value_id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10),
    (11),
    (12),
    (13),
    (14),
    (15),
    (16),
    (17),
    (18),
    (19),
    (20),
    (21),
    (22),
    (23),
    (24),
    (25),
    (26),
    (27),
    (28),
    (29),
    (30),
    (31),
    (32),
    (33),
    (34),
    (35),
    (36),
    (37),
    (38),
    (39),
    (40),
    (41),
    (42),
    (43),
    (44),
    (45),
    (46),
    (47),
    (48),
    (49),
    (50),
    (51),
    (52),
    (53),
    (54),
    (55),
    (56),
    (57),
    (58),
    (59),
    (60),
    (61),
    (62),
    (63),
    (64),
    (65),
    (66),
    (67),
    (68),
    (69),
    (70),
    (71),
    (72),
    (73),
    (74),
    (75),
    (76),
    (77),
    (78),
    (79),
    (80),
    (81),
    (82),
    (83),
    (84);

INSERT INTO value_num (value_id, value) VALUES
    (6, 1),
    (7, 1.64),
    (8, 6900),
    (9, 100),
    (10, 475),
    (11, 730),
    (20, 1.17),
    (21, 1.6),
    (22, 4900),
    (36, 5600),
    (48, 0.82),
    (49, 0.84),
    (50, 2500),
    (62, 0.39),
    (63, 4.71),
    (64, 5180),
    (65, 11),
    (66, 340),
    (68, -55),
    (76, 1.35),
    (77, 1.63),
    (78, 8100);

INSERT INTO value_str (value_id, value) VALUES
    (1, 'TNT'),
    (2, 'Trinitrotolueno; trotil; trilita; triton;'),
    (3, '100% Trinitrotolueno (Ácido Nítrico Tolueno)'),
    (4, 'C7H5(NO2)3'),
    (12, '-74 [Perigoso (Pcp em ambiente fechado)]'),
    (13, 'Excelente'),
    (14, 'Pó ou escamas amarelas'),
    (15, 'AMATOL'),
    (17, 'Nitrato de amônio (80%); TNT (20%)'),
    (18, 'C7H5N3O6'),
    (26, '+11 [Perigoso]'),
    (27, 'Pouca (nitrato de amônio é altamente higroscópico)'),
    (28, 'Semelhante ao TNT'),
    (29, 'AMONAL'),
    (31, 'Nitrato de amônio (22%); TNT (67%); alumínio (11%)'),
    (32, 'C1H10AlN5O8+'),
    (40, 'Pouca (nitrato de amônio é altamente higroscópico)'),
    (41, 'Semelhante ao TNT com cor acinzentada'),
    (43, 'ANFO'),
    (44, 'Ammonium Nitrate / Fuel Oil.'),
    (45, 'Nitrato de amônio (94,3%); óleo combustível (5,7%) (geralmente cetano)'),
    (46, 'NH4NO3 e CH3(CH2)14CH3(C16H34)'),
    (54, NULL),
    (55, NULL),
    (56, 'Grãos esféricos'),
    (57, 'Azida de Chumbo'),
    (58, NULL),
    (59, NULL),
    (60, 'Pb(N3)2'),
    (69, NULL),
    (70, 'Agulhas amarelas'),
    (71, 'Composto A3'),
    (72, NULL),
    (73, 'RDX (91%); cera (9%)'),
    (74, 'C3H6O3(NO2)3 (RDX)'),
    (75, 'Carga de escorva e explosiva'),
    (82, 'Perigoso'),
    (83, 'Boa'),
    (84, 'Cera pastosa');

INSERT INTO value_text (value_id, value) VALUES
    (5, 'Emprego militar generalizado / Carga de destruição e composição de explosivos'),
    (19, 'Carga explosiva'),
    (33, 'Carga explosiva'),
    (47, 'Carga de demolição (corte de rocha, destocamento e valetamento)'),
    (61, 'Detonadores'),
    (79, NULL),
    (80, NULL),
    (81, NULL);

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (1, 6, 1),
    (1, 7, 2),
    (1, 8, 3),
    (1, 9, 4),
    (1, 10, 5),
    (1, 11, 6),
    (1, 12, 7),
    (1, 13, 8),
    (1, 14, 9),
    (1, 15, 10),
    (1, 16, 11),
    (1, 17, 12),
    (1, 18, 13),
    (1, 19, 14),
    (2, 6, 15),
    (2, 7, 16),
    (2, 8, 17),
    (2, 9, 18),
    (2, 10, 19),
    (2, 11, 20),
    (2, 12, 21),
    (2, 13, 22),
    (2, 14, 23),
    (2, 15, 24),
    (2, 16, 25),
    (2, 17, 26),
    (2, 18, 27),
    (2, 19, 28),
    (3, 6, 29),
    (3, 7, 30),
    (3, 8, 31),
    (3, 9, 32),
    (3, 10, 33),
    (3, 11, 34),
    (3, 12, 35),
    (3, 13, 36),
    (3, 14, 37),
    (3, 15, 38),
    (3, 16, 39),
    (3, 17, 40),
    (3, 18, 41),
    (3, 19, 42),
    (4, 6, 43),
    (4, 7, 44),
    (4, 8, 45),
    (4, 9, 46),
    (4, 10, 47),
    (4, 11, 48),
    (4, 12, 49),
    (4, 13, 50),
    (4, 14, 51),
    (4, 15, 52),
    (4, 16, 53),
    (4, 17, 54),
    (4, 18, 55),
    (4, 19, 56),
    (5, 6, 57),
    (5, 7, 58),
    (5, 8, 59),
    (5, 9, 60),
    (5, 10, 61),
    (5, 11, 62),
    (5, 12, 63),
    (5, 13, 64),
    (5, 14, 65),
    (5, 15, 66),
    (5, 16, 67),
    (5, 17, 68),
    (5, 18, 69),
    (5, 19, 70),
    (6, 6, 71),
    (6, 7, 72),
    (6, 8, 73),
    (6, 9, 74),
    (6, 10, 75),
    (6, 11, 76),
    (6, 12, 77),
    (6, 13, 78),
    (6, 14, 79),
    (6, 15, 80),
    (6, 16, 81),
    (6, 17, 82),
    (6, 18, 83),
    (6, 19, 84);


INSERT INTO explosives (explosive_id, name) VALUES
    (7, 'Composto B');


INSERT INTO value (value_id) VALUES
     (85),
     (86),
     (87),
     (88),
     (89),
     (90),
     (91),
     (92),
     (93),
     (94),
     (95),
     (96),
     (97),
     (98);

INSERT INTO value_num (value_id, value) VALUES
    (90, 1.35),
    (91, 1.68),
    (92, 7840),
    (93, NULL),
    (94, NULL),
    (95, 845);

INSERT INTO value_str (value_id, value) VALUES
    (85, 'Composto B'),
    (86, NULL),
    (87, 'RDX (60%); TNT (39%); cera de parafina (1%)'),
    (88, 'C3H6O3(NO2)3 (RDX)'),
    (96, '-43 [Perigoso]'),
    (97, 'Excelente'),
    (98, 'Semelhante ao TNT');

INSERT INTO value_text (value_id, value) VALUES
    (89, 'Carga explosiva');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (7, 6, 85),
    (7, 7, 86),
    (7, 8, 87),
    (7, 9, 88),
    (7, 10, 89),
    (7, 11, 90),
    (7, 12, 91),
    (7, 13, 92),
    (7, 14, 93),
    (7, 15, 94),
    (7, 16, 95),
    (7, 17, 96),
    (7, 18, 97),
    (7, 19, 98);


INSERT INTO explosives (explosive_id, name) VALUES
    (8, 'Composto C3');

INSERT INTO value (value_id) VALUES
    (99),  -- Ficha técnica
    (100), -- Nomes
    (101), -- Composição
    (102), -- Fórmula química
    (103), -- Utilização
    (104), -- Efeito relativo TNT
    (105), -- Densidade
    (106), -- Velocidade de detonação
    (107), -- Sensibilidade ao choque
    (108), -- Sensibilidade ao calor
    (109), -- Produção de gases
    (110), -- Produção de gases tóxicos
    (111), -- Resistência à água
    (112); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (104, 1.34),
    (105, 1.62),
    (106, 7625),
    (107, NULL),
    (108, NULL),
    (109, NULL),
    (110, NULL);

INSERT INTO value_str (value_id, value) VALUES
    (99, 'Composto C3'),
    (100, 'C3'),
    (101, 'RDX (78%); Plastificante (22%)'),
    (102, NULL),
    (111, 'Boa'),
    (112, 'Sólido branco semelhante a plasticina');

INSERT INTO value_text (value_id, value) VALUES
    (103, 'Carga de destruição (corte e ruptura');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (8, 6, 99),   -- Ficha técnica
    (8, 7, 100),  -- Nomes
    (8, 8, 101),  -- Composição
    (8, 9, 102),  -- Fórmula química
    (8, 10, 103), -- Utilização
    (8, 11, 104), -- Efeito relativo TNT
    (8, 12, 105), -- Densidade
    (8, 13, 106), -- Velocidade de detonação
    (8, 14, 107), -- Sensibilidade ao choque
    (8, 15, 108), -- Sensibilidade ao calor
    (8, 16, 109), -- Produção de gases
    (8, 17, 110), -- Produção de gases tóxicos
    (8, 18, 111), -- Resistência à água
    (8, 19, 112); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (9, 'Composto C4');

INSERT INTO value (value_id) VALUES
    (113), -- Ficha técnica
    (114), -- Nomes
    (115), -- Composição
    (116), -- Fórmula química
    (117), -- Utilização
    (118), -- Efeito relativo TNT
    (119), -- Densidade
    (120), -- Velocidade de detonação
    (121), -- Sensibilidade ao choque
    (122), -- Sensibilidade ao calor
    (123), -- Produção de gases
    (124), -- Produção de gases tóxicos
    (125), -- Resistência à água
    (126); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (118, 1.34),
    (119, 1.72),
    (120, 8000),
    (121, NULL),
    (122, NULL),
    (123, NULL),
    (124, NULL);

INSERT INTO value_str (value_id, value) VALUES
    (113, 'Composto C4'),
    (114, 'C4'),
    (115, 'RDX (91%); polibutileno (plastificante) (9%)'),
    (116, NULL),
    (125, 'Excelente'),
    (126, 'Sólido branco semelhante a plasticina');

INSERT INTO value_text (value_id, value) VALUES
    (117, 'Carga de destruição (corte e ruptura)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (9, 6, 113),  -- Ficha técnica
    (9, 7, 114),  -- Nomes
    (9, 8, 115),  -- Composição
    (9, 9, 116),  -- Fórmula química
    (9, 10, 117), -- Utilização
    (9, 11, 118), -- Efeito relativo TNT
    (9, 12, 119), -- Densidade
    (9, 13, 120), -- Velocidade de detonação
    (9, 14, 121), -- Sensibilidade ao choque
    (9, 15, 122), -- Sensibilidade ao calor
    (9, 16, 123), -- Produção de gases
    (9, 17, 124), -- Produção de gases tóxicos
    (9, 18, 125), -- Resistência à água
    (9, 19, 126); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (10, 'Dinamite Militar');

INSERT INTO value (value_id) VALUES
    (127), -- Ficha técnica
    (128), -- Nomes
    (129), -- Composição
    (130), -- Fórmula química
    (131), -- Utilização
    (132), -- Efeito relativo TNT
    (133), -- Densidade
    (134), -- Velocidade de detonação
    (135), -- Sensibilidade ao choque
    (136), -- Sensibilidade ao calor
    (137), -- Produção de gases
    (138), -- Produção de gases tóxicos
    (139), -- Resistência à água
    (140); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (132, 0.92),
    (133, NULL),
    (134, 6100),
    (135, NULL),
    (136, NULL),
    (137, NULL);

INSERT INTO value_str (value_id, value) VALUES
    (127, 'Dinamite Militar'),
    (128, 'Dinamite M1, M2 e M3'),
    (129, 'RDX (75%); TNT (15%); desensibilizante e plastificante (10%)'),
    (130, NULL),
    (138, 'Perigoso'),
    (139, 'Regular'),
    (140, 'Amarelo Claro');

INSERT INTO value_text (value_id, value) VALUES
    (131, 'Carga de demolição (corte de rocha, destocamento e valetamento)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (10, 6, 127),  -- Ficha técnica
    (10, 7, 128),  -- Nomes
    (10, 8, 129),  -- Composição
    (10, 9, 130),  -- Fórmula química
    (10, 10, 131), -- Utilização
    (10, 11, 132), -- Efeito relativo TNT
    (10, 12, 133), -- Densidade
    (10, 13, 134), -- Velocidade de detonação
    (10, 14, 135), -- Sensibilidade ao choque
    (10, 15, 136), -- Sensibilidade ao calor
    (10, 16, 137), -- Produção de gases
    (10, 17, 138), -- Produção de gases tóxicos
    (10, 18, 139), -- Resistência à água
    (10, 19, 140); -- Characteristics físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (11, 'HMX');

INSERT INTO value (value_id) VALUES
    (141), -- Ficha técnica
    (142), -- Nomes
    (143), -- Composição
    (144), -- Fórmula química
    (145), -- Utilização
    (146), -- Efeito relativo TNT
    (147), -- Densidade
    (148), -- Velocidade de detonação
    (149), -- Sensibilidade ao choque
    (150), -- Sensibilidade ao calor
    (151), -- Produção de gases
    (152), -- Produção de gases tóxicos (Balanço de oxigênio)
    (153), -- Resistência à água
    (154); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (146, 1.7),   -- Efeito TNT
    (147, 1.89),  -- Densidade
    (148, 9100),  -- Velocidade
    (149, 32),    -- Choque
    (150, 287),   -- Calor
    (151, NULL),  -- Produção de gases (Não disponível)
    (152, -21.6); -- Balanço de oxigênio %

INSERT INTO value_str (value_id, value) VALUES
    (141, 'HMX'),
    (142, 'Octogênio'),
    (143, 'Ciclotetrametileno Tetranitroamina'),
    (144, 'C4H8N8O8'),
    (153, NULL), -- Resistência à água
    (154, 'Cristais brancos');

INSERT INTO value_text (value_id, value) VALUES
    (145, 'Carga de destruição (corte e ruptura)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (11, 6, 141),  -- Ficha técnica
    (11, 7, 142),  -- Nomes
    (11, 8, 143),  -- Composição
    (11, 9, 144),  -- Fórmula química
    (11, 10, 145), -- Utilização
    (11, 11, 146), -- Efeito relativo TNT
    (11, 12, 147), -- Densidade
    (11, 13, 148), -- Velocidade de detonação
    (11, 14, 149), -- Sensibilidade ao choque
    (11, 15, 150), -- Sensibilidade ao calor
    (11, 16, 151), -- Produção de gases
    (11, 17, 152), -- Produção de gases tóxicos
    (11, 18, 153), -- Resistência à água
    (11, 19, 154); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (12, 'Nitrato de amônio');

INSERT INTO value (value_id) VALUES
    (155), -- Ficha técnica
    (156), -- Nomes
    (157), -- Composição
    (158), -- Fórmula química
    (159), -- Utilização
    (160), -- Efeito relativo TNT
    (161), -- Densidade
    (162), -- Velocidade de detonação
    (163), -- Sensibilidade ao choque
    (164), -- Sensibilidade ao calor
    (165), -- Produção de gases
    (166), -- Produção de gases tóxicos (Balanço de oxigênio)
    (167), -- Resistência à água
    (168); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (160, 0.42),  -- Efeito TNT
    (161, 1.72),  -- Densidade
    (162, 2800),  -- Velocidade
    (163, 200),   -- Choque
    (164, NULL),  -- Calor (Não disponível)
    (165, 980);   -- Produção de gases

INSERT INTO value_str (value_id, value) VALUES
    (155, 'Nitrato de amônio'),
    (156, 'NA'),
    (157, 'Ácido nítrico amoníaco'),
    (158, 'NH4NO3'),
    (166, '+20 [Perigoso]'), -- Contém texto, inserido como string
    (167, 'Nenhuma'),
    (168, 'Grãos brancos');

INSERT INTO value_text (value_id, value) VALUES
    (159, 'Abertura de crateras');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (12, 6, 155),  -- Ficha técnica
    (12, 7, 156),  -- Nomes
    (12, 8, 157),  -- Composição
    (12, 9, 158),  -- Fórmula química
    (12, 10, 159), -- Utilização
    (12, 11, 160), -- Efeito relativo TNT
    (12, 12, 161), -- Densidade
    (12, 13, 162), -- Velocidade de detonação
    (12, 14, 163), -- Sensibilidade ao choque
    (12, 15, 164), -- Sensibilidade ao calor
    (12, 16, 165), -- Produção de gases
    (12, 17, 166), -- Produção de gases tóxicos
    (12, 18, 167), -- Resistência à água
    (12, 19, 168); -- Características físicas

INSERT INTO explosives (explosive_id, name) VALUES
    (13, 'Nitrocelulose');

INSERT INTO value (value_id) VALUES
    (169), -- Ficha técnica
    (170), -- Nomes
    (171), -- Composição
    (172), -- Fórmula química
    (173), -- Utilização
    (174), -- Efeito relativo TNT
    (175), -- Densidade
    (176), -- Velocidade de detonação
    (177), -- Sensibilidade ao choque
    (178), -- Sensibilidade ao calor
    (179), -- Produção de gases
    (180), -- Produção de gases tóxicos
    (181), -- Resistência à água
    (182); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (174, 1.25),  -- Efeito TNT
    (175, 1.66),  -- Densidade
    (176, 7300),  -- Velocidade
    (177, 9),     -- Choque
    (178, 175),   -- Calor
    (179, NULL),  -- Produção de gases (Não disponível)
    (180, NULL);  -- Gases tóxicos (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (169, 'Nitrocelulose'),
    (170, 'NC'),
    (171, 'Ácido Nítrico Celulose'),
    (172, 'C6H5O3(NO2)2'),
    (181, NULL), -- Resistência à água
    (182, 'Fibras brancas');

INSERT INTO value_text (value_id, value) VALUES
    (173, 'Base para pólvora e dinamite');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (13, 6, 169),  -- Ficha técnica
    (13, 7, 170),  -- Nomes
    (13, 8, 171),  -- Composição
    (13, 9, 172),  -- Fórmula química
    (13, 10, 173), -- Utilização
    (13, 11, 174), -- Efeito relativo TNT
    (13, 12, 175), -- Densidade
    (13, 13, 176), -- Velocidade de detonação
    (13, 14, 177), -- Sensibilidade ao choque
    (13, 15, 178), -- Sensibilidade ao calor
    (13, 16, 179), -- Produção de gases
    (13, 17, 180), -- Produção de gases tóxicos
    (13, 18, 181), -- Resistência à água
    (13, 19, 182); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (14, 'Nitroglicerina');

INSERT INTO value (value_id) VALUES
    (183), -- Ficha técnica
    (184), -- Nomes
    (185), -- Composição
    (186), -- Fórmula química
    (187), -- Utilização
    (188), -- Efeito relativo TNT
    (189), -- Densidade
    (190), -- Velocidade de detonação
    (191), -- Sensibilidade ao choque
    (192), -- Sensibilidade ao calor
    (193), -- Produção de gases
    (194), -- Produção de gases tóxicos (Balanço de oxigênio)
    (195), -- Resistência à água
    (196); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (188, 1.5),   -- Efeito TNT
    (189, 1.6),   -- Densidade
    (190, 7700),  -- Velocidade
    (191, 4),     -- Choque
    (192, 205),   -- Calor
    (193, 715),   -- Produção de gases
    (194, 3.5);   -- Gases tóxicos/Balanço O2

INSERT INTO value_str (value_id, value) VALUES
    (183, 'Nitroglicerina'),
    (184, 'Trinitroglicerina; Nitroglicerol trinitrato de glicerol'),
    (185, 'Ácido Nítrico e glicerina'),
    (186, 'C3H5(NO3)3'),
    (195, 'Boa'), -- Resistência à água
    (196, 'Líquido incolor, frequentemente levemente amarelado devido a impurezas');

INSERT INTO value_text (value_id, value) VALUES
    (187, 'Usado como base para outros explosivos');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (14, 6, 183),  -- Ficha técnica
    (14, 7, 184),  -- Nomes
    (14, 8, 185),  -- Composição
    (14, 9, 186),  -- Fórmula química
    (14, 10, 187), -- Utilização
    (14, 11, 188), -- Efeito relativo TNT
    (14, 12, 189), -- Densidade
    (14, 13, 190), -- Velocidade de detonação
    (14, 14, 191), -- Sensibilidade ao choque
    (14, 15, 192), -- Sensibilidade ao calor
    (14, 16, 193), -- Produção de gases
    (14, 17, 194), -- Produção de gases tóxicos
    (14, 18, 195), -- Resistência à água
    (14, 19, 196); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (15, 'Pentolite 50/50');

INSERT INTO value (value_id) VALUES
    (197), -- Ficha técnica
    (198), -- Nomes
    (199), -- Composição
    (200), -- Fórmula química
    (201), -- Utilização
    (202), -- Efeito relativo TNT
    (203), -- Densidade
    (204), -- Velocidade de detonação
    (205), -- Sensibilidade ao choque
    (206), -- Sensibilidade ao calor
    (207), -- Produção de gases
    (208), -- Produção de gases tóxicos (Balanço de oxigênio)
    (209), -- Resistência à água
    (210); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (202, 1.38),  -- Efeito TNT
    (203, 1.62),  -- Densidade
    (204, 7400),  -- Velocidade
    (205, NULL),  -- Choque (Não disponível)
    (206, NULL),  -- Calor (Não disponível)
    (207, NULL);  -- Produção de gases (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (197, 'Pentolite 50/50'),
    (198, 'Não disponível'),
    (199, 'PETN (50%) + TNT (50%)'),
    (200, NULL), -- Fórmula química
    (208, '- 42 [Perigoso]'), -- Inserido como texto pois contém "[Perigoso]"
    (209, 'Excelente'),
    (210, 'Semelhante ao TNT');

INSERT INTO value_text (value_id, value) VALUES
    (201, 'Carga explosiva e reforçador');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (15, 6, 197),  -- Ficha técnica
    (15, 7, 198),  -- Nomes
    (15, 8, 199),  -- Composição
    (15, 9, 200),  -- Fórmula química
    (15, 10, 201), -- Utilização
    (15, 11, 202), -- Efeito relativo TNT
    (15, 12, 203), -- Densidade
    (15, 13, 204), -- Velocidade de detonação
    (15, 14, 205), -- Sensibilidade ao choque
    (15, 15, 206), -- Sensibilidade ao calor
    (15, 16, 207), -- Produção de gases
    (15, 17, 208), -- Produção de gases tóxicos
    (15, 18, 209), -- Resistência à água
    (15, 19, 210); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (16, 'PETN');

INSERT INTO value (value_id) VALUES
    (211), -- Ficha técnica
    (212), -- Nomes
    (213), -- Composição
    (214), -- Fórmula química
    (215), -- Utilização
    (216), -- Efeito relativo TNT
    (217), -- Densidade
    (218), -- Velocidade de detonação
    (219), -- Sensibilidade ao choque
    (220), -- Sensibilidade ao calor
    (221), -- Produção de gases
    (222), -- Produção de gases tóxicos (Balanço de oxigênio)
    (223), -- Resistência à água
    (224); -- Características físicas

-- Inserção dos valores numéricos
INSERT INTO value_num (value_id, value) VALUES
    (216, 1.66),  -- Efeito TNT
    (217, 1.77),  -- Densidade
    (218, 8300),  -- Velocidade
    (219, 28),    -- Choque
    (220, 240),   -- Calor
    (221, 790);   -- Produção de gases

INSERT INTO value_str (value_id, value) VALUES
    (211, 'PETN'),
    (212, 'Nitropenta, NP, Pentrita, Tetranitrato de Pentaeritrita'),
    (213, 'Ácido Nítrico + Pentaeritrita'),
    (214, 'C5H5O3(NO3)4'),
    (222, '- 10 [Perigoso]'), -- Inserido como texto pois contém "[Perigoso]"
    (223, 'Excelente'),
    (224, 'Cristais brancos');

INSERT INTO value_text (value_id, value) VALUES
    (215, 'Cordel detonante, espoletas, composição de explosivos');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (16, 6, 211),  -- Ficha técnica
    (16, 7, 212),  -- Nomes
    (16, 8, 213),  -- Composição
    (16, 9, 214),  -- Fórmula química
    (16, 10, 215), -- Utilização
    (16, 11, 216), -- Efeito relativo TNT
    (16, 12, 217), -- Densidade
    (16, 13, 218), -- Velocidade de detonação
    (16, 14, 219), -- Sensibilidade ao choque
    (16, 15, 220), -- Sensibilidade ao calor
    (16, 16, 221), -- Produção de gases
    (16, 17, 222), -- Produção de gases tóxicos
    (16, 18, 223), -- Resistência à água
    (16, 19, 224); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (17, 'Plastex');

INSERT INTO value (value_id) VALUES
    (225), -- Ficha técnica
    (226), -- Nomes
    (227), -- Composição
    (228), -- Fórmula química
    (229), -- Utilização
    (230), -- Efeito relativo TNT
    (231), -- Densidade
    (232), -- Velocidade de detonação
    (233), -- Sensibilidade ao choque
    (234), -- Sensibilidade ao calor
    (235), -- Produção de gases
    (236), -- Produção de gases tóxicos (Balanço de oxigênio)
    (237), -- Resistência à água
    (238); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (230, 1.12),  -- Efeito TNT
    (231, NULL),  -- Densidade (Não disponível)
    (232, 7200),  -- Velocidade
    (233, NULL),  -- Choque (Não disponível)
    (234, NULL),  -- Calor (Não disponível)
    (235, NULL),  -- Produção de gases (Não disponível)
    (236, NULL);  -- Gases tóxicos (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (225, 'Plastex'),
    (226, 'Não disponível'),
    (227, 'PETN (71%); TNT (5%); Plastificante (24%)'),
    (228, NULL), -- Fórmula química
    (237, 'Boa'),
    (238, NULL); -- Características físicas

INSERT INTO value_text (value_id, value) VALUES
    (229, 'Carga de destruição (corte e ruptura)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (17, 6, 225),  -- Ficha técnica
    (17, 7, 226),  -- Nomes
    (17, 8, 227),  -- Composição
    (17, 9, 228),  -- Fórmula química
    (17, 10, 229), -- Utilização
    (17, 11, 230), -- Efeito relativo TNT
    (17, 12, 231), -- Densidade
    (17, 13, 232), -- Velocidade de detonação
    (17, 14, 233), -- Sensibilidade ao choque
    (17, 15, 234), -- Sensibilidade ao calor
    (17, 16, 235), -- Produção de gases
    (17, 17, 236), -- Produção de gases tóxicos
    (17, 18, 237), -- Resistência à água
    (17, 19, 238); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (18, 'Pólvora negra');

INSERT INTO value (value_id) VALUES
    (239), -- Ficha técnica
    (240), -- Nomes
    (241), -- Composição
    (242), -- Fórmula química
    (243), -- Utilização
    (244), -- Efeito relativo TNT
    (245), -- Densidade
    (246), -- Velocidade de detonação
    (247), -- Sensibilidade ao choque
    (248), -- Sensibilidade ao calor
    (249), -- Produção de gases
    (250), -- Produção de gases tóxicos (Balanço de oxigênio)
    (251), -- Resistência à água
    (252); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (244, 0.55),  -- Efeito TNT
    (245, NULL),  -- Densidade (Não disponível)
    (246, 400),   -- Velocidade
    (247, NULL),  -- Choque (Não disponível)
    (248, NULL),  -- Calor (Não disponível)
    (249, NULL);  -- Produção de gases (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (239, 'Pólvora negra'),
    (240, NULL),
    (241, 'Carvão vegetal (15%); Nitrato de potássio (75%); Enxofre (10%)'),
    (242, NULL), -- Fórmula química
    (250, 'Perigoso'),       -- Produção de gases tóxicos (texto)
    (251, 'Pouca'),
    (252, 'Granulado acinzentado de brilho fosco');

INSERT INTO value_text (value_id, value) VALUES
    (243, 'Pirotécnicos, estopim e abertura de crateras');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (18, 6, 239),  -- Ficha técnica
    (18, 7, 240),  -- Nomes
    (18, 8, 241),  -- Composição
    (18, 9, 242),  -- Fórmula química
    (18, 10, 243), -- Utilização
    (18, 11, 244), -- Efeito relativo TNT
    (18, 12, 245), -- Densidade
    (18, 13, 246), -- Velocidade de detonação
    (18, 14, 247), -- Sensibilidade ao choque
    (18, 15, 248), -- Sensibilidade ao calor
    (18, 16, 249), -- Produção de gases
    (18, 17, 250), -- Produção de gases tóxicos
    (18, 18, 251), -- Resistência à água
    (18, 19, 252); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (19, 'RDX');

INSERT INTO value (value_id) VALUES
    (253), -- Ficha técnica
    (254), -- Nomes
    (255), -- Composição
    (256), -- Fórmula química
    (257), -- Utilização
    (258), -- Efeito relativo TNT
    (259), -- Densidade
    (260), -- Velocidade de detonação
    (261), -- Sensibilidade ao choque
    (262), -- Sensibilidade ao calor
    (263), -- Produção de gases
    (264), -- Produção de gases tóxicos (Balanço de oxigênio)
    (265), -- Resistência à água
    (266); -- Características físicas

-- Inserção dos valores numéricos
INSERT INTO value_num (value_id, value) VALUES
    (258, 1.6),   -- Efeito TNT
    (259, 1.82),  -- Densidade
    (260, 8700),  -- Velocidade
    (261, 29),    -- Choque
    (262, 270),   -- Calor
    (263, 908);   -- Produção de gases

INSERT INTO value_str (value_id, value) VALUES
    (253, 'RDX'),
    (254, 'Hexogeno, H, Ciclonita Ciclotrimetilenotrinitramina'),
    (255, 'Ácido Nítrico + Urotropina'),
    (256, 'C3H6O3(NO2)3'),
    (264, '-21 [Perigoso]'), -- Inserido como texto pois contém "[Perigoso]"
    (265, 'Excelente'),
    (266, 'Cristais brancos');

INSERT INTO value_text (value_id, value) VALUES
    (257, 'Carga de destruição (corte e ruptura)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (19, 6, 253),  -- Ficha técnica
    (19, 7, 254),  -- Nomes
    (19, 8, 255),  -- Composição
    (19, 9, 256),  -- Fórmula química
    (19, 10, 257), -- Utilização
    (19, 11, 258), -- Efeito relativo TNT
    (19, 12, 259), -- Densidade
    (19, 13, 260), -- Velocidade de detonação
    (19, 14, 261), -- Sensibilidade ao choque
    (19, 15, 262), -- Sensibilidade ao calor
    (19, 16, 263), -- Produção de gases
    (19, 17, 264), -- Produção de gases tóxicos
    (19, 18, 265), -- Resistência à água
    (19, 19, 266); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (20, 'Semtex H');

INSERT INTO value (value_id) VALUES
    (267), -- Ficha técnica
    (268), -- Nomes
    (269), -- Composição
    (270), -- Fórmula química
    (271), -- Utilização
    (272), -- Efeito relativo TNT
    (273), -- Densidade
    (274), -- Velocidade de detonação
    (275), -- Sensibilidade ao choque
    (276), -- Sensibilidade ao calor
    (277), -- Produção de gases
    (278), -- Produção de gases tóxicos (Balanço de oxigênio)
    (279), -- Resistência à água
    (280); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (272, 1.66),  -- Efeito TNT
    (273, 1.77),  -- Densidade
    (274, 8420),  -- Velocidade
    (275, NULL),  -- Choque (Não disponível)
    (276, NULL),  -- Calor (Não disponível)
    (277, NULL),  -- Produção de gases (Não disponível)
    (278, NULL);  -- Gases tóxicos (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (267, 'Semtex H'),
    (268, NULL),
    (269, 'PETN (25%); RDX (65,5%); Ligante (2,5%); Plastificante (11,6%); Corante (0,002%)'),
    (270, NULL), -- Fórmula química
    (279, NULL), -- Resistência à água
    (280, NULL); -- Características físicas

INSERT INTO value_text (value_id, value) VALUES
    (271, 'Carga explosiva');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (20, 6, 267),  -- Ficha técnica
    (20, 7, 268),  -- Nomes
    (20, 8, 269),  -- Composição
    (20, 9, 270),  -- Fórmula química
    (20, 10, 271), -- Utilização
    (20, 11, 272), -- Efeito relativo TNT
    (20, 12, 273), -- Densidade
    (20, 13, 274), -- Velocidade de detonação
    (20, 14, 275), -- Sensibilidade ao choque
    (20, 15, 276), -- Sensibilidade ao calor
    (20, 16, 277), -- Produção de gases
    (20, 17, 278), -- Produção de gases tóxicos
    (20, 18, 279), -- Resistência à água
    (20, 19, 280); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (21, 'TETRIL');

INSERT INTO value (value_id) VALUES
    (281), -- Ficha técnica
    (282), -- Nomes
    (283), -- Composição
    (284), -- Fórmula química
    (285), -- Utilização
    (286), -- Efeito relativo TNT
    (287), -- Densidade
    (288), -- Velocidade de detonação
    (289), -- Sensibilidade ao choque
    (290), -- Sensibilidade ao calor
    (291), -- Produção de gases
    (292), -- Produção de gases tóxicos (Balanço de oxigênio)
    (293), -- Resistência à água
    (294); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (286, 1.25),  -- Efeito TNT
    (287, 1.71),  -- Densidade
    (288, 7850),  -- Velocidade
    (289, 30),    -- Choque
    (290, 225),   -- Calor
    (291, 760),   -- Produção de gases
    (292, NULL);  -- Gases tóxicos (Texto 'Perigoso')

INSERT INTO value_str (value_id, value) VALUES
    (281, 'TETRIL'),
    (282, 'Tetrilo; Trinitrofenil metilnitroamina; Tetralitra; Nitromina'),
    (283, 'Ácido Nítrico + Dimetilamina'),
    (284, 'C7H5N5O5'),
    (292, 'Perigoso'),       -- Inserido como texto
    (293, 'Excelente'),
    (294, 'Pó amarelo');

INSERT INTO value_text (value_id, value) VALUES
    (285, 'Carga de escorva e composição de explosivos');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (21, 6, 281),  -- Ficha técnica
    (21, 7, 282),  -- Nomes
    (21, 8, 283),  -- Composição
    (21, 9, 284),  -- Fórmula química
    (21, 10, 285), -- Utilização
    (21, 11, 286), -- Efeito relativo TNT
    (21, 12, 287), -- Densidade
    (21, 13, 288), -- Velocidade de detonação
    (21, 14, 289), -- Sensibilidade ao choque
    (21, 15, 290), -- Sensibilidade ao calor
    (21, 16, 291), -- Produção de gases
    (21, 17, 292), -- Produção de gases tóxicos
    (21, 18, 293), -- Resistência à água
    (21, 19, 294); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (22, 'Tetritol 75/25');

INSERT INTO value (value_id) VALUES
    (295), -- Ficha técnica
    (296), -- Nomes
    (297), -- Composição
    (298), -- Fórmula química
    (299), -- Utilização
    (300), -- Efeito relativo TNT
    (301), -- Densidade
    (302), -- Velocidade de detonação
    (303), -- Sensibilidade ao choque
    (304), -- Sensibilidade ao calor
    (305), -- Produção de gases
    (306), -- Produção de gases tóxicos (Balanço de oxigênio)
    (307), -- Resistência à água
    (308); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (300, 1.2),   -- Efeito TNT
    (301, 1.61),  -- Densidade
    (302, 7000),  -- Velocidade
    (303, NULL),  -- Choque (Não disponível)
    (304, NULL),  -- Calor (Não disponível)
    (305, NULL),  -- Produção de gases (Não disponível)
    (306, NULL);  -- Gases tóxicos (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (295, 'Tetritol 75/25'),
    (296, 'Tetril (75%); TNT (25%)'),
    (297, NULL), -- Composição
    (298, NULL), -- Fórmula química
    (307, 'Excelente'),
    (308, 'Semelhante ao TNT');

INSERT INTO value_text (value_id, value) VALUES
    (299, 'Carga de destruição (corte e abertura de brechas)');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (22, 6, 295),  -- Ficha técnica
    (22, 7, 296),  -- Nomes
    (22, 8, 297),  -- Composição
    (22, 9, 298),  -- Fórmula química
    (22, 10, 299), -- Utilização
    (22, 11, 300), -- Efeito relativo TNT
    (22, 12, 301), -- Densidade
    (22, 13, 302), -- Velocidade de detonação
    (22, 14, 303), -- Sensibilidade ao choque
    (22, 15, 304), -- Sensibilidade ao calor
    (22, 16, 305), -- Produção de gases
    (22, 17, 306), -- Produção de gases tóxicos
    (22, 18, 307), -- Resistência à água
    (22, 19, 308); -- Características físicas


INSERT INTO explosives (explosive_id, name) VALUES
    (23, 'Torpex');

INSERT INTO value (value_id) VALUES
    (309), -- Ficha técnica
    (310), -- Nomes
    (311), -- Composição
    (312), -- Fórmula química
    (313), -- Utilização
    (314), -- Efeito relativo TNT
    (315), -- Densidade
    (316), -- Velocidade de detonação
    (317), -- Sensibilidade ao choque
    (318), -- Sensibilidade ao calor
    (319), -- Produção de gases
    (320), -- Produção de gases tóxicos (Balanço de oxigênio)
    (321), -- Resistência à água
    (322); -- Características físicas

INSERT INTO value_num (value_id, value) VALUES
    (314, NULL),  -- Efeito TNT (Não disponível)
    (315, NULL),  -- Densidade (Não disponível)
    (317, NULL),  -- Choque (Não disponível)
    (318, NULL),  -- Calor (Não disponível)
    (319, NULL),  -- Produção de gases (Não disponível)
    (320, NULL);  -- Gases tóxicos (Não disponível)

INSERT INTO value_str (value_id, value) VALUES
    (309, 'Torpex'),
    (310, NULL),
    (311, 'RDX (42%); TNT (40%); Alumínio (18%)'),
    (312, NULL), -- Fórmula química
    (316, '7400 a 7600'),    -- Velocidade (Intervalo inserido como texto)
    (321, NULL), -- Resistência à água
    (322, NULL); -- Características físicas

INSERT INTO value_text (value_id, value) VALUES
    (313, 'Cargas subaquáticas');

INSERT INTO explosive_parameters (explosive_id, parameter_id, value_id) VALUES
    (23, 6, 309),  -- Ficha técnica
    (23, 7, 310),  -- Nomes
    (23, 8, 311),  -- Composição
    (23, 9, 312),  -- Fórmula química
    (23, 10, 313), -- Utilização
    (23, 11, 314), -- Efeito relativo TNT
    (23, 12, 315), -- Densidade
    (23, 13, 316), -- Velocidade de detonação
    (23, 14, 317), -- Sensibilidade ao choque
    (23, 15, 318), -- Sensibilidade ao calor
    (23, 16, 319), -- Produção de gases
    (23, 17, 320), -- Produção de gases tóxicos
    (23, 18, 321), -- Resistência à água
    (23, 19, 322); -- Características físicas


-- Poder

INSERT INTO value (value_id) VALUES
    (323),
    (324),
    (325),
    (326),
    (327),
    (328),
    (329),
    (330),
    (331),
    (332),
    (333),
    (334),
    (335),
    (336),
    (337),
    (338),
    (339),
    (340),
    (341),
    (342),
    (343),
    (344),
    (345);

INSERT INTO value_num (value_id, value) VALUES
    (323, 1.0),
    (324, 0.95),
    (325, NULL),
    (326, 0.82),
    (327, 0.39),
    (328, NULL),
    (329, 1.11),
    (330, NULL),
    (331, 1.37),
    (332, NULL),
    (333, 1.5),
    (334, 0.56),
    (335, 1.25),
    (336, 1.4),
    (337, 1.38),
    (338, 1.27),
    (339, NULL),
    (340, 0.5),
    (341, 1.19),
    (342, NULL),
    (343, 1.07),
    (344, NULL),
    (345, NULL);

INSERT INTO explosive_parameters(explosive_id, parameter_id, value_id) VALUES
    (1, 20, 323),
    (2, 20, 324),
    (3, 20, 325),
    (4, 20, 326),
    (5, 20, 327),
    (6, 20, 328),
    (7, 20, 329),
    (8, 20, 330),
    (9, 20, 331),
    (10, 20, 332),
    (11, 20, 333),
    (12, 20, 334),
    (13, 20, 335),
    (14, 20, 336),
    (15, 20, 337),
    (16, 20, 338),
    (17, 20, 339),
    (18, 20, 340),
    (19, 20, 341),
    (20, 20, 342),
    (21, 20, 343),
    (22, 20, 344),
    (23, 20, 345);