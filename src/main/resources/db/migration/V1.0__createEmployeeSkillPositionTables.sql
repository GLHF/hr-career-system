CREATE TABLE IF NOT EXISTS employee
(
    ID         SERIAL PRIMARY KEY,
    first_name text,
    last_name  text
);

CREATE TABLE IF NOT EXISTS position
(
    ID    SERIAL PRIMARY KEY,
    title text
);

CREATE TABLE IF NOT EXISTS skill
(
    ID    SERIAL PRIMARY KEY,
    title text
);

CREATE TABLE IF NOT EXISTS employee_skill
(
    ID          SERIAL PRIMARY KEY,
    skill_id    bigint,
    employee_id bigint,
    scale       integer
);

CREATE TABLE IF NOT EXISTS position_skill
(
    ID          SERIAL PRIMARY KEY,
    skill_id    bigint,
    position_id bigint,
    scale       integer
);