CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    email      TEXT UNIQUE NOT NULL CHECK (email ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
),
    active     BOOLEAN     NOT NULL,
    password   TEXT        NOT NULL,
    first_name TEXT        NOT NULL,
    last_name  TEXT        NOT NULL,
    country    TEXT        NOT NULL,
    city       TEXT        NOT NULL,
    street     TEXT        NOT NULL,
    zip_code   TEXT        NOT NULL
);

CREATE TABLE confirmations
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER   NOT NULL REFERENCES users (id),
    confirm_code TEXT      NOT NULL,
    expire_date  TIMESTAMP NOT NULL
);

CREATE TABLE refresh_tokens
(
    uuid    TEXT PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users (id)
);

CREATE TABLE projects
(
    id              SERIAL PRIMARY KEY,
    name            TEXT      NOT NULL,
    description     TEXT      NOT NULL,
    start_date      TIMESTAMP NOT NULL,
    completion_date TIMESTAMP NOT NULL
);

CREATE TABLE tasks
(
    id                        SERIAL PRIMARY KEY,
    project_id      INTEGER   NOT NULL REFERENCES projects (id),
    name                      TEXT      NOT NULL,
    description               TEXT      NOT NULL,
    priority                  INTEGER   NOT NULL,
    start_date                TIMESTAMP NOT NULL,
    estimated_completion_date TIMESTAMP NOT NULL
);

CREATE TABLE members
(
    id         SERIAL PRIMARY KEY,
    is_owner   BOOLEAN NOT NULL,
    user_id    INTEGER NOT NULL REFERENCES projects (id),
    project_id INTEGER NOT NULL REFERENCES users (id),
    CONSTRAINT members_pk UNIQUE (user_id, project_id)
);

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    comment TEXT NOT NULL,
    member_id      INTEGER   NOT NULL REFERENCES members (id),
    task_id      INTEGER   NOT NULL REFERENCES tasks (id)
);
