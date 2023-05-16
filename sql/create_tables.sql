CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   email TEXT UNIQUE NOT NULL CHECK (email ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
  active BOOLEAN NOT NULL,
  password TEXT NOT NULL,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  country TEXT NOT NULL,
  city TEXT NOT NULL,
  street TEXT NOT NULL,
  zip_code TEXT NOT NULL
);

CREATE TABLE confirmations
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER   NOT NULL REFERENCES users (id),
    confirm_code TEXT      NOT NULL,
    expire_date  TIMESTAMP NOT NULL
);
