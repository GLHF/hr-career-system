INSERT INTO employee(first_name, last_name) VALUES ('Timm', 'Galiakberov');
INSERT INTO employee(first_name, last_name) VALUES ('Ahmed', 'Galeev');
INSERT INTO employee(first_name, last_name) VALUES ('Boris', 'Kremlev');

INSERT INTO position(title) VALUES ('Java разработчик');
INSERT INTO position(title) VALUES ('Менеджер');
INSERT INTO position(title) VALUES ('Тестировщик');

INSERT INTO skill(title) VALUES ('Java');
INSERT INTO skill(title) VALUES ('Менеджемент');
INSERT INTO skill(title) VALUES ('Тестиование веб приложений');
INSERT INTO skill(title) VALUES ('Девопс');
INSERT INTO skill(title) VALUES ('Английский');

-- скилы первого тестового сотрудника
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (1, 1, 5);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (3, 1, 3);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (4, 1, 3);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (5, 1, 4);
-- скилы второго тестового сотрудника
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (2, 2, 5);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (5, 2, 3);
-- скилы третьего тестового сотрудника
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (1, 3, 2);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (3, 3, 5);
INSERT INTO employee_skill(skill_id, employee_id, scale) VALUES (5, 3, 2);


-- требования первой должности
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (1, 1, 4);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (3, 1, 2);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (4, 1, 3);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (5, 1, 3);
-- требования второй должности
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (4, 2, 4);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (5, 2, 3);
-- требования третьей должности
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (1, 3, 2);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (3, 3, 4);
INSERT INTO position_skill(skill_id, position_id, scale) VALUES (5, 3, 2);